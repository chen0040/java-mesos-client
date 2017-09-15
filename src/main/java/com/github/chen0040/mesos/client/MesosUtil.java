package com.github.chen0040.mesos.client;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by xschen on 8/2/2017.
 */
public class MesosUtil {


   public static String getStateJson(String mesosMasterUrl){
      String url = mesosMasterUrl + "/master/state.json";

      MesosClient http = new MesosClient();


      Map<String, String> headers = new HashMap<>();
      headers.put("Content-Type", "application/json");

      String responseBody = http.get(url, headers);

      return responseBody;
   }

   public static String getTasks(String mesosMasterUrl) {
      String url = mesosMasterUrl + "/master/tasks";

      MesosClient http = new MesosClient();


      Map<String, String> headers = new HashMap<>();
      headers.put("Content-Type", "application/json");

      String responseBody = http.get(url, headers);

      return responseBody;
   }
}
