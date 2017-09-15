package com.github.chen0040.mesos.client;


import com.alibaba.fastjson.JSON;
import com.github.chen0040.mesos.client.chronos.ChronosException;
import com.github.chen0040.mesos.client.chronos.ChronosJobInfo;
import com.github.chen0040.mesos.client.chronos.ChronosJobRequest;
import com.github.chen0040.mesos.client.chronos.ChronosUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;


/**
 * Created by xschen on 25/6/2016.
 */
public class ChronosUtilIntegrationTest {
   private static final String chronosUrl = "http://10.0.0.1:4400";
   private static final String regressionTestCmd = "echo 'Hello Chronos' ; sleep 5";
   private static final String regressionTestId = "regtest";

   @BeforeMethod
   public void setup(){

   }


   @Test public void testListJobs() throws ChronosException {
      List<ChronosJobInfo> result = ChronosUtil.listJobs(chronosUrl);

      result.stream().forEach(job -> {
         System.out.println("name: " + job.getName());
         System.out.println("command: " + job.getCommand());
      });

      //System.out.println(JSON.toJSONString(result));
   }


   @Test public void testGetJob() throws ChronosException {
      List<ChronosJobInfo> result = ChronosUtil.listJobs(chronosUrl);

      if (!result.isEmpty()) {
         String appId = result.get(0).getName();

         Optional<ChronosJobInfo> jobInfo = ChronosUtil.getJob(chronosUrl, appId);

         System.out.println(JSON.toJSONString(jobInfo.orElseGet(ChronosJobInfo::new)));

         Assert.assertEquals(appId, jobInfo.get().getName());
      }
   }


   @Test public void testStartJob() throws ChronosException {
      if (!ChronosUtil.jobExists(chronosUrl, regressionTestId)) {
         ChronosJobRequest request = new ChronosJobRequest("PT15M");
         request.setCommand(regressionTestCmd);
         request.setName(regressionTestId);
         request.setCpus(0.1);
         request.setMem(100);

         System.out.println(ChronosUtil.startJob(chronosUrl, request));

         try {
            Thread.sleep(5000l);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }

         Assert.assertTrue(ChronosUtil.jobExists(chronosUrl, regressionTestId));
      }
   }


   @Test public void testKillJob() throws ChronosException {
      if (ChronosUtil.jobExists(chronosUrl, regressionTestId)) {
         System.out.println(ChronosUtil.killJob(chronosUrl, regressionTestId));

         try {
            Thread.sleep(5000l);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }

         Assert.assertFalse(ChronosUtil.jobExists(chronosUrl, regressionTestId));
      }
   }
}
