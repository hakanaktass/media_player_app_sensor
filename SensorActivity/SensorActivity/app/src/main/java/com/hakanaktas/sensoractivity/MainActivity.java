package com.hakanaktas.sensoractivity;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.hakanaktas.sensoractivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public ActivityMainBinding binding;
    SensorManager sensorManager;
    Sensor lightSensor,accSensor;
    CountDownTimer sensorTimer;
    float lS,accSX,accSY,accSZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    @Override
    protected void onPause() {

        super.onPause();
        sensorManager.unregisterListener(this);
        sensorTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void myExit() {
        this.finish();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            lS = sensorEvent.values[0];
            binding.lightSensor.setText(String.valueOf(lS));
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER ){
            float [] values = sensorEvent.values;
            accSX = values[0];
            accSY = values[1];
            accSZ = values[2];

            binding.accSensorX.setText(String.valueOf(accSX));
            binding.accSensorY.setText(String.valueOf(accSY));
            binding.accSensorZ.setText(String.valueOf(accSZ));
            if((abs(accSX)<1.0 && abs(accSY) < 10.0 && abs(accSZ) < 1.0) && lS<30.0){
                Intent intent = new Intent("com.hakanaktas.mediaplayerproject.SEND_MESSAGE");
                intent.putExtra("Durum",1);
                sendBroadcast(intent);
            }
            else if(abs(accSX)<1.0 && abs(accSY) < 10.0 && abs(accSZ) < 1.0 && lS>30.0){
                Intent intent = new Intent("com.hakanaktas.mediaplayerproject.SEND_MESSAGE");
                intent.putExtra("Durum",2);
                sendBroadcast(intent);
            }
            else if ((abs(accSX)>1.0 || abs(accSY) > 10.0 || abs(accSZ) > 1.0) && lS<30.0){
                Intent intent = new Intent("com.hakanaktas.mediaplayerproject.SEND_MESSAGE");
                intent.putExtra("Durum",3);
                sendBroadcast(intent);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}