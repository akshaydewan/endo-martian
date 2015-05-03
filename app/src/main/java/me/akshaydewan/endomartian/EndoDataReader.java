package me.akshaydewan.endomartian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EndoDataReader implements EndoData {

  private float distance;
  private String distanceUnit;
  private String pace;
  private String paceUnit;


  public EndoDataReader(String msgData) throws JSONException {
    JSONArray msgDataJSON = new JSONArray(msgData);
    for (int i = 0; i < msgDataJSON.length(); i++) {
      JSONObject data = msgDataJSON.getJSONObject(i);
      setData(data);
    }
  }

  private void setData(JSONObject data) throws JSONException {
    int key = data.getInt("key");
    if (key == Configuration.distanceKey()) {
      this.distance = parseFloat(data.getString("value"));
    } else if (key == Configuration.paceKey()) {
      this.pace = data.getString("value");
    } else if (key == Configuration.distanceUnitKey()) {
      this.distanceUnit = data.getString("value");
    } else if (key == Configuration.paceUnitKey()) {
      this.paceUnit = data.getString("value");
    }
  }

  private float parseFloat(String value) {
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      return 0;
    }
  }


  @Override
  public float getCurrentDistance() {
    return this.distance;
  }

  @Override
  public String getDistanceUnit() {
    return this.distanceUnit;
  }

  @Override
  public String getCurrentPace() {
    return this.pace;
  }

  @Override
  public String getPaceUnit() {
    return this.paceUnit;
  }
}
