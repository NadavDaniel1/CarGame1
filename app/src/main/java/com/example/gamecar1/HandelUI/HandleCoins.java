package com.example.gamecar1.HandelUI;

import android.view.View;

import com.example.gamecar1.Logic.GameManager;
import com.example.gamecar1.MainActivity;
import com.example.gamecar1.Utilities.SignalManager;
import com.example.gamecar1.Utilities.Utils;

import java.util.TimerTask;

public class HandleCoins {
    HandelTimers handelTimers;
    MainActivity mainActivity;
    GameManager gameManager;


    public HandleCoins() {

    }

    public HandleCoins(MainActivity mainActivity,HandelTimers handelTimers, GameManager gameManager){
        this.handelTimers = handelTimers;
        this.gameManager = gameManager;
        this.mainActivity = mainActivity;
    }

    public void createAndMoveCoins() {
        handelTimers.getCoinCreationTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                handelTimers.getMainActivity().runOnUiThread(() -> {
                    int lane = (int) (Math.random() * Utils.COLS);
                    while (gameManager.isStoneVisible(0, lane)) {
                        lane = (int) (Math.random() * Utils.COLS);
                    }
                    gameManager.setCoinVisible(0, lane); // Make the new coin visible at the top row
                    updateCoinPosition();
                    scheduleCoinMovement(lane); // Update coin movement down the rows
                    gameManager.addDistanceScore(); // Add 1 score every (createCoinDelayTime value)/1000 seconds
                });
            }
        }, 0, handelTimers.getCreateCoinDelayTime()); // Create a new coin every (createCoinDelayTime value)/1000 seconds
    }

    private void scheduleCoinMovement(int col) {
        TimerTask moveTask = new TimerTask() {
            int row = 0;

            @Override
            public void run() {
                mainActivity.runOnUiThread(() -> {
                    if (row < Utils.ROWS - 1) {
                        gameManager.setCoinUnVisible(row, col);
                        row++;
                        gameManager.setCoinVisible(row, col);
                    } else {
                        gameManager.setCoinUnVisible(row, col);
                        cancel();
                    }
                    updateCoinPosition();
                    checkCoinCollision(row, col);
                });
            }
        };
        handelTimers.getCoinMovementTimer().schedule(moveTask, 200, handelTimers.getMovementSpeedDelayTime()); // Move the stone down every second
    }

    public void updateCoinPosition() {
        for (int i = 0; i < Utils.ROWS; i++) {
            for (int j = 0; j < Utils.COLS; j++) {
                mainActivity.getCoinMatrix()[i][j].setVisibility
                        (gameManager.getCoinVisibilityMatrix()[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }

    private void checkCoinCollision(int row, int col) {
        // Check if android device is RTL or LTR
        boolean isRtl = mainActivity.getResources().getConfiguration().getLayoutDirection()
                == mainActivity.getCoinMatrix()[row][col].LAYOUT_DIRECTION_RTL;
        int adjustedCarCol = isRtl ? Utils.COLS - 1 - mainActivity.getCurrentLane() : mainActivity.getCurrentLane();

        if (row == Utils.ROWS - 1 && col == adjustedCarCol && gameManager.isCoinVisible(row, col)) {
            // Collision detected
            gameManager.addCoinScore();
            mainActivity.getSoundPlayer().playCoinCrash();
            SignalManager.getInstance().toast("Good Job!");
        }
    }






}
