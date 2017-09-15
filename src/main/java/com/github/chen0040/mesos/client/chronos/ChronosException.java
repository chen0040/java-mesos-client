package com.github.chen0040.mesos.client.chronos;


/**
 * Created by xschen on 9/29/16.
 */
public class ChronosException extends Exception {
   private static final long serialVersionUID = 318707041370546474L;

   public ChronosException() {
      super();
   }

   public ChronosException(String message) {
      super(message);
   }

   public ChronosException(String chronosUrl, String message, String responseBody) {
      super(chronosUrl.concat("throws exception: ").concat(message).concat("\r\nrespones from chronos: ").concat(responseBody));
   }
}
