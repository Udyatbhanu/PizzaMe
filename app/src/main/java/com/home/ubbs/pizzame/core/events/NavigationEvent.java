package com.home.ubbs.pizzame.core.events;

import android.os.Parcelable;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class NavigationEvent {

    public  Class clazz;
    public  Parcelable data;



    public NavigationEvent(Class clazz, Parcelable data) {
        this.clazz = clazz;
        this.data = data;
    }



}
