package com.example.gamecar1.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.gamecar1.MainActivity;
import com.example.gamecar1.MenuActivity;
import com.example.gamecar1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;


public class GameOverFragment extends Fragment {

    private MaterialTextView fragment_LBL_game_over;
    private MaterialButton fragment_BTN_restart;
    private MaterialButton fragment_BTN_exit;
    private AppCompatImageView game_over_IMG_background;


    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_over_fragment, container, false);

        findViews(view);
        initViews();

        return view;
    }


    private void findViews(View v) {
        fragment_BTN_restart = v.findViewById(R.id.fragment_BTN_restart);
        fragment_BTN_exit = v.findViewById(R.id.fragment_BTN_exit);
        game_over_IMG_background = v.findViewById(R.id.game_over_IMG_background);
    }

    private void initViews() {
        fragment_BTN_restart.setOnClickListener(v -> handleRestart());
        fragment_BTN_exit.setOnClickListener(v -> handleExit());
        Glide
                .with(this)
                .load("data:image/jpeg;base64,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")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(game_over_IMG_background);
    }

    private void handleRestart() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(GameOverFragment.this).commit();
            ((MainActivity) getActivity()).restartGame();
        }
    }

    private void handleExit() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    }
}