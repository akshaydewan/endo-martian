package me.akshaydewan.endomartian;

public class Configuration {

  public static final float NOTIFY_PER_DISTANCE = 0.05f;

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
