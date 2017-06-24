package com.home.ubbs.pizzame.core.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.ErrorEvent;
import com.home.ubbs.pizzame.core.events.NavigationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 * BaseActivity for all the activities within this app, all activities should extend from this as it takes care of basic eventbus registering and un-registering, also
 * responsible for showing error snackbars.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    /**
     * Shows a {@link Snackbar} using {@code text}.
     *
     * @param text The Snackbar text.
     */
    protected void showSnackbar() {
        showSnackbar(R.string.generic_error_message, android.R.string.ok,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
    }


    /**
     * Shows a {@link Snackbar}.
     */
    protected void showSnackbar(final int mainTextStringId, final int actionStringId,
                                View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * @param event
     */
    @Subscribe
    public void onNavigationEvent(NavigationEvent event) {

        EventBus.getDefault().removeStickyEvent(event);
        Intent intent = new Intent(this, event.clazz);
        intent.putExtra(BUNDLE_EXTRAS, event.data);
        startActivity(intent);

    }

    /**
     * @param event
     */
    @Subscribe
    public void onErrorEvent(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        showSnackbar();
    }


    protected void clearStack() {
        SessionStack.clearStack();
    }
}
