package com.lego.mydiablo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lego.mydiablo.R;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.rest.callback.models.UserData.UserHero;
import com.lego.mydiablo.rest.callback.models.UserData.UserHeroList;
import com.lego.mydiablo.view.adapters.UserHeroListRecyclerAdapter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class PickDialog extends DialogFragment implements UserHeroPick {

    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.user_hero_list_progress_loading)
    LinearLayout mLinearLayout;
    private Unbinder mUnbinder;
    private Core mCore;
    private UserHeroListRecyclerAdapter mUserHeroListRecyclerAdapter;

    //тэг для передачи результата обратно
    public static final String TAG_PICK_SELECTED = "pick";


    public PickDialog() {
        //do nothing
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_dialog, container);
        mUnbinder = ButterKnife.bind(this, rootView);
        mCore = Core.getInstance();
        init();
        return rootView;
    }

    private void init() {
        mUserHeroListRecyclerAdapter = new UserHeroListRecyclerAdapter(getContext(), Collections.EMPTY_LIST, this);
        mRecyclerView.setAdapter(mUserHeroListRecyclerAdapter);
        getDialog().setTitle("With whom to compare?");
        mCore.loadUserHeroList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> mLinearLayout.setVisibility(View.VISIBLE))
                .doAfterTerminate(() -> mLinearLayout.setVisibility(View.GONE))
                .subscribe(new Subscriber<UserHeroList>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("loadUserHeroList", "onError: " + e);
                    }

                    @Override
                    public void onNext(UserHeroList heroList) {
                        updateUserList(heroList.getHeroes());
                    }
                });
    }

    private void updateUserList(List<UserHero> userHeroes) {
        mUserHeroListRecyclerAdapter.setItems(userHeroes);
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void pickHero(int userHeroId) {
        //отправляем результат обратно
        Intent intent = new Intent();
        intent.putExtra(TAG_PICK_SELECTED, userHeroId);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }
}