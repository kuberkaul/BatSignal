package com.example.s2aas;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SensorActivity extends Activity implements SensorEventListener {
  private SensorManager sensorManager;
  private boolean color = false;
  private View view;
  private long lastUpdate;

  
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sensor);
    view = findViewById(R.id.textView1);

    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    lastUpdate = System.currentTimeMillis();
    Log.i("SensorActivity", "onCreate called...");
    //some code left here - harshil
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
    	getAccelerationSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
    	getTemperatureSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
    	getGravitySense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
    	getGyroscopeSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
    	getLightSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
    	getMagneticFieldSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
    	getPressureSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
    	getProximitySense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
    	getLinearAccelerationSense(event);
    }
    if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
    	getRotationVectorSense(event);
    }
  }

  private void getRotationVectorSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getLinearAccelerationSense(SensorEvent event) {
	// TODO Auto-generated method stub
}

private void getProximitySense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getPressureSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getMagneticFieldSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getLightSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getGyroscopeSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getGravitySense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getTemperatureSense(SensorEvent event) {
	// TODO Auto-generated method stub
	
}

private void getAccelerationSense(SensorEvent event) {
    float[] values = event.values;
    // Movement
    float x = values[0];
    float y = values[1];
    float z = values[2];

    float accelationSquareRoot = (x * x + y * y + z * z)
        / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
    long actualTime = System.currentTimeMillis();
    if (accelationSquareRoot >= 2) //
    {
      if (actualTime - lastUpdate < 200) {
        return;
      }
      lastUpdate = actualTime;
      Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
          .show();
      if (color) {
        view.setBackgroundColor(Color.GREEN);

      } else {
        view.setBackgroundColor(Color.RED);
      }
      color = !color;
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  @Override
  protected void onResume() {
    super.onResume();
    // register this class as a listener for the orientation and
    // accelerometer sensors
    sensorManager.registerListener(this,
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    // unregister listener
    super.onPause();
    sensorManager.unregisterListener(this);
  }
}