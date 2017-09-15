package com.github.chen0040.mesos.client;


import com.alibaba.fastjson.JSON;
import com.github.chen0040.mesos.client.marathon.MarathonJobInfoList;
import com.github.chen0040.mesos.client.marathon.MarathonJobRequest;
import com.github.chen0040.mesos.client.marathon.MarathonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by xschen on 25/6/2016.
 */
public class MarathonUtilIntegrationTest {
   private static final String marathonUrl = "http://10.0.0.2:8080";
   private static final String regressionTestCmd = "while [ true ] ; do echo 'Hello Marathon' ; sleep 5 ; done";
   private static final String regressionTestId = "regtest";


   @Test public void testListJobs() {
      MarathonJobInfoList result = MarathonUtil.listJobs(marathonUrl);

      result.getApps().stream().forEach(job -> {
         System.out.println("id: " + job.getId());
         System.out.println("cmd: " + job.getCmd());
      });

      //System.out.println(JSON.toJSONString(result));
   }


   @Test public void testGetJob() {
      MarathonJobInfoList result = MarathonUtil.listJobs(marathonUrl);

      if (!result.getApps().isEmpty()) {
         String appId = result.getApps().get(0).getId();

         MarathonJobInfoList jobInfo = MarathonUtil.listJobs(marathonUrl, appId);

         System.out.println(JSON.toJSONString(jobInfo));

         Assert.assertEquals(appId, jobInfo.getApps().get(0).getId());
      }
   }


   @Test public void testStartJob() {
      if (!MarathonUtil.jobExists(marathonUrl, regressionTestId)) {
         MarathonJobRequest request = new MarathonJobRequest();
         request.setCmd(regressionTestCmd);
         request.setId(regressionTestId);
         System.out.println(MarathonUtil.startJob(marathonUrl, request));

         try {
            Thread.sleep(5000l);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }

         Assert.assertTrue(MarathonUtil.jobExists(marathonUrl, regressionTestId));
      }
   }


   @Test public void testRestartJob() {
      if (MarathonUtil.jobExists(marathonUrl, regressionTestId)) {
         System.out.println(MarathonUtil.restartJob(marathonUrl, regressionTestId));

         try {
            Thread.sleep(5000l);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }

         Assert.assertTrue(MarathonUtil.jobExists(marathonUrl, regressionTestId));
      }
   }


   @Test public void testKillJob() {
      if (MarathonUtil.jobExists(marathonUrl, regressionTestId)) {
         System.out.println(MarathonUtil.killJob(marathonUrl, regressionTestId));

         try {
            Thread.sleep(5000l);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }

         Assert.assertFalse(MarathonUtil.jobExists(marathonUrl, regressionTestId));
      }
   }
}
