package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
public class ClassAdapter extends ArrayAdapter<String> {

    @BindView(R.id.spinnertext)
    TextView mLabel;

    private Context mContext;
    private String[] classList;

    public ClassAdapter(Context context, int resource, String[] objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.classList = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View rootView = super.getView(position, convertView, parent);
        ButterKnife.bind(this,rootView);
        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/blizzard.ttf");
        mLabel.setTypeface(face);
        mLabel.setTextSize(20);
        mLabel.setGravity(Gravity.CENTER);
        mLabel.setTextColor(Color.BLACK);
        return rootView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (convertView == null) {
            rootView = View.inflate(mContext, R.layout.spinner, null);
        }
        ButterKnife.bind(this,rootView);
        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/blizzard.ttf");
        mLabel.setTypeface(face);
        mLabel.setText(classList[position]);
        mLabel.setTextSize(20);
        mLabel.setTextColor(Color.WHITE);

        return rootView;
    }
}
