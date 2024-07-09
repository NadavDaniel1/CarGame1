package com.example.gamecar1.Logic;



import android.view.View;

import com.example.gamecar1.MainActivity;
import com.example.gamecar1.R;

import com.google.android.material.textview.MaterialTextView;


public class GameManager {
    private MainActivity mainActivity;
    private boolean[][] stoneVisibilityMatrix;
    private boolean[][] coinVisibilityMatrix;
    MaterialTextView main_LBL_score;
    private int lives;
    private int scores;



    public GameManager() {

    }

    public GameManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.lives = 3; // Initial lives
        this.stoneVisibilityMatrix = DataManager.getStoneVisibilityMatrix();
        this.coinVisibilityMatrix = DataManager.getCoinVisibilityMatrix();
        this.scores = 0;
        main_LBL_score = mainActivity.findViewById(R.id.main_LBL_score);
    }

    public void updateHeartsUI(int lives) {
        for (int i = 0; i < mainActivity.getMain_IMG_hearts().length; i++) {
            mainActivity.getMain_IMG_hearts()[i].setVisibility(i < lives ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void updateCarPosition() {
        mainActivity.getMain_IMG_car().setX(
                mainActivity.getLanes()[mainActivity.getCurrentLane()]
                        - mainActivity.getMain_IMG_car().getWidth() / 2f);
    }
    public void reduceLife() {
        lives--;
        updateHeartsUI(lives);
    }

    public int getLives() {
        return lives;
    }

    public void addCoinScore() {
        scores += 5;
        main_LBL_score.setText(String.valueOf(scores));
    }

    public void addDistanceScore() {
        scores++;
        main_LBL_score.setText(String.valueOf(scores));
    }

    public void resetAllObjectsVisibility(boolean[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = false;
            }
        }
    }

    public void setStoneVisible(int row, int col) {
        stoneVisibilityMatrix[row][col] = true;
    }

    public void setStoneUnVisible(int row, int col) {
        stoneVisibilityMatrix[row][col] = false;
    }

    public void setCoinVisible(int row, int col) {
        coinVisibilityMatrix[row][col] = true;
    }

    public void setCoinUnVisible(int row, int col) {
        coinVisibilityMatrix[row][col] = false;
    }

    public boolean[][] getStoneVisibilityMatrix() {
        return stoneVisibilityMatrix;
    }

    public boolean isStoneVisible(int row, int col){
        return stoneVisibilityMatrix[row][col];
    }

    public boolean[][] getCoinVisibilityMatrix() {
        return coinVisibilityMatrix;
    }

    public boolean isCoinVisible(int row, int col){
        return coinVisibilityMatrix[row][col];
    }
}
