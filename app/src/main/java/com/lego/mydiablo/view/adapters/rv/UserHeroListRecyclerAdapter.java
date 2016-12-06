package com.lego.mydiablo.view.adapters.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.lego.mydiablo.dialog.UserHeroPick;
import com.lego.mydiablo.rest.callback.models.user.UserHero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lego.mydiablo.utils.ImgUtils.pickImageDash;

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
            holder.mContentView.setText(hero.getName());
            holder.mRankView.setText(hero.gethClass());
            holder.mClassView.setImageDrawable(pickImageDash(mContext, hero.gethClass()));
            holder.mView.setOnClickListener(v ->
                    mUserHeroPick.pickHero(hero.getId())
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
