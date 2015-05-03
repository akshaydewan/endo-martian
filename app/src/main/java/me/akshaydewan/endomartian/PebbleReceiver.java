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
    String msg_data = intent.getStringExtra("msg_data");
    int transactionId = intent.getIntExtra("transaction_id", 0);

    System.out.println("msg_data = " + msg_data);
    System.out.println("transactionId = " + transactionId);

    try {
      JSONArray msgDataJSON = new JSONArray(msg_data);
      for (int i = 0; i < msgDataJSON.length(); i++) {
        System.out.print("key= "+ msgDataJSON.getJSONObject(i).getInt("key"));
        System.out.print(", ");
        System.out.print("value= "+ msgDataJSON.getJSONObject(i).getString("value"));
        System.out.println();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    Intent ackIntent = new Intent(ACK);
    intent.putExtra("transaction_id", transactionId);
    context.sendBroadcast(ackIntent);
  }
}
