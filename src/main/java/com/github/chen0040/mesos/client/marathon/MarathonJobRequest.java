package com.github.chen0040.mesos.client.marathon;


import java.io.Serializable;


/**
 * Created by xschen on 25/6/2016.
 */
public class MarathonJobRequest implements Serializable {
   private static final long serialVersionUID = -7588404981170122735L;
   private String id = "duplicated";
   private String cmd = "sleep 100";
   private double cpus = 0.1;
   private int mem = 16;
   private int instances = 1;


   public String getId() {
      return id;
   }


   public void setId(String id) {
      this.id = id;
   }


   public String getCmd() {
      return cmd;
   }


   public void setCmd(String cmd) {
      this.cmd = cmd;
   }


   public double getCpus() {
      return cpus;
   }


   public void setCpus(double cpus) {
      this.cpus = cpus;
   }


   public int getMem() {
      return mem;
   }


   public void setMem(int mem) {
      this.mem = mem;
   }


   public int getInstances() {
      return instances;
   }


   public void setInstances(int instances) {
      this.instances = instances;
   }
}
