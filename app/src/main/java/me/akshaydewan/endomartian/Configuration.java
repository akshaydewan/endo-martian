package me.akshaydewan.endomartian;

import android.content.Context;
import android.content.SharedPreferences;

public class Configuration {

  private static final String PREFS_NAME = "EndoMartianPrefs";
  private static final String NOTIFY_PER_DISTANCE_KEY = "NOTIFY_PER_DISTANCE";
  private static final String NOTIFY_METRIC_KEY = "NOTIFY_METRIC";

  public static float getNotifyPerDistance(Context context) {
    SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    return prefs.getFloat(NOTIFY_PER_DISTANCE_KEY, 0.5f);
  }

  public static String getNotifyMetric(Context context) {
    SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    return prefs.getString(NOTIFY_METRIC_KEY, Metric.PACE.getName());
  }

  public static boolean saveConfiguration(float notifyPerDistance, String notificationMetric, Context context) {
    if(notifyPerDistance <=0 ) {
      return false;
    }
    Metric metric = Metric.withName(notificationMetric);
    if(metric == null) {
      return false;
    }
    SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putFloat(NOTIFY_PER_DISTANCE_KEY, notifyPerDistance);
    editor.putString(NOTIFY_METRIC_KEY, metric.getName());
    editor.commit();
    return true;
  }

  public static final int ZONE_1_UNIT = 16;
  public static final int ZONE_2_UNIT = 17;
  public static final int ZONE_3_UNIT = 18;

  private static final int ZONE_1_VALUE = 11;
  public static final int ZONE_2_VALUE = 13;
  public static final int ZONE_3_VALUE = 15;

  public static int distanceKey() {
    return ZONE_3_VALUE;
  }

  public static int distanceUnitKey() {
    return ZONE_3_UNIT;
  }

  public static int paceKey() {
    return ZONE_2_VALUE;
  }

  public static int paceUnitKey() {
    return ZONE_2_UNIT;
  }

  public static int durationKey() {
    return ZONE_1_VALUE;
  }

  public static int durationUnitKey() {
    return ZONE_1_UNIT;
  }
}
