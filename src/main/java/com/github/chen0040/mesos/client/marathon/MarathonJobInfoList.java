package com.github.chen0040.mesos.client.marathon;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xschen on 25/6/2016.
 */
public class MarathonJobInfoList implements Serializable {
   private static final long serialVersionUID = 1573582201438848637L;
   private List<MarathonJobInfo> apps = new ArrayList<>();


   public List<MarathonJobInfo> getApps() {
      return apps;
   }


   public void setApps(List<MarathonJobInfo> apps) {
      this.apps = apps;
   }
}
