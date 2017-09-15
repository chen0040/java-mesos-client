package com.github.chen0040.mesos.client.chronos;


import com.alibaba.fastjson.JSON;
import com.github.chen0040.mesos.client.core.MesosClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by xschen on 27/6/2016.
 */
public class ChronosUtil {

   private static Logger logger = LoggerFactory.getLogger(ChronosUtil.class);


   private static String getChronosDeleteUrl(String chronosUrl) {
      return chronosUrl + "/scheduler/job";
   }


   public static String killJob(String chronosUrl, String jobName) {
      try {
         MesosClient mr = new MesosClient();
         return mr.delete(getChronosDeleteUrl(chronosUrl) + "/" + jobName);
      }
      catch (Exception exception) {
         logger.error("Failed to kill job: " + jobName, exception);
      }
      return "";
   }


   public static boolean jobExists(String chronosUrl, String app) throws ChronosException {
      return getJob(chronosUrl, app).isPresent();
   }


   public static Optional<ChronosJobInfo> getJob(String chronosUrl, String app) throws ChronosException {
      List<ChronosJobInfo> jobs = listJobs(chronosUrl);

      //jobs.forEach(j -> logger.info("{} = {}", j.getName(), app));

      return jobs.stream().filter(job -> job.getName().equals(app)).findAny();
   }


   public static List<ChronosJobInfo> listJobs(String chronosUrl) throws ChronosException {
      MesosClient http = new MesosClient();

      //HTTP/1.1
      String url = chronosUrl.concat("/scheduler/jobs");

      Map<String, String> headers = new HashMap<>();
      headers.put("Content-Type", "application/json");

      String responseBody = http.get(url, headers);

      //

      try {
         List<ChronosJobInfo> jobs = JSON.parseArray(responseBody, ChronosJobInfo.class);
         return jobs;
      }catch(Exception exception){
         logger.info("chronos returned: {}", responseBody);
         throw new ChronosException(chronosUrl, exception.getMessage(), responseBody);
      }
   }

   public static List<ChronosJobInfo> listJobs(String chronosUrl, String prefix) throws ChronosException {
      List<ChronosJobInfo> jobs = listJobs(chronosUrl);

      return jobs.stream().filter(job -> job.getName().startsWith(prefix)).collect(Collectors.toList());
   }



   public static String startJob(String chronosUrl, ChronosJobRequest request) {

      MesosClient http = new MesosClient();

      //HTTP/1.1
      String url = chronosUrl.concat("/scheduler/iso8601");

      Map<String, String> headers = new HashMap<>();

      headers.put("Content-Type", "application/json");

      return http.post(url, headers, request);
   }
}
