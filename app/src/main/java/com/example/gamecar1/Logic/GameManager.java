package com.example.gamecar1.Logic;



import com.example.gamecar1.MainActivity;


public class GameManager {
    private MainActivity mainActivity;
    private int lives;
    private boolean[][] stoneVisibilityMatrix;

    public GameManager() {

    }

    public GameManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.lives = 3; // Initial lives
        this.stoneVisibilityMatrix = DataManager.getStoneVisibilityMatrix();
    }


        public void setStoneVisible(int row, int col) {
        stoneVisibilityMatrix[row][col] = true;
        }

    public void setStoneUnVisible(int row, int col) {
        stoneVisibilityMatrix[row][col] = false;
    }

    public void reduceLife() {
        lives--;
        mainActivity.updateHeartsUI(lives);
    }

    public int getLives() {
        return lives;
    }

    public boolean[][] getStoneVisibilityMatrix() {
        return stoneVisibilityMatrix;
    }

    public boolean isStoneVisible(int row, int col){
        return stoneVisibilityMatrix[row][col];
    }

}
