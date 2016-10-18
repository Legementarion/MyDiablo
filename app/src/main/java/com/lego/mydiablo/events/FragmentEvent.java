package com.lego.mydiablo.events;


import android.support.v4.app.Fragment;

public class FragmentEvent {

    private Fragment data;

    public FragmentEvent(Fragment data){
        this.data = data;
    }

    public Fragment getData(){
        return data;
    }
}
