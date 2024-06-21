package com.example.gamecar1;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import com.example.gamecar1.Logic.DataManager;
import com.example.gamecar1.Logic.GameManager;
import com.example.gamecar1.Model.Stone;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    //Finals
    private static final int ROWS = 4;
    private static final int COLS = 3;

    //Images
    private AppCompatImageView main_BTN_left;
    private AppCompatImageView main_BTN_right;
    private AppCompatImageView[] main_IMG_hearts;
    private AppCompatImageView[][] stoneMatrix;
    private AppCompatImageView main_IMG_car;

    // Control the game
    private GameManager gameManager;
    private Timer stoneCreationTimer;
    private Timer stoneMovementTimer;

    // Resolutions and Positions
    private int screenHeight;
    private int screenWidth;
    private int laneWidth;
    private int laneHeight;
    private int currentLane = 1; // Start in the middle lane (0, 1, 2)
    private float[] lanes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        gameManager = new GameManager(this);
        startGame();
    }

    private void findViews() {
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_IMG_car = findViewById(R.id.main_IMG_car);

        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };

        stoneMatrix = new AppCompatImageView[ROWS][COLS];
        List<Stone> stones = DataManager.getStones();
        for (Stone stone : stones) {
            stoneMatrix[stone.getRow()][stone.getCol()] = findViewById(stone.getStoneImage());
        }

    }

    private void initViews() {
        getScreenResolution();
        main_BTN_left.setOnClickListener(view -> moveLeft());
        main_BTN_right.setOnClickListener(view -> moveRight());
        lanes = new float[]{laneWidth / 2f, laneWidth * 1.5f, laneWidth * 2.5f};
    }

    private void moveRight() {
        if (currentLane < 2) {
            currentLane++;
            updateCarPosition();
        }
    }

    private void moveLeft() {
        if (currentLane > 0) {
            currentLane--;
            updateCarPosition();
        }
    }

    public void getScreenResolution() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        laneWidth = screenWidth / COLS;
        laneHeight = screenHeight / ROWS;
    }

    private void startGame() {
        // Initialize and start the timer for creating new stones
        stoneCreationTimer = new Timer();
        stoneMovementTimer = new Timer();
        stoneCreationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    int lane = (int) (Math.random() * COLS);
                    gameManager.setStoneVisible(0, lane); // Make the new stone visible at the top row
                    updateStonePosition();
                    scheduleStoneMovement(lane); // Update stone movement down the rows
                });
            }
        }, 0, 3000); // Create a new stone every 3 seconds
    }

    private void scheduleStoneMovement( int col) {
        TimerTask moveTask = new TimerTask() {
            int row = 0;

            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (row < ROWS - 1) {
                        gameManager.setStoneUnVisible(row, col);
                        row++;
                        gameManager.setStoneVisible(row, col);
                    } else {
                        gameManager.setStoneUnVisible(row, col);
                        cancel();
                    }
                    updateStonePosition();
                    checkCollision(row,col);
                });
            }
        };
        stoneMovementTimer.schedule(moveTask, 300, 1000); // Move the stone down every second
    }

    public void updateStonePosition() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                stoneMatrix[i][j].setVisibility
                        (gameManager.getStoneVisibilityMatrix()[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }

    private void checkCollision(int row, int col) {
        // Check if android device is RTL or LTR
        boolean isRtl = getResources().getConfiguration().getLayoutDirection()
                == stoneMatrix[row][col].LAYOUT_DIRECTION_RTL;
        int adjustedCarCol = isRtl ? COLS - 1 - currentLane : currentLane;

        if (row == ROWS - 1 && col == adjustedCarCol && gameManager.isStoneVisible(row, col)) {
            // Collision detected
            gameManager.reduceLife();
            if (gameManager.getLives() == 0) {
                gameOver();
            } else {
                toast("Crash! " + gameManager.getLives() + " lives left.");
                vibrate();
            }
        }
    }
    public void updateCarPosition() {
        main_IMG_car.setX(lanes[currentLane] - main_IMG_car.getWidth() / 2f);
        main_IMG_car.setY(screenHeight - laneHeight); // Position the car at the bottom
    }

    public void updateHeartsUI(int lives) {
        for (int i = 0; i < main_IMG_hearts.length; i++) {
            main_IMG_hearts[i].setVisibility(i < lives ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void gameOver() {
        stoneCreationTimer.cancel();
        stoneMovementTimer.cancel();
        Intent intent = new Intent(this, GameOverActivity.class);
        startActivity(intent);
        finish();
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
    }
}

