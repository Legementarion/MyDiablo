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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lego.mydiablo.utils.ImgUtils.pickImageDash;

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
            holder.mContentView.setText(hero.getName());
//            holder.mRankView.setText(hero.gethClass());
//            holder.mClassView.setImageDrawable(pickImageDash(mContext, hero.gethClass()));
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
//        @BindView(R.id.id)
//        TextView mIdView;
        @BindView(R.id.title)
        TextView mContentView;
//        @BindView(R.id.rank)
//        TextView mRankView;
//        @BindView(R.id.idClass)
//        ImageView mClassView;

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
