package com.home.ubbs.pizzame.viewmodel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.PermissionEvent;
import com.home.ubbs.pizzame.core.viewmodel.BaseViewModel;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.utils.Constants;
import com.home.ubbs.pizzame.utils.Utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by udyatbhanu-mac on 6/20/17.
 */

public class PizzaPlacesDetailsViewModel extends BaseViewModel {

    private Context context;
    private Result result;


    public PizzaPlacesDetailsViewModel(@NonNull Context context) {
        this.context = context;
        preValidate();
    }


    /**
     * Check the stack, if it's cleared send out an event
     */
    private void preValidate() {
        if (SessionStack.get(Constants.SESSION_RESULT_KEY) != null &&
                SessionStack.get(Constants.SESSION_RESULT_KEY) instanceof Result) {
            try {
                result = (Result) SessionStack.get(Constants.SESSION_RESULT_KEY);
            } catch (Exception e) {
                sendErrorEvent();
            }
        } else {
            sendErrorEvent();
        }
    }


    public String getToolbarImage() {
        String[] pizzas = context.getResources().getStringArray(R.array.pizza_uris);
        return Utils.getRandomUrl(pizzas);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }


    public String getTitle() {
        if (result != null) {
            return result.getTitle();
        }
        return "";
    }


    public String getAddress() {
        if (result != null) {
            return result.getAddress();
        }
        return "";
    }

    public String getCity() {
        if (result != null) {
            return result.getCity();
        }
        return "";
    }

    public String getDistance() {
        if (result != null) {
            return context.getString(R.string.distance_unit, result.getDistance());
        }
        return "";
    }

    public String getAverageRating() {
        if (result != null) {
            if (result.getRating() != null) {
                return result.getRating().getAverageRating();
            } else {
                return context.getString(R.string.none_text);
            }
        }
        return "";
    }

    public String getWebsite() {
        if (result != null) {
            String websiteFormatted = Utils.getWebsite(result.getBusinessClickUrl());
            return websiteFormatted;
        }

        return "";

    }


    public String getPhone() {
        if (result != null) {
            return result.getPhone();
        }
        return "";
    }


    @SuppressWarnings("MissingPermission")
    public void onPhoneLinkClick(View view) {

        if (checkCallPermissions(view.getContext())) {

            if (result.getPhone() != null) {
                String uri = "tel:" + result.getPhone().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                view.getContext().startActivity(intent);
            }

        } else {
            EventBus.getDefault().post(new PermissionEvent(Constants.EVENT_CALL_PERMISSION));
        }


    }

    public void onLinkClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getBusinessUrl()));
        view.getContext().startActivity(browserIntent);
    }


    public void onMapLinkClick(View view) {

        if (result.getLatitude() != null && result.getLongitude() != null) {
            Uri gmmIntentUri = Uri.parse(context.getString(R.string.google_maps_format,
                    result.getLatitude(), result.getLongitude(), Uri.encode(result.getTitle())));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage(context.getString(R.string.google_maps_package_name));
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                view.getContext().startActivity(mapIntent);
            }
        }


    }


    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkCallPermissions(Context context) {

        int permissionState = ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    /**
     *
     */
    public void destroy() {

        context = null;
    }

}
