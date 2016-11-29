package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.presenter.fragment.ItemListPresenter;
import com.lego.mydiablo.view.fragments.HeroTabsFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

import static com.lego.mydiablo.utils.ImgUtils.castGender;
import static com.lego.mydiablo.utils.ImgUtils.pickHeroIcon;
import static com.lego.mydiablo.utils.Settings.mHeroId;
import static com.lego.mydiablo.view.fragments.ItemListFragment.TAG;

/**
 * @author Lego on 12.09.2016.
 */

public class TableItemRecyclerViewAdapter
        extends RecyclerView.Adapter<TableItemRecyclerViewAdapter.HeroViewHolder> {

    private List<Hero> mHeroList;
    private Context mContext;
    private ItemListPresenter mItemListPresenter;
    private Core mCore;
    private EventBus bus = EventBus.getDefault();

    public TableItemRecyclerViewAdapter(List<Hero> heroList, Context context, ItemListPresenter itemListPresenter) {
        mItemListPresenter = itemListPresenter;
        mHeroList = new ArrayList<>(heroList);
        mContext = context;
        mCore = Core.getInstance();
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, int position) {
        Hero hero = mHeroList.get(position);
        holder.mIdView.setText("#" + hero.getRank());
        holder.mContentView.setText(hero.getBattleTag());
        holder.mClassView.setImageDrawable(pickHeroIcon(mContext, hero.getHeroClass() + "_" + castGender(hero.getGender())));
        holder.mRankView.setText("Rift - " + hero.getRiftLevel());
        holder.mView.setTag(hero.getId());
        mHeroId = hero.getRank();
        holder.mView.setOnClickListener(v -> {
                    if (mCore.checkHeroData(hero.getRank())) {
                        bus.post(new FragmentEvent(HeroTabsFragment.newInstance(hero.getRank()), HeroTabsFragment.TAG)); //send to diablo activity
                    } else {
                        mCore.loadDetailHeroData(hero.getBattleTag(), hero.getId())
                                .doOnSubscribe(() -> mItemListPresenter.showProgressDialog())
                                .doAfterTerminate(() -> mItemListPresenter.hideProgressDialog())
                                .subscribe(new Subscriber<Hero>() {
                                    @Override
                                    public void onCompleted() {
                                        unsubscribe();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("Table adapter", "onError: " + e);
                                        Toast.makeText(mContext, "Cant Load  Hero Data ;(", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(Hero hero) {
                                        bus.post(new FragmentEvent(HeroTabsFragment.newInstance(hero.getRank()), HeroTabsFragment.TAG)); //send to diablo activity
                                    }
                                });
                    }
                }
        );
    }

    public void add(List<Hero> items) {
        int previousDataSize = mHeroList.size();
        if (!items.isEmpty()) {
            mHeroList.addAll(items);
        }
        notifyItemRangeChanged(previousDataSize, mHeroList.size());
    }

    public void setItems(List<Hero> heroList) {
        this.mHeroList = heroList;
        notifyDataSetChanged();
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
            Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                    "fonts/blizzard.ttf");
            mRankView.setTypeface(face);
            mContentView.setTypeface(face);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
