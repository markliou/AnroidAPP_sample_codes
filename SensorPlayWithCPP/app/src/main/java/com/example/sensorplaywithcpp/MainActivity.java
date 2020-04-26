package com.example.sensorplaywithcpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    float x_lateral = 0;
    float y_longitudinal = 0;
    float z_vertical = 0;

    TextView number;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String stringFromJNIMarkMsg() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.cpp_info);
        //tv.setText(stringFromJNI());
        tv.setText(stringFromJNIMarkMsg());

        // show the sensors with sensor manager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : sensorList){
            Log.d("sensor", "Name=" + s.getName());
            Log.d("sensor", "Vendor=" + s.getVendor());
            Log.d("sensor", "Version=" + s.getVersion());
            Log.d("sensor", "MaximumRange=" + s.getMaximumRange());
            Log.d("sensor", "MinDelay=" + s.getMinDelay());
            Log.d("sensor", "Power=" + s.getPower());
            Log.d("sensor", "Type=" + s.getType());
        }

        // the lottery sample code
        number = findViewById(R.id.number);

    }
    final SensorEventListener myAccelerometerListener = new SensorEventListener() {
        //@Override
        public void onSensorChanged(SensorEvent event) {
            if (Math.abs(x_lateral - event.values[0]) > 1 ||
                Math.abs(y_longitudinal - event.values[1]) > 1 ||
                Math.abs(z_vertical - event.values[2]) > 1){
                try{
                    number.setText("" + (Math.round(Math.random() * 10000) % 100 + 1));
                }catch(Exception e){
                    Log.d("sensor", e.toString());
                }
            }
            x_lateral = event.values[0];
            y_longitudinal = event.values[1];
            z_vertical = event.values[2];
        }

        //@Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    public void onPause(){
        mSensorManager.unregisterListener(myAccelerometerListener);
        super.onPause();
    }

    public void onResume(){
        mSensorManager.registerListener(myAccelerometerListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }


}
