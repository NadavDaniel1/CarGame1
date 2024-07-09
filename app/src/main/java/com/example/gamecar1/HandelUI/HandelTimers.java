package com.example.gamecar1.HandelUI;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.gamecar1.MainActivity;
import com.example.gamecar1.Utilities.Utils;

import java.util.Timer;

public class HandelTimers {

    private boolean isFastMode;
    private boolean isSlowMode;
    private boolean isSensorMode;
    private MainActivity mainActivity;
    private Timer stoneCreationTimer;
    private Timer stoneMovementTimer;
    private Timer coinCreationTimer;
    private Timer coinMovementTimer;
    private int movementSpeedDelayTime = 750;
    private int createStoneDelayTime = 2250;
    private int createCoinDelayTime = 8000;

    public HandelTimers() {
    }

    public HandelTimers(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void initChosenModeFromUser(){

        // Retrieve the mode from the intent with default value as false
        Intent intent = mainActivity.getIntent();
        isFastMode = intent.getBooleanExtra("FAST_MODE", false); // Default is false
        isSlowMode = intent.getBooleanExtra("SLOW_MODE", false); // Default is false
        isSensorMode = intent.getBooleanExtra("SENSOR_MODE", false); // Default is false

        // Initialize the game based on the selected modes
        initializeGame();
    }

    private void initializeGame() {
        if (isFastMode) {
            // Set the game to fast mode
            movementSpeedDelayTime = 350;
            createStoneDelayTime = 1050;
            createCoinDelayTime = 4000;
        }
        if (isSlowMode) {
            // Set the game to slow mode
            movementSpeedDelayTime = 1000;
            createStoneDelayTime = 3000;
            createCoinDelayTime = 10000;
        }
        if (isSensorMode) {
            // Enable sensor mode
            mainActivity.getLeftBTN().setVisibility(View.GONE);
            mainActivity.getRightBTN().setVisibility(View.GONE);
        }
    }

    public void startTimers() {
        stoneCreationTimer = new Timer();
        stoneMovementTimer = new Timer();
        coinCreationTimer = new Timer();
        coinMovementTimer = new Timer();
    }

    public void stopTimers() {
        if (stoneCreationTimer != null) {
            stoneCreationTimer.cancel();
            stoneCreationTimer.purge();
        }
        if (stoneMovementTimer != null) {
            stoneMovementTimer.cancel();
            stoneMovementTimer.purge();
        }
        if (coinCreationTimer != null) {
            coinCreationTimer.cancel();
            coinCreationTimer.purge();
        }
        if (coinMovementTimer != null) {
            coinMovementTimer.cancel();
            coinMovementTimer.purge();
        }
    }

    public Timer getStoneCreationTimer() {
        return stoneCreationTimer;
    }

    public HandelTimers setStoneCreationTimer(Timer stoneCreationTimer) {
        this.stoneCreationTimer = stoneCreationTimer;
        return this;
    }

    public Timer getStoneMovementTimer() {
        return stoneMovementTimer;
    }

    public HandelTimers setStoneMovementTimer(Timer stoneMovementTimer) {
        this.stoneMovementTimer = stoneMovementTimer;
        return this;
    }

    public Timer getCoinCreationTimer() {
        return coinCreationTimer;
    }

    public HandelTimers setCoinCreationTimer(Timer coinCreationTimer) {
        this.coinCreationTimer = coinCreationTimer;
        return this;
    }

    public Timer getCoinMovementTimer() {
        return coinMovementTimer;
    }

    public HandelTimers setCoinMovementTimer(Timer coinMovementTimer) {
        this.coinMovementTimer = coinMovementTimer;
        return this;
    }

    public int getMovementSpeedDelayTime() {
        return movementSpeedDelayTime;
    }

    public HandelTimers setMovementSpeedDelayTime(int movementSpeedDelayTime) {
        this.movementSpeedDelayTime = movementSpeedDelayTime;
        return this;
    }

    public int getCreateStoneDelayTime() {
        return createStoneDelayTime;
    }

    public HandelTimers setCreateStoneDelayTime(int createStoneDelayTime) {
        this.createStoneDelayTime = createStoneDelayTime;
        return this;
    }

    public int getCreateCoinDelayTime() {
        return createCoinDelayTime;
    }

    public HandelTimers setCreateCoinDelayTime(int createCoinDelayTime) {
        this.createCoinDelayTime = createCoinDelayTime;
        return this;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public boolean isSensorMode() {
        return isSensorMode;
    }
}
