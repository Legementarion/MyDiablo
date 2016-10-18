package com.lego.mydiablo.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.paginate.recycler.LoadingListItemCreator;

public class PaginateLoadingListItemCreator implements LoadingListItemCreator {

    private RecyclerView mRecyclerView;
    private TableItemRecyclerViewAdapter mTableItemRecyclerViewAdapter;

    public PaginateLoadingListItemCreator(RecyclerView recyclerView, TableItemRecyclerViewAdapter tableItemRecyclerViewAdapter) {
        mRecyclerView = recyclerView;
        mTableItemRecyclerViewAdapter = tableItemRecyclerViewAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.paginate_loading_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.tvLoading.setText(String.format("Total Hero loaded: %d.\nLoading more...", mTableItemRecyclerViewAdapter.getItemCount()));

        // This is how you can make full span if you are using StaggeredGridLayoutManager
        if (mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }


    private class VH extends RecyclerView.ViewHolder {
        TextView tvLoading;

        VH(View itemView) {
            super(itemView);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading_text);

        }
    }
}