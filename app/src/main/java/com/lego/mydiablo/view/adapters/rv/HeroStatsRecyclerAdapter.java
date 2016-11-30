package com.lego.mydiablo.view.adapters.rv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lego.mydiablo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroStatsRecyclerAdapter extends RecyclerView.Adapter<HeroStatsRecyclerAdapter.HeroStatsViewHolder> {

    private List<String> stats;
    private Context mContext;

    public HeroStatsRecyclerAdapter(List<String> statsList, Context context) {
        stats = statsList;
        mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public HeroStatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_list_content, parent, false);
        return new HeroStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HeroStatsViewHolder holder, int position) {
        holder.mTextView.setText(" " +stats.get(position));
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    class HeroStatsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stat_text)
        TextView mTextView;

        HeroStatsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                    "fonts/blizzard.ttf");
            mTextView.setTypeface(face);
            mTextView.setTextSize(20);
            mTextView.setTextColor(Color.WHITE);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText() + "'";
        }
    }
}
