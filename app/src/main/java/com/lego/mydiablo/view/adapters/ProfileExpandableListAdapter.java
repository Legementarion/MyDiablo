package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mExpandableListTitle;
    private HashMap<String, List<String>> mExpandableListDetail;
    private List<String> mIcons = new ArrayList<>();

    public ProfileExpandableListAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail, List<String> icons) {
        this.mContext = context;
        this.mIcons = icons;
        this.mExpandableListTitle = expandableListTitle;
        this.mExpandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.mExpandableListDetail.get(this.mExpandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.profile_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.mExpandableListDetail.get(this.mExpandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.profile_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        ImageView listTitleIcon = (ImageView) convertView.findViewById(R.id.IconTitle);
        ProgressBar progressBar = null;
        if (convertView != null) {
            progressBar = (ProgressBar) convertView.findViewById(R.id.details_progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }
        listTitleTextView.setText(listTitle);
        ProgressBar finalProgressBar = progressBar;
        Picasso.with(mContext)
                .load(mIcons.get(listPosition))
//               .resize(128, 128)
//                .error(mContext.getResources().getDrawable(R.drawable.dh))
                .into(listTitleIcon, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (finalProgressBar != null) {
                            finalProgressBar.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onError() {

                    }
                });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
