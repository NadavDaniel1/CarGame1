package com.example.gamecar1.Model;


public class Stone {
    private int row;
    private int col;
    private int stoneImage;
    private boolean isVisible;

    public Stone() {
        isVisible = false;
    }


    public int getRow() {
        return row;
    }

    public Stone setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }

    public Stone setCol(int col) {
        this.col = col;
        return this;
    }

    public int getStoneImage() {
        return stoneImage;
    }

    public Stone setStoneImage(int stoneImage) {
        this.stoneImage = stoneImage;
        return this;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Stone setVisible(boolean visible) {
        isVisible = visible;
        return this;
    }
}
