import org.apache.spark.sql.SparkSession

object Fysallida {

	def main(args: Array[String]): Unit = {
		val spark = SparkSession.builder().master("spark://olympia:41054").getOrCreate()
		val sc = spark.sparkContext


		val qcew_07 = spark.read.format("csv").option("header", "true").load("/proj/input/QCEW/2007.annual.singlefile.csv")
		val qcew_19 = spark.read.format("csv").option("header", "true").load("/proj/input/QCEW/2019.annual.singlefile.csv")
		val curated05_09 = spark.read.format("csv").option("header", "true").load("/proj/input/ml/2005_2009_curated.csv")
		val curated15_19 = spark.read.format("csv").option("header", "true").load("/proj/input/ml/2015_2019_curated.csv")
		val crash_amount = spark.read.format("csv").option("header", "true").load("/proj/input/ml/crash_amounts.csv")

		// filter out weird fips codes
		// 2007
		val filtered_qcew_07 = qcew_07.filter(row => {
			var ret: Boolean = true
			val fips = row.getString(0)
			try{
				fips.toInt
			} catch {
				case e: Exception=>
					ret = false
			}
			ret
		})
		// 2019
		val filtered_qcew_19 = qcew_19.filter(row => {
			var ret: Boolean = true
			val fips = row.getString(0)
			try{
				fips.toInt
			} catch {
				case e: Exception=>
					ret = false
			}
			ret
		})

		//filtered_qcew_07.show()
		//filtered_qcew_19.show()

		// getting avg of interesting features per county for qcews
		// 2007
		filtered_qcew_07.createOrReplaceTempView("qcew_07")
		val avg_07 = spark.sql("""SELECT DISTINCT 
								area_fips, 
								AVG(avg_annual_pay) as avg_annual_pay,
								AVG(oty_annual_avg_estabs_pct_chg) as oty_annual_avg_estabs_pct_chg,
								AVG(oty_annual_avg_emplvl_pct_chg) as oty_annual_avg_emplvl_pct_chg,
								AVG(oty_total_annual_wages_pct_chg) as oty_total_annual_wages_pct_chg,
								AVG(oty_taxable_annual_wages_pct_chg) as oty_taxable_annual_wages_pct_chg,
								AVG(oty_annual_contributions_pct_chg) as oty_annual_contributions_pct_chg,
								AVG(oty_annual_avg_wkly_wage_pct_chg) as oty_annual_avg_wkly_wage_pct_chg, 
								AVG(oty_avg_annual_pay_pct_chg) as oty_avg_annual_pay_pct_chg 
							FROM qcew_07 
							GROUP BY area_fips""")
		// 2019
		filtered_qcew_19.createOrReplaceTempView("qcew_19")
		val avg_19 = spark.sql("""SELECT DISTINCT 
								area_fips, 
								AVG(avg_annual_pay) as avg_annual_pay,
								AVG(oty_annual_avg_estabs_pct_chg) as oty_annual_avg_estabs_pct_chg,
								AVG(oty_annual_avg_emplvl_pct_chg) as oty_annual_avg_emplvl_pct_chg,
								AVG(oty_total_annual_wages_pct_chg) as oty_total_annual_wages_pct_chg,
								AVG(oty_taxable_annual_wages_pct_chg) as oty_taxable_annual_wages_pct_chg,
								AVG(oty_annual_contributions_pct_chg) as oty_annual_contributions_pct_chg,
								AVG(oty_annual_avg_wkly_wage_pct_chg) as oty_annual_avg_wkly_wage_pct_chg, 
								AVG(oty_avg_annual_pay_pct_chg) as oty_avg_annual_pay_pct_chg 
							FROM qcew_19 
							GROUP BY area_fips""")

		//avg_07.show()
		//avg_19.show()

		// fips code -> GISJOIN
		// 2007
		import spark.implicits._
		val gis_avg_07 = avg_07.map(row=>{
			val state_county_fips = row.getString(0)
			val state_fips = state_county_fips.slice(0,2)
			val county_fips = state_county_fips.slice(2, state_county_fips.length)
			val GISJOIN = f"G${state_fips.toInt}%02d${county_fips.toInt}%04d0"
			(GISJOIN, row.getDouble(1), row.getDouble(2), row.getDouble(3), row.getDouble(4), row.getDouble(5), row.getDouble(6), row.getDouble(7), row.getDouble(8))
		}).toDF("GISJOIN", "avg_annual_pay", "oty_annual_avg_estabs_pct_chg", "oty_annual_avg_emplvl_pct_chg", "oty_total_annual_wages_pct_chg", "oty_taxable_annual_wages_pct_chg", "oty_annual_contributions_pct_chg", "oty_annual_avg_wkly_wage_pct_chg", "oty_avg_annual_pay_pct_chg")
		// 2019
		val gis_avg_19 = avg_19.map(row=>{
			val state_county_fips = row.getString(0)
			val state_fips = state_county_fips.slice(0,2)
			val county_fips = state_county_fips.slice(2, state_county_fips.length)
			val GISJOIN = f"G${state_fips.toInt}%02d${county_fips.toInt}%04d0"
			(GISJOIN, row.getDouble(1), row.getDouble(2), row.getDouble(3), row.getDouble(4), row.getDouble(5), row.getDouble(6), row.getDouble(7), row.getDouble(8))
		}).toDF("GISJOIN", "avg_annual_pay", "oty_annual_avg_estabs_pct_chg", "oty_annual_avg_emplvl_pct_chg", "oty_total_annual_wages_pct_chg", "oty_taxable_annual_wages_pct_chg", "oty_annual_contributions_pct_chg", "oty_annual_avg_wkly_wage_pct_chg", "oty_avg_annual_pay_pct_chg")
		
		//gis_avg_07.show()
		//gis_avg_19.show()

		// giga join
		// create views
		curated05_09.createOrReplaceTempView("curated05_09")
		curated15_19.createOrReplaceTempView("curated15_19")
		crash_amount.createOrReplaceTempView("crash_amount")
		gis_avg_07.createOrReplaceTempView("nice_07")
		gis_avg_19.createOrReplaceTempView("nice_19")

		// use the views :}
		val old = spark.sql("""
			SELECT c.*,
				   n.avg_annual_pay,
				   n.oty_annual_avg_estabs_pct_chg,
				   n.oty_annual_avg_emplvl_pct_chg,
				   n.oty_total_annual_wages_pct_chg,
				   n.oty_taxable_annual_wages_pct_chg,
				   n.oty_annual_contributions_pct_chg,
				   n.oty_annual_avg_wkly_wage_pct_chg,
				   n.oty_avg_annual_pay_pct_chg
			FROM curated05_09 c, nice_07 n
			WHERE c.GISJOIN = n.GISJOIN
		""")
		//old.show()

		val now = spark.sql("""
			SELECT c.*,
				   n.avg_annual_pay,
				   n.oty_annual_avg_estabs_pct_chg,
				   n.oty_annual_avg_emplvl_pct_chg,
				   n.oty_total_annual_wages_pct_chg,
				   n.oty_taxable_annual_wages_pct_chg,
				   n.oty_annual_contributions_pct_chg,
				   n.oty_annual_avg_wkly_wage_pct_chg,
				   n.oty_avg_annual_pay_pct_chg
			FROM curated15_19 c, nice_19 n
			WHERE c.GISJOIN = n.GISJOIN
		""")
		//now.show()

		// write to csv

		old.write.option("header",true).csv("/proj/out/old")

		now.write.option("header",true).csv("/proj/out/now")
	}
}