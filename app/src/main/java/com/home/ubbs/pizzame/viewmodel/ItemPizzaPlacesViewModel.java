package com.home.ubbs.pizzame.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.NavigationEvent;
import com.home.ubbs.pizzame.core.viewmodel.BaseViewModel;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.utils.Constants;
import com.home.ubbs.pizzame.views.activities.PizzaPlacesDetailsActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class ItemPizzaPlacesViewModel extends BaseViewModel {


    private Result result;
    private Context context;


    public ItemPizzaPlacesViewModel(Result result, @NonNull Context context) {
        this.context = context;
        this.result = result;
        preValidate();
    }

    private void preValidate() {
        if (result == null) {
            sendErrorEvent();
        }
    }

    public String getId() {
        if(result != null){
            return result.getId();
        }
      return "";
    }

    public String getTitle() {
        if(result != null) {
            return result.getTitle();
        }
        return "";
    }

    public String getAddress() {
        if(result != null) {
            return result.getAddress();
        }
        return "";
    }

    public String getDistance() {
        if(result != null){
            return context.getString(R.string.distance_unit, result.getDistance());

        }
        return "";
    }

    public String getCity() {
        if (result != null) {
            return result.getCity();
        }
        return "";
    }


    public String getState() {
        if(result != null){
            return result.getState();
        }
      return "";
    }


    public Result getResult() {
        return result;
    }

    public void onItemClick(View view) {
        SessionStack.put(Constants.SESSION_RESULT_KEY, result);
        EventBus.getDefault().post(new NavigationEvent(PizzaPlacesDetailsActivity.class, result));
    }


    public void setResult(Result result) {
        this.result = result;
        notifyChange();
    }

    /**
     *
     */
    public void destroy() {
        context = null;
    }


}
