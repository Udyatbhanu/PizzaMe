package com.home.ubbs.pizzame.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.view.BaseActivity;
import com.home.ubbs.pizzame.databinding.LicenseActivityBinding;

/**
 * Created by udyatbhanu-mac on 6/23/17.
 */

public class LicenseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LicenseActivityBinding licenseActivityBinding = DataBindingUtil.setContentView(this, R.layout.license_activity);
        setSupportActionBar(licenseActivityBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
