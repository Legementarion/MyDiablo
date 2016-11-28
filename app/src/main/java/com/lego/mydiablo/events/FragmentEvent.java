package com.lego.mydiablo.events;

import android.support.v4.app.Fragment;

public class FragmentEvent {

    private Fragment data;
    private String tag;

    public FragmentEvent(Fragment data, String tag){
        this.data = data;
        this.tag = tag;
    }

    public Fragment getData(){
        return data;
    }

    public String getTag(){
        return tag;
    }
}
