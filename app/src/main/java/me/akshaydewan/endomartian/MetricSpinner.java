package me.akshaydewan.endomartian;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MetricSpinner {

  private final Spinner spinner;

  public MetricSpinner(Spinner spinner) {
    this.spinner = spinner;
    ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(spinner.getContext(), R.array.metric, android.R.layout.simple_spinner_item);
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(spinnerAdapter);
  }


  public void setSelectedValue(final String notifyMetric) {
    String[] metrics = spinner.getResources().getStringArray(R.array.metric);
    int index = 0;
    for (String metric : metrics) {
      if(metric.equals(notifyMetric)) {
        spinner.setSelection(index);
        return;
      }
      index++;
    }
  }

  public String getSelectedValue() {
    return spinner.getSelectedItem().toString();
  }
}
