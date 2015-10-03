package me.akshaydewan.endomartian;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

  private MetricSpinner metricSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initSpinner();
  }

  private void initSpinner() {
    Spinner spinner = (Spinner) findViewById(R.id.metric_spinner);
    metricSpinner = new MetricSpinner(spinner);
  }

  @Override
  protected void onResume() {
    super.onResume();
    EditText textBox = (EditText) findViewById(R.id.notify_per_distance);
    textBox.setText(String.valueOf(Configuration.getNotifyPerDistance(this)));

    metricSpinner.setSelectedValue(Configuration.getNotifyMetric(this));
  }

  public void saveConfiguration(View view) {
    EditText textBox = (EditText) findViewById(R.id.notify_per_distance);
    hideKeyboard(textBox);
    String notifyPerDistString = textBox.getText().toString();
    float notifyPerDistanceVal = 0;
    try {
      notifyPerDistanceVal = Float.parseFloat(notifyPerDistString);
    } catch (NumberFormatException e) {
      showInvalidValueToast();
      return;
    }
    if(notifyPerDistanceVal > 0 &&
        Configuration.saveConfiguration(notifyPerDistanceVal, metricSpinner.getSelectedValue(), this)) {
      Toast toast = Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT);
      toast.show();
      return;
    }
    showInvalidValueToast();
  }

  private void hideKeyboard(EditText textBox) {
    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(textBox.getWindowToken(), 0);
  }

  private void showInvalidValueToast() {
    Toast toast = Toast.makeText(this, "Invalid value", Toast.LENGTH_SHORT);
    toast.show();
  }
}
