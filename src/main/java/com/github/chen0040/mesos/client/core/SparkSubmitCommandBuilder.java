package com.github.chen0040.mesos.client.core;


import com.alibaba.fastjson.JSON;


/**
 * Created by xschen on 22/7/2016.
 */
public class SparkSubmitCommandBuilder {

   private int numExecutors = 2;
   private int executorCores = 1;
   private String deployMode = "client";
   private String env = "dev";
   private String executorMemory = "1g";
   private String driverMemory = "1g";

   private int maxKryoSerializerBuffer = 256;
   private int sparkAkkaHeartbeatPauses = 60000;
   private int sparkAkkaHeartbeatInterval = 1000;
   private int sparkDefaultParallelism = 1000;
   private boolean jmx4Driver = false;
   private boolean sparkRddCompress = true;
   private String mainClass = null;
   private String springActiveProfile = "";

   private int maxCores = 10;

   private String args = null;

   private String jarFilePath;
   private String sparkMasterUrl;

   private String sparkRootPath = "/home/clef/spark/spark-1.6.0-bin-hadoop2.6";

   public static final String JVMGC = "-XX:+UseG1GC"; // "-XX:+UseConcMarkSweepGC";


   public SparkSubmitCommandBuilder withSparkRootPath(String sparkRootPath){
      this.sparkRootPath = sparkRootPath;
      return this;
   }

   private SparkSubmitCommandBuilder(String sparkMasterUrl, String jarFilePath) {
      this.sparkMasterUrl = sparkMasterUrl;
      this.jarFilePath = jarFilePath;
      this.args = null;
   }



   public static SparkSubmitCommandBuilder newInstance(String sparkMasterUrl, String jarFilePath) {
      return new SparkSubmitCommandBuilder(sparkMasterUrl, jarFilePath);
   }


   public SparkSubmitCommandBuilder withNumExecutors(int numExecutors) {
      this.numExecutors = numExecutors;
      return this;
   }

   public SparkSubmitCommandBuilder withDefaultParallelism(int parallelism) {
      this.sparkDefaultParallelism = parallelism;
      return this;
   }


   public SparkSubmitCommandBuilder withMaxCores(int maxCores) {
      this.maxCores = maxCores;
      return this;
   }

   public SparkSubmitCommandBuilder withMainClass(String mainClass) {
      this.mainClass = mainClass;
      return this;
   }


   public SparkSubmitCommandBuilder withExecutorCores(int executorCores) {
      this.executorCores = executorCores;
      return this;
   }


   public SparkSubmitCommandBuilder withEnv(String env) {
      this.env = env;
      return this;
   }


   public SparkSubmitCommandBuilder withExecutorMemory(String executorMemory) {
      this.executorMemory = executorMemory;
      return this;
   }


   public SparkSubmitCommandBuilder withDriverMemory(String driverMemory) {
      this.driverMemory = driverMemory;
      return this;
   }


   public SparkSubmitCommandBuilder withInfo(Object algorithmProperties) {

      String json = JSON.toJSONString(algorithmProperties);
      this.args = Base64Encoding.stringToHexString(json);

      return this;
   }


   public String build() {

      StringBuilder sb = new StringBuilder();

      sb.append(" -Dspark.kryoserializer.buffer.max=").append(maxKryoSerializerBuffer).append("m");
      sb.append(" -Dspark.rdd.compress=").append(sparkRddCompress);
      sb.append(" -Dspark.akka.heartbeat.pauses=").append(sparkAkkaHeartbeatPauses);
      sb.append(" -Dspark.akka.heartbeat.interval=").append(sparkAkkaHeartbeatInterval);
      sb.append(" -Dspark.default.parallelism=").append(sparkDefaultParallelism);
      sb.append(" -Dspark.rpc.askTimeout=900000");
      sb.append(" -Dspark.network.timeout=900000");
      sb.append(" -Dspark.executor.extraJavaOptions="+JVMGC);
      sb.append(" -Dspark.cores.max="+maxCores);

      if(springActiveProfile != null && !springActiveProfile.equals("")) {
         sb.append(" -Dspring.profiles.active=" + springActiveProfile);
      }

      if (jmx4Driver) {
         sb.append(" -Dcom.sun.management.jmxremote");
         sb.append(" -Dcom.sun.management.jmxremote.port=8090");
         sb.append(" -Dcom.sun.management.jmxremote.rmi.port=8091");
         sb.append(" -Dcom.sun.management.jmxremote.authenticate=false");
         sb.append(" -Dcom.sun.management.jmxremote.ssl=false");
      }

      String executorJavaExtra = "";

      String textMainClass = "";

      if(mainClass!=null && !mainClass.equals("")) {
         textMainClass = " --class ".concat(mainClass);
      }

      String command =
              sparkRootPath + "/bin/spark-submit  --master " + sparkMasterUrl + "  --deploy-mode " + deployMode + executorJavaExtra
                      + " --conf spark.driver.userClassPathFirst=true" + " --driver-java-options \"" + JVMGC + " -Denv=" + env + sb.toString() + "\" --executor-memory " + executorMemory + " --driver-memory "
                      + driverMemory + " --executor-cores " + executorCores + " --num-executors " + numExecutors + textMainClass + " " + jarFilePath;

      if (args != null) {
         command = command + " -j " + args;
      }
      return command;
   }


   public SparkSubmitCommandBuilder withSpringProfile(String activeProfile) {
      this.springActiveProfile = activeProfile;
      return this;

   }
}
