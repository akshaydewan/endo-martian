package me.akshaydewan.endomartian;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;

public class PebbleReceiver extends BroadcastReceiver {

  private static final String ACK = "com.getpebble.action.app.RECEIVE_ACK";

  public PebbleReceiver() {
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String msgData = intent.getStringExtra("msg_data");
    int transactionId = intent.getIntExtra("transaction_id", 0);

    try {
      EndoDataReader dataReader = new EndoDataReader(msgData);
      Intent notifyIntent = new Intent(context, MartianService.class);
      notifyIntent.putExtra(MartianService.RESET_EXTRA, isFirstTransaction(transactionId));
      notifyIntent.putExtra(MartianService.DISTANCE_EXTRA, dataReader.getCurrentDistance());
      notifyIntent.putExtra(MartianService.DISTANCE_UNIT_EXTRA, dataReader.getDistanceUnit());
      notifyIntent.putExtra(MartianService.PACE_EXTRA, dataReader.getCurrentPace());
      notifyIntent.putExtra(MartianService.PACE_UNIT_EXTRA, dataReader.getPaceUnit());
      context.startService(notifyIntent);
    } catch (JSONException e) {
      //TODO log
      e.printStackTrace();
    }

    Intent ackIntent = new Intent(ACK);
    intent.putExtra("transaction_id", transactionId);
    context.sendBroadcast(ackIntent);
  }

  private boolean isFirstTransaction(int transactionId) {
    return transactionId == 0;
  }
}
