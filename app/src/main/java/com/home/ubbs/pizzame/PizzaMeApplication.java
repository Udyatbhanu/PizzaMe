package com.home.ubbs.pizzame;

import android.app.Application;
import android.content.Context;

import com.home.ubbs.pizzame.service.PizzaPlacesService;
import com.home.ubbs.pizzame.service.PizzaServiceFactory;
import com.squareup.leakcanary.LeakCanary;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class PizzaMeApplication extends Application {

    private PizzaPlacesService pizzaPlacesService;
    private Scheduler scheduler;



    @Override public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
    }


    private static PizzaMeApplication get(Context context) {
        return (PizzaMeApplication) context.getApplicationContext();
    }

    public static PizzaMeApplication create(Context context) {
        return PizzaMeApplication.get(context);
    }

    public PizzaPlacesService getPizzaService() {
        if (pizzaPlacesService == null) {
            pizzaPlacesService = PizzaServiceFactory.create();
        }

        return pizzaPlacesService;
    }



    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setPizzaPlacesService(PizzaPlacesService pizzaPlacesService) {
        this.pizzaPlacesService = pizzaPlacesService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }



}
