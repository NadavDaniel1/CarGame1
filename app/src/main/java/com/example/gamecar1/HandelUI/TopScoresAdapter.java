package com.example.gamecar1.HandelUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecar1.Fragments.MapsFragment;
import com.example.gamecar1.R;

import java.util.List;

public class TopScoresAdapter extends RecyclerView.Adapter<TopScoresAdapter.ViewHolder> {

    private List<Integer> topScores;

    public TopScoresAdapter(List<Integer> topScores) {
        this.topScores = topScores;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int score = topScores.get(position);
        holder.scoreTextView.setText( "score: " + score);
        holder.rankTextView.setText(String.format("%d.", position + 1));
        if (position == 0) {
            holder.iconImageView.setImageResource(R.drawable.first); // Add your gold medal icon in drawable
            holder.iconImageView.setVisibility(View.VISIBLE);
        } else if (position == 1) {
            holder.iconImageView.setImageResource(R.drawable.second); // Add your silver medal icon in drawable
            holder.iconImageView.setVisibility(View.VISIBLE);
        } else if (position == 2) {
            holder.iconImageView.setImageResource(R.drawable.third); // Add your bronze medal icon in drawable
            holder.iconImageView.setVisibility(View.VISIBLE);
        } else {
            holder.iconImageView.setVisibility(View.GONE);

            holder.scoreTextView.setOnClickListener(v -> new MapsFragment().findLocationDevice());
        }
    }

    @Override
    public int getItemCount() {
        return topScores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView scoreTextView;
        public TextView rankTextView;
        public ImageView iconImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            scoreTextView = itemView.findViewById(R.id.score_text_view);
            rankTextView = itemView.findViewById(R.id.rank_text_view);
            iconImageView = itemView.findViewById(R.id.icon_image_view);
        }
    }
}
