package com.example.gamecar1.Logic;


import com.example.gamecar1.Model.Stone;
import com.example.gamecar1.R;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final int ROWS = 4;
    private static final int COLS = 3;


    private static int[] stoneRows = new int[]{
            0, 0, 0,
            1, 1, 1,
            2, 2, 2,
            3, 3, 3
    };

    private static int[] stoneImages = new int[]{
            R.id.main_IMG_stone00, R.id.main_IMG_stone01, R.id.main_IMG_stone02,
            R.id.main_IMG_stone10, R.id.main_IMG_stone11, R.id.main_IMG_stone12,
            R.id.main_IMG_stone20, R.id.main_IMG_stone21, R.id.main_IMG_stone22,
            R.id.main_IMG_stone30, R.id.main_IMG_stone31, R.id.main_IMG_stone32
    };

    private static int[] stoneCols = new int[]{
            0, 1, 2,
            0, 1, 2,
            0, 1, 2,
            0, 1, 2
    };


    public static List<Stone> getStones() {
        ArrayList<Stone> stones = new ArrayList<>();
        for (int i = 0; i < stoneImages.length; i++) {
            stones.add(
                    new Stone()
                            .setRow(stoneRows[i])
                            .setCol(stoneCols[i])
                            .setStoneImage(stoneImages[i])

            );
        }
        return stones;
    }

    public static boolean[][] getStoneVisibilityMatrix() {
        boolean[][] stoneVisibilityMatrix = new boolean[ROWS][COLS];
        int stoneListIndex = 0;
        for (int i = 0; i<ROWS; i++){
            for(int j = 0; j < COLS; j++){
                stoneVisibilityMatrix[i][j] = getStones().get(stoneListIndex).isVisible();
                stoneListIndex++;
            }
        }
        return stoneVisibilityMatrix;
    }
}
