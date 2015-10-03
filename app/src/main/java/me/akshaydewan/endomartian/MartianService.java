package me.akshaydewan.endomartian;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MartianService extends Service {

  public static final String DISTANCE_EXTRA = "me.akshaydewan.endomartian.extras.distance";
  public static final String DISTANCE_UNIT_EXTRA = "me.akshaydewan.endomartian.extras.distanceUnit";
  public static final String PACE_EXTRA = "me.akshaydewan.endomartian.extras.pace";
  public static final String PACE_UNIT_EXTRA = "me.akshaydewan.endomartian.extras.paceUnit";
  public static final String RESET_EXTRA = "me.akshaydewan.endomartian.extras.reset";
  private float prevDistance = 0;

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if(intent == null)
      return super.onStartCommand(intent, flags, startId);

    boolean shouldReset = intent.getBooleanExtra(RESET_EXTRA, false);
    if (shouldReset) {
      reset();
    }
    float distance = intent.getFloatExtra(DISTANCE_EXTRA, 0);
    String distanceUnit = intent.getStringExtra(DISTANCE_UNIT_EXTRA);
    String pace = intent.getStringExtra(PACE_EXTRA);
    String paceUnit = intent.getStringExtra(PACE_UNIT_EXTRA);
    if (distance - prevDistance >= Configuration.getNotifyPerDistance(this)) {
      if(shouldDisplayDistance()) {
        createNotification(distance, distanceUnit);
      } else {
        createNotification(pace, paceUnit);
      }
      prevDistance = distance;
    }
    return super.onStartCommand(intent, flags, startId);
  }

  private boolean shouldDisplayDistance() {
    return Configuration.getNotifyMetric(this).equalsIgnoreCase(Metric.DISTANCE.getName());
  }

  private void reset() {
    this.prevDistance = 0;
  }

  private void createNotification(float metric, String metricUnit) {
    if(metric == (int)metric) {
      createNotification(String.format("%d", metric), metricUnit);
    } else {
      createNotification(String.format("%.2f", metric), metricUnit);
    }
  }

  private void createNotification(String metric, String metricUnit) {
    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("")
            .setContentText(metric + " " + metricUnit);
    ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(1, mBuilder.build());
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }


}
