import org.apache.spark.sql.SparkSession

object Fysallida {

	def main(args: Array[String]): Unit = {
		val sc = SparkSession.builder().master("spark://olympia:41054").getOrCreate().sparkContext

		val lines = sc.textFile(args(0))

		lines.saveAsTextFile(args(1))

	}
}