package me.akshaydewan.endomartian;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MartianService extends Service {

  public static final String DISTANCE_EXTRA = "me.akshaydewan.endomartian.extras.distance";
  public static final String PACE_EXTRA = "me.akshaydewan.endomartian.extras.pace";
  public static final String PACE_UNIT_EXTRA = "me.akshaydewan.endomartian.extras.paceUnit";
  private float prevDistance = 0;

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    float distance = intent.getFloatExtra(DISTANCE_EXTRA, 0);
    String pace = intent.getStringExtra(PACE_EXTRA);
    String paceUnit = intent.getStringExtra(PACE_UNIT_EXTRA);
    if(distance - prevDistance >= Configuration.NOTIFY_PER_DISTANCE) {
      createNotification(pace, paceUnit);
      prevDistance = distance;
    }
    return super.onStartCommand(intent, flags, startId);
  }

  private void createNotification(String pace, String paceUnit) {
    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this)
            .setContentText(pace + " " + paceUnit);
    ((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).notify(1, mBuilder.build());
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }


}
