package com.home.ubbs.pizzame.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.view.BaseActivity;
import com.home.ubbs.pizzame.core.viewmodel.BaseViewModel;
import com.home.ubbs.pizzame.model.Results;
import com.home.ubbs.pizzame.utils.Utils;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class PizzaPlacesViewModel extends BaseViewModel {


    private Context context;

    public PizzaPlacesViewModel(@NonNull Context context) {
        this.context = context;
        preValidate();

    }


    /**
     * Check the stack, if it's cleared send out an event
     */
    private void preValidate() {
        if (SessionStack.get(BaseActivity.BUNDLE_EXTRAS) != null &&
                SessionStack.get(BaseActivity.BUNDLE_EXTRAS) instanceof Results) {
            try {
                Results results = (Results) SessionStack.get(BaseActivity.BUNDLE_EXTRAS);
            } catch (Exception e) {
                sendErrorEvent();
            }
        } else {
            sendErrorEvent();
        }
    }

    public String getToolbarImage() {
        String[] pizzas = context.getResources().getStringArray(R.array.pizza_uris);
        destroy();   //destroy the context, will prevent memory leaks
        return Utils.getRandomUrl(pizzas);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public String getTitle() {
        return context.getString(R.string.pizza_places_title_text);
    }


    /**
     *
     */
    public void destroy() {

        context = null;
    }

}
