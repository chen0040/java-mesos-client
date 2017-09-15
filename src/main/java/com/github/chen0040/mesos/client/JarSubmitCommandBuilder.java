package com.github.chen0040.mesos.client;


import com.alibaba.fastjson.JSON;


/**
 * Created by xschen on 22/7/2016.
 */
public class JarSubmitCommandBuilder {
   private String jarFilePath;
   private String args = null;
   private String springActiveProfile = "env";
   private static final String JVMGC = "-XX:+UseG1GC";


   private JarSubmitCommandBuilder(String jarFilePath) {
      this.jarFilePath = jarFilePath;
      this.args = null;
   }


   public static JarSubmitCommandBuilder newInstance(String jarFilePath) {
      return new JarSubmitCommandBuilder(jarFilePath);
   }


   public JarSubmitCommandBuilder withInfo(Object algorithmProperties) {

      String json = JSON.toJSONString(algorithmProperties);
      this.args = Base64Encoding.stringToHexString(json);

      return this;
   }


   public JarSubmitCommandBuilder withActiveSpringProfile(String env) {
      this.springActiveProfile = env;
      return this;
   }


   public String build() {
      String command = "java -Dspring.profiles.active=" + springActiveProfile + " " + JVMGC + " -jar " + jarFilePath ;
      if (args != null) {
         command = command + " -j " + args;
      }
      return command;
   }

}
