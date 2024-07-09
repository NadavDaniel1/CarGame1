package com.example.gamecar1.Logic;


import com.example.gamecar1.Model.Coin;
import com.example.gamecar1.Model.GameObject;
import com.example.gamecar1.Model.Stone;
import com.example.gamecar1.R;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final int ROWS = 6;
    private static final int COLS = 5;


    private static int[] objectRows = new int[]{
            0, 0, 0, 0, 0,
            1, 1, 1, 1, 1,
            2, 2, 2, 2, 2,
            3, 3, 3, 3, 3,
            4, 4, 4, 4, 4,
            5, 5, 5, 5, 5
    };

    private static int[] objectCols = new int[]{
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4
    };

    private static int[] stoneImages = new int[]{
            R.id.main_IMG_stone00, R.id.main_IMG_stone01, R.id.main_IMG_stone02, R.id.main_IMG_stone03, R.id.main_IMG_stone04,
            R.id.main_IMG_stone10, R.id.main_IMG_stone11, R.id.main_IMG_stone12, R.id.main_IMG_stone13, R.id.main_IMG_stone14,
            R.id.main_IMG_stone20, R.id.main_IMG_stone21, R.id.main_IMG_stone22, R.id.main_IMG_stone23, R.id.main_IMG_stone24,
            R.id.main_IMG_stone30, R.id.main_IMG_stone31, R.id.main_IMG_stone32, R.id.main_IMG_stone33, R.id.main_IMG_stone34,
            R.id.main_IMG_stone40, R.id.main_IMG_stone41, R.id.main_IMG_stone42, R.id.main_IMG_stone43, R.id.main_IMG_stone44,
            R.id.main_IMG_stone50, R.id.main_IMG_stone51, R.id.main_IMG_stone52, R.id.main_IMG_stone53, R.id.main_IMG_stone54,
    };

    private static int[] coinImages = new int[]{
            R.id.main_IMG_coin00, R.id.main_IMG_coin01, R.id.main_IMG_coin02, R.id.main_IMG_coin03, R.id.main_IMG_coin04,
            R.id.main_IMG_coin10, R.id.main_IMG_coin11, R.id.main_IMG_coin12, R.id.main_IMG_coin13, R.id.main_IMG_coin14,
            R.id.main_IMG_coin20, R.id.main_IMG_coin21, R.id.main_IMG_coin22, R.id.main_IMG_coin23, R.id.main_IMG_coin24,
            R.id.main_IMG_coin30, R.id.main_IMG_coin31, R.id.main_IMG_coin32, R.id.main_IMG_coin33, R.id.main_IMG_coin34,
            R.id.main_IMG_coin40, R.id.main_IMG_coin41, R.id.main_IMG_coin42, R.id.main_IMG_coin43, R.id.main_IMG_coin44,
            R.id.main_IMG_coin50, R.id.main_IMG_coin51, R.id.main_IMG_coin52, R.id.main_IMG_coin53, R.id.main_IMG_coin54,
    };

    private static <T extends GameObject> List<T> getGameObjects(int[] rows, int[] cols, int[] images, Class<T> classObj) {
        List<T> objects = new ArrayList<>();
        try {
            for (int i = 0; i < images.length; i++) {
                T obj = classObj.newInstance();
                obj.setRow(rows[i]).setCol(cols[i]).setImage(images[i]);
                objects.add(obj);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static List<Stone> getStones() {
        return getGameObjects(objectRows, objectCols, stoneImages, Stone.class);
    }

    public static List<Coin> getCoins() {
        return getGameObjects(objectRows, objectCols, coinImages, Coin.class);
    }

    private static <T extends GameObject> boolean[][] getVisibilityMatrix(List<T> objects, int rows, int cols) {
        boolean[][] visibilityMatrix = new boolean[rows][cols];
        int listIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visibilityMatrix[i][j] = objects.get(listIndex).isVisible();
                listIndex++;
            }
        }
        return visibilityMatrix;
    }

    public static boolean[][] getStoneVisibilityMatrix() {
        return getVisibilityMatrix(getStones(), ROWS, COLS);
    }

    public static boolean[][] getCoinVisibilityMatrix() {
        return getVisibilityMatrix(getCoins(), ROWS, COLS);
    }

}
