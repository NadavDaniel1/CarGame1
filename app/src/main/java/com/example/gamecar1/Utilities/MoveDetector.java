package com.example.gamecar1.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.gamecar1.Interfaces.MoveCallback;

public class MoveDetector {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private long timestamp = 0l;
    private MoveCallback moveCallback;

    public MoveDetector(Context context, MoveCallback moveCallback) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
        this.moveCallback = moveCallback;
        initEventListener();
    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float x = event.values[0];
                    float y = event.values[1];
                    calculateMove(x, y);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void calculateMove(float x, float y) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (x > 2.0) {
                if (moveCallback != null) {
                    moveCallback.moveXUP();
                }
            }
            if (x < -2.0) {
                if (moveCallback != null) {
                    moveCallback.moveXDown();
                }
            }
            if (y > 6.0) {
                if (moveCallback != null) {
                    moveCallback.moveY();
                }
            }
        }
    }

    public void start() {
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor);
    }
}


