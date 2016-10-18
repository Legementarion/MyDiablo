package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lego.mydiablo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lego on 26.03.2016.
 */
public class SeasonAdapter  extends ArrayAdapter<String>{

    @BindView(R.id.spinnertext)
    TextView mLabel;
    private Context mContext;
    private String[] seasonList;

    public SeasonAdapter(Context context, int resource, String[] objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.seasonList = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        ButterKnife.bind(this,convertView);
        mLabel.setTextSize(20);
        mLabel.setGravity(Gravity.CENTER);
        mLabel.setTextColor(Color.BLACK);
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.spinner, null);
        }
        ButterKnife.bind(this,convertView);
        mLabel.setText(seasonList[position]);
        mLabel.setTextSize(20);
        mLabel.setTextColor(Color.WHITE);
        return convertView;
    }
}
