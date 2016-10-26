package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.view.fragments.HeroTabsFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Lego on 12.09.2016.
 */

public class TableItemRecyclerViewAdapter
        extends RecyclerView.Adapter<TableItemRecyclerViewAdapter.ViewHolder> {

    private List<Hero> mHeroList;
    private Context mContext;
    private EventBus bus = EventBus.getDefault();
    private FragmentEvent event;

    public TableItemRecyclerViewAdapter(List<Hero> heroList, Context context) {
        mHeroList = heroList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Hero hero = mHeroList.get(position);
        holder.mIdView.setText("#" + hero.getmRank());
        holder.mContentView.setText(hero.getmBattleTag());
        holder.mClassView.setImageDrawable(pickImageClass(hero.getmClass()));
        holder.mRankView.setText("Rift - " + hero.getmRiftLevel());
        holder.mView.setTag(hero.getId());
        holder.mView.setOnClickListener(v -> {
            event = new FragmentEvent(new HeroTabsFragment());
            bus.post(event);    //send to diablo activity
        });
    }

    public void add(List<Hero> items) {
        int previousDataSize = this.mHeroList.size();
        if (items.size()>0) {
            this.mHeroList.addAll(items);
            notifyItemRangeInserted(previousDataSize, items.size());
        }
    }

    private Drawable pickImageClass(String s) {
        switch (s) {
            case "barbarian":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName()));
            case "demon hunter":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier("dh", "drawable", mContext.getPackageName()));
            case "witch doctor":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier("wd", "drawable", mContext.getPackageName()));
            case "crusader":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName()));
            case "monk":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName()));
            case "wizard":
                return mContext.getResources().getDrawable(mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName()));
            default:
                return null;
        }
    }

    public void setItems(List<Hero> heroList) {
        this.mHeroList = heroList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;
        @BindView(R.id.rank)
        TextView mRankView;
        @BindView(R.id.idClass)
        ImageView mClassView;

        ViewHolder(View view) {
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
