package com.github.chen0040.mesos.client.core;



import org.apache.commons.codec.Charsets;

import java.util.Base64;


/**
 * Created by xschen on 22/7/2016.
 */
public class Base64Encoding {
   public static String stringToHexString(String text) {
      return Base64.getEncoder().encodeToString(text.getBytes(Charsets.UTF_8));
   }
}
