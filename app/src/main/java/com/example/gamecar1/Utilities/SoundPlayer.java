package com.example.gamecar1.Utilities;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.gamecar1.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SoundPlayer {

    private SoundPool soundPool;
    private int stoneCrashSound;
    private int coinCrashSound;

    public SoundPlayer(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        stoneCrashSound = soundPool.load(context, R.raw.stone_crash, 1);
        coinCrashSound = soundPool.load(context, R.raw.coin_crash, 1);
    }

    public void playStoneCrash() {
        soundPool.play(stoneCrashSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playCoinCrash() {
        soundPool.play(coinCrashSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
