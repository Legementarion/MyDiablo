package com.lego.mydiablo.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lego.mydiablo.utils.ImgUtils.pickImage;

/**
 * @author Lego on 26.03.2016.
 */
public class ClassAdapter extends ArrayAdapter<String> {

    @BindView(R.id.spinner_class_text)
    TextView mLabel;
    @BindView(R.id.spinner_class_imageView)
    ImageView mImageView;

    private Context mContext;
    private String[] classList;

    public ClassAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.classList = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (convertView == null) {
            rootView = View.inflate(mContext, R.layout.spinner_class_dropdown_item, null);
        }
        ButterKnife.bind(this, rootView);
        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/blizzard.ttf");
        mLabel.setTypeface(face);
//        mLabel.setTextColor(Color.BLACK);
//        mLabel.setBackgroundColor(Color.WHITE);
        mImageView.setImageDrawable(pickImage(mContext, classList[position]));
        return rootView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (convertView == null) {
            rootView = View.inflate(mContext, R.layout.spinner_class, null);
        }
        ButterKnife.bind(this, rootView);
        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/blizzard.ttf");
        mLabel.setTypeface(face);
        mLabel.setText(classList[position]);
        mLabel.setTextColor(Color.WHITE);
        mImageView.setImageDrawable(pickImage(mContext, classList[position]));
        return rootView;
    }
}
