package com.example.gamecar1;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.gamecar1.Fragments.GameOverFragment;
import com.example.gamecar1.HandelUI.HandelTimers;
import com.example.gamecar1.HandelUI.HandleCoins;
import com.example.gamecar1.HandelUI.HandleStones;
import com.example.gamecar1.Interfaces.MoveCallback;
import com.example.gamecar1.Logic.DataManager;
import com.example.gamecar1.Logic.GameManager;
import com.example.gamecar1.Model.Coin;
import com.example.gamecar1.Model.Stone;
import com.example.gamecar1.Utilities.MoveDetector;
import com.example.gamecar1.Utilities.SoundPlayer;
import com.example.gamecar1.Utilities.Utils;


import java.util.List;



public class MainActivity extends AppCompatActivity {

    //Images
    private AppCompatImageView main_BTN_left;
    private AppCompatImageView main_BTN_right;
    private AppCompatImageView[] main_IMG_hearts;
    private AppCompatImageView[][] stoneMatrix;
    private AppCompatImageView[][] coinMatrix;
    private AppCompatImageView main_IMG_car;
    private AppCompatImageView game_IMG_background;

    // Control the game
    private GameManager gameManager;
    private MoveDetector moveDetector;
    private SoundPlayer soundPlayer;
    private HandelTimers handelTimers;
    private HandleStones handleStones;
    private HandleCoins handleCoins;

    // Resolutions and Positions
    private int screenHeight;
    private int screenWidth;
    private int laneWidth;
    private int laneHeight;
    private int currentLane = 2; // Start in the middle lane (0, 1, 2, 3, 4)
    private float[] lanes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameManager = new GameManager(this);
        findViews();
        initViews();
        startGame();
    }

    private void findViews() {
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_IMG_car = findViewById(R.id.main_IMG_car);
        game_IMG_background = findViewById(R.id.game_IMG_background);

        findHeartsView();
        findStonesAndCoinsViews();
    }

    private void initViews() {
        initMainBackground();
        getScreenResolution();
        main_BTN_left.setOnClickListener(view -> moveLeft());
        main_BTN_right.setOnClickListener(view -> moveRight());
        handelTimers = new HandelTimers(this);
        handleStones = new HandleStones(this, handelTimers, gameManager);
        handleCoins = new HandleCoins(this, handelTimers, gameManager);
        lanes = new float[]{laneWidth / 2f, laneWidth * 1.5f, laneWidth * 2.5f, laneWidth * 3.5f, laneWidth * 4.5f};
        handelTimers.initChosenModeFromUser();
        setAndInitVolume();
        if(handelTimers.isSensorMode()) {
            initMoveDetector();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handelTimers.isSensorMode()) {
            moveDetector.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handelTimers.stopTimers();
        stopMoverDetector();

    }

    protected void onStop() {
        super.onStop();
        handelTimers.stopTimers();
        stopMoverDetector();
        soundPlayer.release();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restartGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handelTimers.stopTimers();
        stopMoverDetector();
        soundPlayer.release();
    }

    private void stopMoverDetector(){
        if (handelTimers.isSensorMode()) {
            moveDetector.stop();
        }
    }

    private void initMoveDetector() {
        moveDetector = new MoveDetector(this,
                new MoveCallback() {
                    @Override
                    public void moveXUP() {
                        moveLeft();
                    }

                    @Override
                    public void moveXDown() {
                        moveRight();
                    }

                    @Override
                    public void moveY() {
                    }
                }
        );
    }

    public void moveRight() {
        if (currentLane < 4) {
            currentLane++;
            gameManager.updateCarPosition();
        }
    }

    public void moveLeft() {
        if (currentLane > 0) {
            currentLane--;
            gameManager.updateCarPosition();
        }
    }

    public void getScreenResolution() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        laneWidth = screenWidth / Utils.COLS;
        laneHeight = screenHeight / Utils.ROWS;
    }

    private void startGame() {
        handelTimers.startTimers();
        handleStones.createAndMoveStones();
        handleCoins.createAndMoveCoins();
    }

    public void restartGame(){
        finish();
        startActivity(getIntent());
    }

    private void showGameOverFragment() {
        GameOverFragment gameOverFragment = new GameOverFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_FRAME_game_over, gameOverFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void gameOver() {
        handelTimers.stopTimers();
        stopMoverDetector();
        gameManager.resetAllObjectsVisibility(gameManager.getCoinVisibilityMatrix());
        gameManager.resetAllObjectsVisibility(gameManager.getStoneVisibilityMatrix());
        handleCoins.updateCoinPosition();
        handleStones.updateStonePosition();
        showGameOverFragment();
    }

    private void setAndInitVolume(){
        // Set media volume
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), 0);
        soundPlayer = new SoundPlayer(this);
    }

    public void initMainBackground(){
        Glide
                .with(this)
//                .load(R.drawable.colosseum)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpsme_9rKed5pd-U_vjEnA-qSwRxaQ5AUYNfnoX-1Jngx_IW0w9CvSNmhGyw&s")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(game_IMG_background);
    }

    private void findHeartsView(){
        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };
    }

    private void findStonesAndCoinsViews(){
        stoneMatrix = new AppCompatImageView[Utils.ROWS][Utils.COLS];
        List<Stone> stones = DataManager.getStones();
        for (Stone stone : stones) {
            stoneMatrix[stone.getRow()][stone.getCol()] = findViewById(stone.getImage());
        }

        coinMatrix = new AppCompatImageView[Utils.ROWS][Utils.COLS];
        List<Coin> coins = DataManager.getCoins();
        for (Coin coin : coins) {
            coinMatrix[coin.getRow()][coin.getCol()] = findViewById(coin.getImage());
        }
    }

    public AppCompatImageView getLeftBTN(){
        return main_BTN_left;
    }

    public AppCompatImageView getRightBTN(){
        return main_BTN_right;
    }

    public AppCompatImageView[][] getCoinMatrix() {
        return coinMatrix;
    }

    public AppCompatImageView[][] getStoneMatrix() {
        return stoneMatrix;
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public int getCurrentLane() {
        return currentLane;
    }

    public AppCompatImageView[] getMain_IMG_hearts() {
        return main_IMG_hearts;
    }

    public AppCompatImageView getMain_IMG_car() {
        return main_IMG_car;
    }

    public float[] getLanes() {
        return lanes;
    }
}

