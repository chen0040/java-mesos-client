package com.github.chen0040.mesos.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 * Created by xschen on 8/2/2017.
 */
public class MesosUtilIntegrationTest {

   private static final String mesosMasterUrl1 = "http://10.0.0.15:5050";
   private static final String mesosMasterUrl2 = "http://10.0.0.16:5050";

   private static final Logger logger = LoggerFactory.getLogger(MesosUtilIntegrationTest.class);

   @Test
   public void test_getStats(){
      String json1 =MesosUtil.getStateJson(mesosMasterUrl1);
      logger.info(json1);
      String json2 =MesosUtil.getStateJson(mesosMasterUrl2);
      logger.info(json2);

   }

   @Test
   public void test_getTasks(){
      String json1 = MesosUtil.getTasks("http://10.0.0.15:5050");
      logger.info(json1);
   }

}
