package com.example.gamecar1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecar1.Fragments.MapsFragment;
import com.example.gamecar1.Fragments.TopScoresFragment;
import com.example.gamecar1.Utilities.SharedPreferencesManagerV3;
import com.example.gamecar1.Utilities.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Top10Activity extends AppCompatActivity {
    //Save scores with gson
    Gson gson;
    //save the score in this list
    private List<Integer> topTen;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        initViews();
        saveTop10Scores();
        getTop10Scores();
    }



    private void initViews() {
        gson = new Gson();
        topTen = Utils.topTenScores;
        TopScoresFragment topScoresFragment = new TopScoresFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.top10_FRAME_list,topScoresFragment).commit();
        MapsFragment mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.top10_FRAME_map,mapsFragment).commit();
    }

    private void saveTop10Scores() {
        String topTenScoreListAsJson = gson.toJson(topTen);
        SharedPreferencesManagerV3.getInstance().putString("topTenScoreList", topTenScoreListAsJson);
    }

    private void getTop10Scores(){
        String topTenScoreListAsJson = SharedPreferencesManagerV3.getInstance().getString("topTenScoreList", "");
        if(!topTenScoreListAsJson.isEmpty()){
            Type type = new TypeToken<List<Integer>>() {}.getType();
            topTen = gson.fromJson(topTenScoreListAsJson, type);
        }
    }

}
