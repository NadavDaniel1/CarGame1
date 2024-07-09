package com.example.gamecar1.HandelUI;

import android.view.View;

import com.example.gamecar1.Logic.GameManager;
import com.example.gamecar1.MainActivity;
import com.example.gamecar1.Utilities.SignalManager;
import com.example.gamecar1.Utilities.Utils;

import java.util.TimerTask;

public class HandleStones {

    HandelTimers handelTimers;
    MainActivity mainActivity;
    GameManager gameManager;


    public HandleStones() {

    }

    public HandleStones(MainActivity mainActivity, HandelTimers handelTimers, GameManager gameManager) {
        this.handelTimers = handelTimers;
        this.gameManager = gameManager;
        this.mainActivity = mainActivity;
    }



    public void createAndMoveStones() {
        handelTimers.getStoneCreationTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                mainActivity.runOnUiThread(() -> {
                    int lane = (int) (Math.random() * Utils.COLS);
                    while (gameManager.isCoinVisible(0, lane)) {
                        lane = (int) (Math.random() * Utils.COLS);
                    }
                    gameManager.setStoneVisible(0, lane); // Make the new stone visible at the top row
                    updateStonePosition();
                    scheduleStoneMovement(lane); // Update stone movement down the rows
                });
            }
        }, 0, handelTimers.getCreateStoneDelayTime()); // Create a new stone every 3 seconds
    }
    private void scheduleStoneMovement(int col) {
        TimerTask moveTask = new TimerTask() {
            int row = 0;

            @Override
            public void run() {
                mainActivity.runOnUiThread(() -> {
                    if (row < Utils.ROWS - 1) {
                        gameManager.setStoneUnVisible(row, col);
                        row++;
                        gameManager.setStoneVisible(row, col);
                    } else {
                        gameManager.setStoneUnVisible(row, col);
                        cancel();
                    }
                    updateStonePosition();
                    checkStoneCollision(row, col);
                });
            }
        };
        handelTimers.getStoneMovementTimer().schedule(moveTask, 200, handelTimers.getMovementSpeedDelayTime()); // Move the stone down every second
    }
    public void updateStonePosition() {
        for (int i = 0; i < Utils.ROWS; i++) {
            for (int j = 0; j < Utils.COLS; j++) {
                mainActivity.getStoneMatrix()[i][j].setVisibility
                        (gameManager.getStoneVisibilityMatrix()[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }
    private void checkStoneCollision(int row, int col) {
        // Check if android device is RTL or LTR
        boolean isRtl = mainActivity.getResources().getConfiguration().getLayoutDirection()
                == mainActivity.getStoneMatrix()[row][col].LAYOUT_DIRECTION_RTL;
        int adjustedCarCol = isRtl ? Utils.COLS - 1 - mainActivity.getCurrentLane() : mainActivity.getCurrentLane();

        if (row == Utils.ROWS - 1 && col == adjustedCarCol && gameManager.isStoneVisible(row, col)) {
            // Collision detected
            gameManager.reduceLife();
            if (gameManager.getLives() == 0) {
                mainActivity.gameOver();
            } else {
                SignalManager.getInstance().toast("Crash! " + gameManager.getLives() + " lives left.");
                SignalManager.getInstance().vibrate(500);
                mainActivity.getSoundPlayer().playStoneCrash();
            }
        }
    }
}
