package com.example.gamecar1.Model;

public class GameObject {
    protected int row;
    protected int col;
    protected int image;
    protected boolean isVisible;

    public GameObject() {
        isVisible = false;
    }


    public int getRow() {
        return row;
    }


    public GameObject setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }


    public GameObject setCol(int col) {
        this.col = col;
        return this;
    }

    public int getImage() {
        return image;
    }


    public GameObject setImage(int stoneImage) {
        this.image = stoneImage;
        return this;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public GameObject setVisible(boolean visible) {
        isVisible = visible;
        return this;
    }
}
