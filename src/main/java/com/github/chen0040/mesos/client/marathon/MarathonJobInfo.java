package com.github.chen0040.mesos.client.marathon;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xschen on 25/6/2016.
 */
public class MarathonJobInfo implements Serializable {
   private static final long serialVersionUID = -7916981767974603749L;
   private String id = "";
   private String cmd = "";
   private List<List<String>> constraints = new ArrayList<>();

   private String container = null;

   private double cpus = 0.1;
   private Map<String, String> env = new HashMap<>();

   private String executor = "";
   private int instances = 3;
   private double mem = 5.0;
   private List<Integer> ports = new ArrayList<>();

   private int tasksRunning = 0;
   private int tasksStaged = 1;
   private List<String> uris = new ArrayList<>();
   private String version = "";


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


   public List<List<String>> getConstraints() {
      return constraints;
   }


   public void setConstraints(List<List<String>> constraints) {
      this.constraints = constraints;
   }


   public String getContainer() {
      return container;
   }


   public void setContainer(String container) {
      this.container = container;
   }


   public double getCpus() {
      return cpus;
   }


   public void setCpus(double cpus) {
      this.cpus = cpus;
   }


   public Map<String, String> getEnv() {
      return env;
   }


   public void setEnv(Map<String, String> env) {
      this.env = env;
   }


   public String getExecutor() {
      return executor;
   }


   public void setExecutor(String executor) {
      this.executor = executor;
   }


   public int getInstances() {
      return instances;
   }


   public void setInstances(int instances) {
      this.instances = instances;
   }


   public double getMem() {
      return mem;
   }


   public void setMem(double mem) {
      this.mem = mem;
   }


   public List<Integer> getPorts() {
      return ports;
   }


   public void setPorts(List<Integer> ports) {
      this.ports = ports;
   }


   public int getTasksRunning() {
      return tasksRunning;
   }


   public void setTasksRunning(int tasksRunning) {
      this.tasksRunning = tasksRunning;
   }


   public int getTasksStaged() {
      return tasksStaged;
   }


   public void setTasksStaged(int tasksStaged) {
      this.tasksStaged = tasksStaged;
   }


   public List<String> getUris() {
      return uris;
   }


   public void setUris(List<String> uris) {
      this.uris = uris;
   }


   public String getVersion() {
      return version;
   }


   public void setVersion(String version) {
      this.version = version;
   }
}
