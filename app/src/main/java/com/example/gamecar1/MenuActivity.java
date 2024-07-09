package com.example.gamecar1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.gamecar1.Utilities.SignalManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MenuActivity extends AppCompatActivity {

    private String mode;
    private String speed;
    private boolean isFastMode = false;
    private boolean isSlowMode = false;
    private boolean isSensorMode = false;


    private AppCompatImageView menu_IMG_background;
    private MaterialButton menu_BTN_play;
    private MaterialButton menu_BTN_fast;
    private MaterialButton menu_BTN_slow;
    private MaterialButton menu_BTN_sensor;
    private ExtendedFloatingActionButton menu_BTN_top10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        initViews();
    }

    private void findViews() {
        menu_BTN_play = findViewById(R.id.menu_BTN_play);
        menu_BTN_fast = findViewById(R.id.menu_BTN_fast);
        menu_BTN_slow = findViewById(R.id.menu_BTN_slow);
        menu_BTN_sensor = findViewById(R.id.menu_BTN_sensor);
        menu_BTN_top10 = findViewById(R.id.menu_BTN_top10);
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
    }

    private void initViews() {
        menu_BTN_play.setOnClickListener(v -> changeActivity());
        menu_BTN_fast.setOnClickListener(v -> toggleFastMode());
        menu_BTN_slow.setOnClickListener(v -> toggleSlowMode());
        menu_BTN_sensor.setOnClickListener(v -> toggleSensorMode());

        Glide
                .with(this)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDeEjVBTxcIYcEHPOxyPt2P7_nhvBSVZHTSg&s")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(menu_IMG_background);
        updatePlayButtonState();
    }

    private void changeActivity() {
            Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FAST_MODE", isFastMode);
        intent.putExtra("SLOW_MODE", isSlowMode);
        intent.putExtra("SENSOR_MODE", isSensorMode);
        startActivity(intent);
    }

    private void toggleFastMode() {
        if (isSlowMode) {
            isSlowMode = false;
            updateButtonAppearance(menu_BTN_slow, false);
        }
        isFastMode = !isFastMode;
        updateButtonAppearance(menu_BTN_fast, isFastMode);
    }

    private void toggleSlowMode() {
        if (isFastMode) {
            isFastMode = false;
            updateButtonAppearance(menu_BTN_fast, false);
        }
        isSlowMode = !isSlowMode;
        updateButtonAppearance(menu_BTN_slow, isSlowMode);
    }

    private void toggleSensorMode() {
        isSensorMode = !isSensorMode;
        updateButtonAppearance(menu_BTN_sensor, isSensorMode);
    }

    private void updateButtonAppearance(MaterialButton button, boolean isSelected) {
        if (isSelected) {
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_button_color));
        } else if(button == menu_BTN_slow && !isSelected) {
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
        }else if(button == menu_BTN_fast && !isSelected){
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
        }
        else
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_400));

        updatePlayButtonState();
    }

    private void updatePlayButtonState() {
        menu_BTN_play.setEnabled(isFastMode || isSlowMode || isSensorMode);
        if(menu_BTN_play.isEnabled())
            menu_BTN_play.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        else
            menu_BTN_play.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_400));
    }


}