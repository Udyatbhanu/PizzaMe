package com.home.ubbs.pizzame.views.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.view.BaseActivity;
import com.home.ubbs.pizzame.databinding.PizzaPlacesActivityBinding;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.model.Results;
import com.home.ubbs.pizzame.viewmodel.PizzaPlacesViewModel;
import com.home.ubbs.pizzame.views.activities.adapter.PizzaPlacesAdapter;

import java.util.List;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class PizzaPlacesActivity extends BaseActivity {


    private PizzaPlacesActivityBinding pizzaPlacesActivityBinding;
    private PizzaPlacesViewModel pizzaPlacesViewModel;
    private PizzaPlacesAdapter adapter;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pizzaPlacesActivityBinding = DataBindingUtil.setContentView(this, R.layout.pizza_places_activity);
        setSupportActionBar(pizzaPlacesActivityBinding.toolbar);
        pizzaPlacesViewModel = new PizzaPlacesViewModel(this);
        pizzaPlacesActivityBinding.setPizzaPlacesViewModel(pizzaPlacesViewModel);
        setUpResults();
    }


    private void setUpResults() {
        Results results = (Results) SessionStack.get(BaseActivity.BUNDLE_EXTRAS);
        if(results != null){
            List<Result> result = results.getResult();
            adapter = new PizzaPlacesAdapter(result);
            pizzaPlacesActivityBinding.listPizzaPlaces.setAdapter(adapter);
            pizzaPlacesActivityBinding.listPizzaPlaces.setLayoutManager(new LinearLayoutManager(this));
        } else {
            showSnackbar();
        }

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            clearStack();
            finishAffinity();
            System.exit(1);
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.press_back_message), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.legal:
                Intent legalIntent = new Intent(this, LicenseActivity.class);
                startActivity(legalIntent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        pizzaPlacesViewModel.destroy(); //not calling this will might cause a Context leak
        super.onDestroy();
    }


}
