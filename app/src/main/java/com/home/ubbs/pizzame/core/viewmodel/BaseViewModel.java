package com.home.ubbs.pizzame.core.viewmodel;

import android.databinding.BaseObservable;

import com.home.ubbs.pizzame.core.events.ErrorEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by udyatbhanu-mac on 6/24/17.
 */

public abstract class BaseViewModel extends BaseObservable {

    protected void sendErrorEvent(){
        EventBus.getDefault().post(new ErrorEvent());
    }
}
