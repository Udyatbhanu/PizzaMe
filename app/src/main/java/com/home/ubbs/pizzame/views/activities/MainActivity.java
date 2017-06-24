package com.home.ubbs.pizzame.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.view.PermissionManagerActivity;
import com.home.ubbs.pizzame.databinding.MainActivityBinding;
import com.home.ubbs.pizzame.viewmodel.MainViewModel;

public class MainActivity extends PermissionManagerActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    private MainActivityBinding mainActivityBinding;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainViewModel = new MainViewModel(this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }



    @Override
    protected void onDestroy() {
        mainViewModel.destroy(); //not calling this will might cause a Context leak
        super.onDestroy();
    }










}
