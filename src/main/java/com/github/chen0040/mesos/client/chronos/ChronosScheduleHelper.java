package com.github.chen0040.mesos.client.chronos;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by xschen on 22/7/2016.
 */
public class ChronosScheduleHelper {

   private static final long ONE_MINUTE_IN_MILLIS = 60000;


   private static SimpleDateFormat format() {
      return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
   }


   private static String format(Date date) {
      return format().format(date);
   }


   public static String getChronosSchedule() {
      String repeat = "R1"; //no repeat
      String interval = "P3M"; // 3 month one time

      Calendar calendar = Calendar.getInstance();
      long t = calendar.getTimeInMillis();
      Date date = new Date(t + ONE_MINUTE_IN_MILLIS);

      String dateString = format(date);

      return repeat + "/" + dateString + "/" + interval;
   }


   public static String getChronosSchedule(boolean scheduleImmediate, long startTime, ScheduleInterval scheduleInterval) {

      String repeat = "R"; //repeat infinitely

      String interval = "PT24H"; //24 hours one time

      if (scheduleInterval == ScheduleInterval.Weekly) {
         interval = "PT168H"; // 1 week one time
      }
      else if (scheduleInterval == ScheduleInterval.Monthly) {
         interval = "P1M"; // 1 month one time
      }

      Date date;
      if (scheduleImmediate) {
         Calendar calendar = Calendar.getInstance();
         long t = calendar.getTimeInMillis();
         date = new Date(t + (ONE_MINUTE_IN_MILLIS / 6)); // (15 * ONE_MINUTE_IN_MILLIS));
      }
      else {
         date = new Date(startTime + ONE_MINUTE_IN_MILLIS);
      }

      String dateString = format(date);

      return repeat + "/" + dateString + "/" + interval;
   }

}
