package com.example.gamecar1.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    // Finals
    public static final int ROWS = 6;
    public static final int COLS = 5;

    public static List<Integer> topTenScores = new ArrayList<>();

    public static void addScore(int score) {
        topTenScores.add(score);
        Collections.sort(topTenScores, Collections.reverseOrder());
        if (topTenScores.size() > 10) {
            topTenScores = topTenScores.subList(0, 10);
        }
    }
}
