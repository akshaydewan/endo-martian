package me.akshaydewan.endomartian;


import org.json.JSONException;
import org.json.JSONObject;

public class MsgData {

  private String value;
  private int key;

  public MsgData(int key, String value) {
    this.value = value;
    this.key = key;
  }

  public MsgData(JSONObject msgData) {
    try {
      this.key = msgData.getInt("key");
      this.value = msgData.getString("value");
    } catch (JSONException e) {
    }
  }

}
