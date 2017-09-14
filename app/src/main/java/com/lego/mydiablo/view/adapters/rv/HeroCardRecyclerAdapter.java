package com.lego.mydiablo.view.adapters.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.fragment.HeroTabsPresenter;
import com.lego.mydiablo.rest.callback.models.user.UserHero;
import com.lego.mydiablo.utils.HeroUtils;
import com.lego.mydiablo.utils.ImgUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroCardRecyclerAdapter
        extends RecyclerView.Adapter<HeroCardRecyclerAdapter.HeroViewHolder> {

    private List<UserHero> mHeroList;
    private HeroTabsPresenter mHeroTabsPresenter;
    private Context mContext;

    public HeroCardRecyclerAdapter(Context context, List<UserHero> userHeroes, HeroTabsPresenter userHeroPick) {
        mContext = context;
        mHeroList = userHeroes;
        mHeroTabsPresenter = userHeroPick;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_card, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        if (!mHeroList.isEmpty()) {
            UserHero hero = mHeroList.get(position);
            holder.mHeroName.setText(hero.getName());
            holder.mLevelView.setText(String.valueOf(hero.getLevel()));
            holder.mParagonView.setText(String.valueOf(hero.getParagon()));
            holder.mClassView.setText(hero.gethClass());
            holder.mImageClassView.setImageDrawable(ImgUtils.pickHeroIcon(mContext, hero.gethClass() + "_"
                    + HeroUtils.castGender(hero.getGender())));
            holder.mView.setOnClickListener(v ->
                    mHeroTabsPresenter.addTab(hero.getId())
            );
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
        @BindView(R.id.title)
        TextView mHeroName;
        @BindView(R.id.card_hero_class_value)
        TextView mClassView;
        @BindView(R.id.card_hero_paragon_value)
        TextView mParagonView;
        @BindView(R.id.card_hero_level_value)
        TextView mLevelView;
        @BindView(R.id.headerImage)
        ImageView mImageClassView;

        HeroViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mHeroName.getText() + "'";
        }
    }

}
