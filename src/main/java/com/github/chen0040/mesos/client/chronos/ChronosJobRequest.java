package com.github.chen0040.mesos.client.chronos;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by xschen on 7/7/16.
 */
public class ChronosJobRequest implements Serializable {
   private static final long serialVersionUID = 1945151759622620852L;
   private String name = "";
   private String command = "";
   private String schedule = "";
   private String scheduleTimeZone = "";
   private String owner = "mlearn@clef-spark";
   private String epsilon = "PT15M";
   private boolean async = false;

   private String description = "";
   private double cpus = 0.3;
   private double mem = 4096;
   private double disk = 4096;


   public ChronosJobRequest(String epsilon) {
      schedule = buildSchedule((new Date()).getTime(), -1, epsilon);
   }

   public ChronosJobRequest(String epsilon, int repeatCount) {
      schedule = buildSchedule((new Date()).getTime(), repeatCount, epsilon);
   }

   public ChronosJobRequest(String epsilon, int repeatCount, long time) {
      schedule = buildSchedule(time, repeatCount, epsilon);
   }

   public double getCpus() {
      return cpus;
   }


   public void setCpus(double cpus) {
      this.cpus = cpus;
   }


   public double getMem() {
      return mem;
   }


   public void setMem(double mem) {
      this.mem = mem;
   }


   public double getDisk() {
      return disk;
   }


   public void setDisk(double disk) {
      this.disk = disk;
   }


   public String getName() {
      return name;
   }


   public void setName(String name) {
      this.name = name;
   }


   public void setDescription(String description) {
      this.description = description;
   }


   public String getDescription() {
      return description;
   }


   public String getCommand() {
      return command;
   }


   public void setCommand(String command) {
      this.command = command;
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


   public String getOwner() {
      return owner;
   }


   public void setOwner(String owner) {
      this.owner = owner;
   }


   public String getEpsilon() {
      return epsilon;
   }


   public void setEpsilon(String epsilon) {
      this.epsilon = epsilon;
   }


   public boolean isAsync() {
      return async;
   }


   public void setAsync(boolean async) {
      this.async = async;
   }


   public static String buildSchedule(long startTime, int repeatCount, String interval) {
      String repeat = "R"; //repeat infinitely

      if (repeatCount > -1) {
         repeat = repeat + repeatCount;
      }

      Date date = new Date(startTime);

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
      String dateString = format.format(date);

      return repeat + "/" + dateString + "/" + interval;
   }
}
