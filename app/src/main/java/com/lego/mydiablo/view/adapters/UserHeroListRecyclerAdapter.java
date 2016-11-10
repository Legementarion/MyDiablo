package com.lego.mydiablo.view.adapters;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.lego.mydiablo.dialog.UserHeroPick;
import com.lego.mydiablo.rest.callback.models.UserData.UserHero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserHeroListRecyclerAdapter
        extends RecyclerView.Adapter<UserHeroListRecyclerAdapter.HeroViewHolder> {

    private List<UserHero> mHeroList;
    private UserHeroPick mUserHeroPick;
    private Context mContext;

    public UserHeroListRecyclerAdapter(Context context, List<UserHero> userHeroes, UserHeroPick userHeroPick) {
        mContext = context;
        mUserHeroPick = userHeroPick;
        mHeroList = userHeroes;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        if (!mHeroList.isEmpty()) {
            UserHero hero = mHeroList.get(position);
            holder.mIdView.setText("id " + hero.getId());
            holder.mContentView.setText("class " + hero.getHeroClass());
//            holder.mClassView.setImageDrawable(pickImage(hero.getHeroClass()));
            holder.mRankView.setText("gender " + hero.getGender());
            holder.mView.setTag(hero.getId());
            holder.mView.setOnClickListener(v -> {
                        Log.d("AAAAAAAAAA", "onBindViewHolder: " + hero.getId());
                        mUserHeroPick.pickHero(hero.getId());
                    }
            );
        }
    }

    private Drawable pickImage(String s) {
        switch (s) {
            case "demon hunter":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier("dh", "drawable", mContext.getPackageName()));
            case "witch doctor":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier("wd", "drawable", mContext.getPackageName()));
            default:
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName()));
        }
    }

    public void setItems(List<UserHero> items) {
        if (!items.isEmpty()) {
            this.mHeroList = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;
        @BindView(R.id.rank)
        TextView mRankView;
        @BindView(R.id.idClass)
        ImageView mClassView;

        HeroViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
