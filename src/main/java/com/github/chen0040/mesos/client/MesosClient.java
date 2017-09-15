package com.github.chen0040.mesos.client;


import com.alibaba.fastjson.JSON;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xschen on 3/10/16.
 */
public class MesosClient {
   private Map<String, Object> properties = new HashMap<>();
   private static final String DATA_ENCODING = "UTF-8";


   private CloseableHttpClient buildClient() {
      //HttpClientBuilder builder = HttpClientBuilder.create();

      int timeout = 10;
      RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).build();

      return HttpClients.custom().setDefaultRequestConfig(config).build(); //builder.build();

   }


   public void add(String key, Object value) {
      properties.put(key, value);
   }


   public String post(final String url) {
      CloseableHttpClient httpClient = buildClient();
      String json = "";
      String body = JSON.toJSONString(properties);
      try {
         HttpPost request = new HttpPost(url);
         StringEntity params = new StringEntity(body);
         request.addHeader("content-type", "application/json");
         request.setEntity(params);
         CloseableHttpResponse result = httpClient.execute(request);
         if (result.getEntity() != null) {
            json = EntityUtils.toString(result.getEntity(), DATA_ENCODING);
         }
         result.close();
         httpClient.close();
      }
      catch (IOException ex) {
         json = ex.getMessage();
      }

      return json;
   }


   public String post(final String url, final Map<String, String> headers) {
      CloseableHttpClient httpClient = buildClient();
      String json = "";
      try {
         HttpPost request = new HttpPost(url);

         for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
         }

         CloseableHttpResponse result = httpClient.execute(request);
         if (result.getEntity() != null) {
            json = EntityUtils.toString(result.getEntity(), DATA_ENCODING);
         }
         result.close();
         httpClient.close();
      }
      catch (IOException ex) {
         json = ex.getMessage();
      }

      return json;
   }


   public String post(final String url, final Map<String, String> headers, Object requestBody) {
      CloseableHttpClient httpClient = buildClient();
      String json = "";
      try {
         HttpPost request = new HttpPost(url);

         StringEntity params = new StringEntity(JSON.toJSONString(requestBody));
         params.setContentType("application/json");

         for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
         }

         request.setEntity(params);

         CloseableHttpResponse result = httpClient.execute(request);
         if (result.getEntity() != null) {
            json = EntityUtils.toString(result.getEntity(), DATA_ENCODING);
         }
         result.close();
         httpClient.close();
      }
      catch (IOException ex) {
         json = ex.getMessage();
      }

      return json;
   }


   public String delete(final String url, final Map<String, String> headers) {

      CloseableHttpClient httpClient = buildClient();
      String json = "";
      try {
         HttpDelete request = new HttpDelete(url);

         for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
         }

         CloseableHttpResponse response = httpClient.execute(request);
         if (response.getEntity() != null) {
            json = EntityUtils.toString(response.getEntity(), DATA_ENCODING);
         }
         response.close();
         httpClient.close();
      }
      catch (IOException ex) {
         json = ex.getMessage();
      }
      return json;
   }


   public String delete(final String url) {

      CloseableHttpClient httpClient = buildClient();
      String json = "";
      try {
         HttpDelete request = new HttpDelete(url);
         request.addHeader("content-type", "application/json");
         CloseableHttpResponse response = httpClient.execute(request);
         if (response.getEntity() != null) {
            json = EntityUtils.toString(response.getEntity(), DATA_ENCODING);
         }
         response.close();
         httpClient.close();
      }
      catch (IOException ex) {
         json = ex.getMessage();
      }
      return json;
   }


   public String get(final String url) {
      String json = "";
      try {
         CloseableHttpClient httpClient = buildClient();
         HttpGet request = new HttpGet(url);
         request.addHeader("content-type", "application/json");
         CloseableHttpResponse response = httpClient.execute(request);
         if (response.getEntity() != null) {
            json = EntityUtils.toString(response.getEntity(), DATA_ENCODING);
         }
         //logger.info("spark[tryReadAlgorithmModuleStatus]: "+json);
      }
      catch (Exception ex2) {
         json = ex2.getMessage();
      }

      return json;
   }


   public String get(final String url, final Map<String, String> headers) {
      String json = "";
      try {
         CloseableHttpClient httpClient = buildClient();
         HttpGet request = new HttpGet(url);
         for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
         }
         CloseableHttpResponse response = httpClient.execute(request);
         if (response.getEntity() != null) {
            json = EntityUtils.toString(response.getEntity(), DATA_ENCODING);
         }
         //logger.info("spark[tryReadAlgorithmModuleStatus]: "+json);
      }
      catch (Exception ex2) {
         json = ex2.getMessage();
      }

      return json;
   }
}
