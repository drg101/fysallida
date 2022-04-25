MAIN_CLASS=Fysallida
INPUT_FILE=/testFile
OUTPUT_FILE=/sparkTestOutput

$HADOOP_HOME/bin/hadoop fs -test -d ${OUTPUT_FILE}
if [ $? == 0 ];then
    echo "deleting ${OUTPUT_FILE} before running job."
    $HADOOP_HOME/bin/hadoop fs -rm -r ${OUTPUT_FILE}
fi

sbt package

if [ $? == 0 ]; then
    $SPARK_HOME/bin/spark-submit --class ${MAIN_CLASS} --deploy-mode client --supervise target/scala-2.12/fysallida_2.12-0.1.jar ${INPUT_FILE} ${OUTPUT_FILE}
fi

 $HADOOP_HOME/bin/hadoop fs -cat "${OUTPUT_FILE}/*"