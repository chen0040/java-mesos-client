package com.github.chen0040.mesos.client.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * Created by xschen on 27/6/2016.
 */
public class ChronosTool implements Serializable {

   private static final long serialVersionUID = -4058493712046518445L;
   private static Logger logger = LoggerFactory.getLogger(ChronosTool.class);


   public String getMasterUrl() {
      return "mesos://zk://" + getZkConnect() + "/mesos";
   }


   protected String zkConnect = "d-clef-stream-04:2181,d-clef-stream-05:2181,d-clef-stream-06:2181";

   public String getZkConnect() {
      return zkConnect;
   }

   public void setZkConnect(String zkConnect) {
      this.zkConnect = zkConnect;
   }
}
