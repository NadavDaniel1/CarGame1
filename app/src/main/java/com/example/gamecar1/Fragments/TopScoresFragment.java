package com.example.gamecar1.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gamecar1.HandelUI.TopScoresAdapter;
import com.example.gamecar1.MenuActivity;
import com.example.gamecar1.R;
import com.example.gamecar1.Utilities.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class TopScoresFragment extends Fragment {

    List<Integer> topScoreList;
    private RecyclerView recyclerView;
    private TopScoresAdapter adapter;
    FloatingActionButton top_score_FAB_back;


    // Callback_ListItemClicked callbackListItemClicked;

    public TopScoresFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_scores, container, false);

        findViews(v);
        initViews();
        showTop10(v);
        return v;
    }

    private void findViews(View v) {
        recyclerView = v.findViewById(R.id.top_scores_recycler_view);
        top_score_FAB_back = v.findViewById(R.id.top_score_FAB_back);

    }

    private void initViews() {
        topScoreList = Utils.topTenScores;
        top_score_FAB_back.setOnClickListener(v -> backToMenu());
    }

    private void backToMenu() {
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
    }

    private void showTop10(View v) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topScoreList = Utils.topTenScores; // Assuming Utils.topTenScores is a static list of top scores
        adapter = new TopScoresAdapter(topScoreList);
        recyclerView.setAdapter(adapter);

    }
}