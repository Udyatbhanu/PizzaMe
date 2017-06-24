package com.home.ubbs.pizzame.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.view.PermissionManagerActivity;
import com.home.ubbs.pizzame.databinding.PizzaPlacesDetailsActivityBinding;
import com.home.ubbs.pizzame.viewmodel.PizzaPlacesDetailsViewModel;

/**
 * Created by udyatbhanu-mac on 6/20/17.
 */

public class PizzaPlacesDetailsActivity extends PermissionManagerActivity {

    private PizzaPlacesDetailsActivityBinding pizzaPlacesDetailsActivityBinding;
    private PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pizzaPlacesDetailsActivityBinding = DataBindingUtil.setContentView(this, R.layout.pizza_places_details_activity);
        setSupportActionBar(pizzaPlacesDetailsActivityBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(this);
        pizzaPlacesDetailsActivityBinding.setPizzaPlacesDetailsViewModel(pizzaPlacesDetailsViewModel);



    }

    @Override
    protected void onDestroy() {
        pizzaPlacesDetailsViewModel.destroy(); //not calling this will might cause a Context leak
        super.onDestroy();
    }


}
