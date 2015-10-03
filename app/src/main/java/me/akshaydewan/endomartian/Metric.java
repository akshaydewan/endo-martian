package me.akshaydewan.endomartian;

public enum Metric {
  DISTANCE("Distance"),
  PACE("Pace");

  private final String name;

  public String getName() {
    return name;
  }

  Metric(String name) {
    this.name = name;
  }

  public static Metric withName(String notificationMetric) {
    for(Metric metric : Metric.values()) {
      if(metric.getName().equalsIgnoreCase(notificationMetric)) {
        return metric;
      }
    }
    return null;
  }
}
