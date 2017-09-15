package com.github.chen0040.mesos.client.chronos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xschen on 7/7/16.
 */
public class ChronosJobInfo implements Serializable {

   private static final long serialVersionUID = 2549487009282286134L;
   private String name = "";
   private String command = "";
   private boolean shell = true;
   private String epsilon = "PT60M";
   private String executor = "";
   private String executorFlags = "";
   private String owner = "";
   private String ownerName = "";
   private String description = "";
   private boolean async = false;
   private int successCount = 0;
   private String lastSuccess = "yyyy-MM-ddTHH:mm:ss.sssZ";
   private String lastError = "";
   private double cpus = 0.2;
   private double disk = 4100.0;
   private double mem = 4100.0;
   private boolean disabled = false;
   private boolean softError = false;
   private boolean dataProcessingJobType = false;
   private int errorsSinceLastSuccess = 0;
   private List<String> uris = new ArrayList<>();
   private List<String> environmentVariables = new ArrayList<>();
   private List<String> arguments = new ArrayList<>();
   private boolean highPriority = false;
   private String runAsUser = "root";
   private List<String> constraints = new ArrayList<>();
   private String schedule = "R/2016-07-17T20:37:51.000+08:00/P1M";
   private String scheduleTimeZone = "";


   public String getName() {
      return name;
   }


   public void setName(String name) {
      this.name = name;
   }


   public String getCommand() {
      return command;
   }


   public void setCommand(String command) {
      this.command = command;
   }


   public boolean isShell() {
      return shell;
   }


   public void setShell(boolean shell) {
      this.shell = shell;
   }


   public String getEpsilon() {
      return epsilon;
   }


   public void setEpsilon(String epsilon) {
      this.epsilon = epsilon;
   }


   public String getExecutor() {
      return executor;
   }


   public void setExecutor(String executor) {
      this.executor = executor;
   }


   public String getExecutorFlags() {
      return executorFlags;
   }


   public void setExecutorFlags(String executorFlags) {
      this.executorFlags = executorFlags;
   }


   public String getOwner() {
      return owner;
   }


   public void setOwner(String owner) {
      this.owner = owner;
   }


   public String getOwnerName() {
      return ownerName;
   }


   public void setOwnerName(String ownerName) {
      this.ownerName = ownerName;
   }


   public String getDescription() {
      return description;
   }


   public void setDescription(String description) {
      this.description = description;
   }


   public boolean isAsync() {
      return async;
   }


   public void setAsync(boolean async) {
      this.async = async;
   }


   public int getSuccessCount() {
      return successCount;
   }


   public void setSuccessCount(int successCount) {
      this.successCount = successCount;
   }


   public String getLastSuccess() {
      return lastSuccess;
   }


   public void setLastSuccess(String lastSuccess) {
      this.lastSuccess = lastSuccess;
   }


   public String getLastError() {
      return lastError;
   }


   public void setLastError(String lastError) {
      this.lastError = lastError;
   }


   public double getCpus() {
      return cpus;
   }


   public void setCpus(double cpus) {
      this.cpus = cpus;
   }


   public double getDisk() {
      return disk;
   }


   public void setDisk(double disk) {
      this.disk = disk;
   }


   public double getMem() {
      return mem;
   }


   public void setMem(double mem) {
      this.mem = mem;
   }


   public boolean isDisabled() {
      return disabled;
   }


   public void setDisabled(boolean disabled) {
      this.disabled = disabled;
   }


   public boolean isSoftError() {
      return softError;
   }


   public void setSoftError(boolean softError) {
      this.softError = softError;
   }


   public boolean isDataProcessingJobType() {
      return dataProcessingJobType;
   }


   public void setDataProcessingJobType(boolean dataProcessingJobType) {
      this.dataProcessingJobType = dataProcessingJobType;
   }


   public int getErrorsSinceLastSuccess() {
      return errorsSinceLastSuccess;
   }


   public void setErrorsSinceLastSuccess(int errorsSinceLastSuccess) {
      this.errorsSinceLastSuccess = errorsSinceLastSuccess;
   }


   public List<String> getUris() {
      return uris;
   }


   public void setUris(List<String> uris) {
      this.uris = uris;
   }


   public List<String> getEnvironmentVariables() {
      return environmentVariables;
   }


   public void setEnvironmentVariables(List<String> environmentVariables) {
      this.environmentVariables = environmentVariables;
   }


   public List<String> getArguments() {
      return arguments;
   }


   public void setArguments(List<String> arguments) {
      this.arguments = arguments;
   }


   public boolean isHighPriority() {
      return highPriority;
   }


   public void setHighPriority(boolean highPriority) {
      this.highPriority = highPriority;
   }


   public String getRunAsUser() {
      return runAsUser;
   }


   public void setRunAsUser(String runAsUser) {
      this.runAsUser = runAsUser;
   }


   public List<String> getConstraints() {
      return constraints;
   }


   public void setConstraints(List<String> constraints) {
      this.constraints = constraints;
   }


   public String getSchedule() {
      return schedule;
   }


   public void setSchedule(String schedule) {
      this.schedule = schedule;
   }


   public String getScheduleTimeZone() {
      return scheduleTimeZone;
   }


   public void setScheduleTimeZone(String scheduleTimeZone) {
      this.scheduleTimeZone = scheduleTimeZone;
   }
}
