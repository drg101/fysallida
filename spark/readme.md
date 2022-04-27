# Compile
sbt compile
# Make JAR
sbt package 
# Run
./run.sh


# Spark setup
Will probably need to fix SPARK_HOME and add sbt(scala build system) to your path with things like below
I can provide you with spark-3.0.3-bin-hadoop2.7 config

export SPARK_HOME=${HOME}/cs455/spark-3.0.3-bin-hadoop2.7
export PATH=$PATH:/usr/local/sbt/latest/bin