package com.github.chen0040.mesos.client.marathon;


import com.alibaba.fastjson.JSON;
import com.github.chen0040.mesos.client.core.MesosClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Created by xschen on 25/6/2016.
 */
public class MarathonUtil {

   private static final Logger logger = LoggerFactory.getLogger(MarathonUtil.class);


   public static String startJob(String marathonUrl, MarathonJobRequest request) {
      MesosClient http = new MesosClient();
      //HTTP/1.1
      String url = marathonUrl.concat("/v2/apps");

      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "application/json");
      headers.put("Accept-Encoding", "gzip, deflate");
      headers.put("Content-Type", "application/json; charset=utf-8");

      return http.post(url, headers, request);
   }


   public static MarathonJobInfoList listJobs(String marathonUrl, String appId) {

      MesosClient http = new MesosClient();
      String url = marathonUrl.concat("/v2/apps?id=").concat(appId);

      logger.info("marathon: {}", url);

      String responseBody = http.get(url);

      //logger.info("marathon.response: {}", responseBody);

      return JSON.parseObject(responseBody, MarathonJobInfoList.class);
   }


   public static Optional<MarathonJobInfo> getJob(String marathonUrl, String appId) {

      MarathonJobInfoList result = listJobs(marathonUrl, appId);

      List<MarathonJobInfo> jobs = result.getApps();

      if (jobs.isEmpty()) {
         return Optional.empty();
      }
      return Optional.of(jobs.get(0));
   }


   public static boolean jobExists(String marathonUrl, String appId) {

      return getJob(marathonUrl, appId).isPresent();
   }


   public static String restartJob(String marathonUrl, String appId) {
      MesosClient http = new MesosClient();

      //HTTP/1.1
      String url = marathonUrl.concat("/v2/apps/").concat(appId).concat("/restart");

      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "application/json");
      headers.put("Accept-Encoding", "gzip, deflate, compress");
      headers.put("Content-Length", "0");

      return http.post(url, headers);
   }


   public static String killJob(String marathonUrl, String appId) {
      MesosClient http = new MesosClient();

      //HTTP/1.1
      String url = marathonUrl.concat("/v2/apps/").concat(appId);

      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "application/json");
      headers.put("Accept-Encoding", "gzip, deflate, compress");
      headers.put("Content-Length", "0");
      headers.put("Content-Type", "application/json; charset=utf-8");

      return http.delete(url, headers);
   }


   public static MarathonJobInfoList listJobs(String marathonUrl) {
      MesosClient http = new MesosClient();

      //HTTP/1.1
      String url = marathonUrl.concat("/v2/apps/");

      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "application/json");
      headers.put("Accept-Encoding", "gzip, deflate, compress");
      headers.put("Content-Type", "application/json; charset=utf-8");

      String responseBody = http.get(url, headers);

      return JSON.parseObject(responseBody, MarathonJobInfoList.class);

   }
}
