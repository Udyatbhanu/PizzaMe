package com.home.ubbs.pizzame.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.home.ubbs.pizzame.PizzaMeApplication;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.NavigationEvent;
import com.home.ubbs.pizzame.core.view.BaseActivity;
import com.home.ubbs.pizzame.core.view.PermissionManagerActivity;
import com.home.ubbs.pizzame.core.viewmodel.BaseViewModel;
import com.home.ubbs.pizzame.model.PizzaResponse;
import com.home.ubbs.pizzame.model.Results;
import com.home.ubbs.pizzame.service.PizzaPlacesService;
import com.home.ubbs.pizzame.utils.Constants;
import com.home.ubbs.pizzame.views.activities.PizzaPlacesActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class MainViewModel extends BaseViewModel {

    /**
     * MainViewModel the view model for the MainActivity, putting the business logic and service calls here.
     * Used EventBus to notify events, however we can either use Rx or plain observables to notify events.
     */


    private Context context;
    public ObservableField<String> buttonText;

    private Results results;

    public ObservableInt serviceProgress;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MainViewModel(@NonNull Context context) {
        this.context = context;
        serviceProgress = new ObservableInt(View.GONE);
        buttonText = new ObservableField<>(context.getString(R.string.pizza_places_button_text));

    }

    /* Hardcoded for now, ideally would come form cms or an svc*/
    public String getToolbarImage() {
        return "http://www.leonis-pizzeria.com/wp-content/uploads/2015/04/Hot_pizza.jpg";
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    /**
     * @param view
     */

    public void onClickPizzaPlaces(View view) {

        boolean isServiceEnabled = false;
        PermissionManagerActivity permissionManagerActivity = null;
        if (view.getContext() instanceof PermissionManagerActivity) {
            permissionManagerActivity = (PermissionManagerActivity) view.getContext();
            isServiceEnabled = permissionManagerActivity.isNetworkEnabled() &&
                    permissionManagerActivity.isLocationEnabled();
        }

        if (isServiceEnabled) {
            if (SessionStack.getString(Constants.LATITUDE_KEY) == null) {
                permissionManagerActivity.getAddress();
            }
            serviceProgress.set(View.VISIBLE);
            PizzaMeApplication pizzaMeApplication = PizzaMeApplication.create(view.getContext());
            PizzaPlacesService pizzaPlacesService = pizzaMeApplication.getPizzaService();

            String query = context.getString(R.string.yql_query_text, SessionStack.getString(Constants.LATITUDE_KEY),
                    SessionStack.getString(Constants.LONGITUDE_KEY));


            Disposable disposable = pizzaPlacesService.fetchPizzaPlaces(query, context.getString(R.string.yql_format))
                    .subscribeOn(pizzaMeApplication.subscribeScheduler())
                    .throttleFirst(10000, TimeUnit.MILLISECONDS)  // this will prevent double click on the button
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<PizzaResponse>() {
                        @Override
                        public void accept(PizzaResponse pizzaResponse) throws Exception {

                            if (pizzaResponse != null && pizzaResponse.getQuery() != null &&
                                    pizzaResponse.getQuery().getResults().getResult() != null) {
                                serviceProgress.set(View.GONE);


                                results = pizzaResponse.getQuery().getResults();
                                SessionStack.put(BaseActivity.BUNDLE_EXTRAS, results);
                                handleServiceResponse(EventBus.getDefault());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            serviceProgress.set(View.GONE);
                            throwable.printStackTrace();
                            sendErrorEvent();
                        }
                    });

            compositeDisposable.add(disposable);


        }


    }


    /**
     * Handle the service response
     */
    public void handleServiceResponse(EventBus eventBus) {
        eventBus.post(new NavigationEvent(PizzaPlacesActivity.class, results));
    }


    /**
     * Clean it up
     */
    public void destroy() {

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
        context = null;
    }


}
