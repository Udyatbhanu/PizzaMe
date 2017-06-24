package com.home.ubbs.pizzame.core.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.home.ubbs.pizzame.BuildConfig;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.PermissionEvent;
import com.home.ubbs.pizzame.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by udyatbhanu-mac on 6/20/17.
 */

/**
 * Base class for activities that use strict permission
 * It is better to keep the permission management within the activity lifecycle rather than the view model handle this, additional permissions
 * can be added to this activity as and when required,
 * <p>
 * </div>
 */


public abstract class PermissionManagerActivity extends BaseActivity {

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    private static final String TAG = PermissionManagerActivity.class.getSimpleName();

    private LocationManager locationManager;
    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Represents a geographical location.
     */
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }


    @Override
    public void onStart() {
        super.onStart();

        if (!checkLocationPermissions()) {
            requestPermissions(Constants.EVENT_LOCATION_PERMISSION);
        } else {
            if (isNetworkEnabled() && isLocationEnabled()) {
                getAddress();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressWarnings("MissingPermission")
    public void getAddress() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            Log.w(TAG, "onSuccess:null");
                            return;
                        }

                        mLastLocation = location;
                        // Store the co-ordinates in session
                        SessionStack.put(Constants.LATITUDE_KEY, Double.toString(location.getLatitude()));
                        SessionStack.put(Constants.LONGITUDE_KEY, Double.toString(location.getLongitude()));


                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "getLastLocation:onFailure", e);
                    }
                });
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");

        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getAddress();
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }
    }

    /**
     * Return the current state of the permissions needed.
     */
    protected boolean checkLocationPermissions() {

        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param event
     */
    @Subscribe
    public void onPermissionEvent(PermissionEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        requestPermissions(event.getEventType());
    }


    /**
     * @param event
     */

    protected void requestPermissions(int event) {
        switch (event) {
            case Constants.EVENT_CALL_PERMISSION:


                boolean shouldProvideCallRationale =
                        ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.CALL_PHONE);

                // Provide an additional rationale to the user. This would happen if the user denied the
                // request previously, but didn't check the "Don't ask again" checkbox.
                if (shouldProvideCallRationale) {
//            Log.i(tag, "Displaying permission rationale to provide additional context.");

                    showSnackbar(R.string.permission_call_rationale, android.R.string.ok,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Request permission
                                    ActivityCompat.requestPermissions(PermissionManagerActivity.this,
                                            new String[]{Manifest.permission.CALL_PHONE},
                                            REQUEST_PERMISSIONS_REQUEST_CODE);
                                }
                            });

                } else {
//            Log.i(tag, "Requesting permission");
                    // Request permission. It's possible this can be auto answered if device policy
                    // sets the permission in a given state or the user denied the permission
                    // previously and checked "Never ask again".
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_PERMISSIONS_REQUEST_CODE);
                }
                break;
            case Constants.EVENT_LOCATION_PERMISSION:

                boolean shouldProvideLocationRationale =
                        ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.ACCESS_FINE_LOCATION);

                // Provide an additional rationale to the user. This would happen if the user denied the
                // request previously, but didn't check the "Don't ask again" checkbox.
                if (shouldProvideLocationRationale) {
//            Log.i(tag, "Displaying permission rationale to provide additional context.");

                    showSnackbar(R.string.permission_location_rationale, android.R.string.ok,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Request permission
                                    ActivityCompat.requestPermissions(PermissionManagerActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            REQUEST_PERMISSIONS_REQUEST_CODE);
                                }
                            });

                } else {
//            Log.i(tag, "Requesting permission");
                    // Request permission. It's possible this can be auto answered if device policy
                    // sets the permission in a given state or the user denied the permission
                    // previously and checked "Never ask again".
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_PERMISSIONS_REQUEST_CODE);
                }
        }


    }


    /**
     * @return
     */
    public boolean isNetworkEnabled() {
        boolean networkEnabled = false;
        try {
            networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            showSnackbar();
            return false;
        }

        if (!networkEnabled) {
            showSnackbar(R.string.unable_to_detect_network_error_message, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isNetworkEnabled();
                        }
                    });
            return false;
        }

        return true;
    }

    /**
     * @return
     */
    public boolean isLocationEnabled() {
        boolean gpsEnabled = false;
        try {
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            showSnackbar();
            return false;
        }

        if (!gpsEnabled) {
            showSnackbar(R.string.unable_to_detect_location_error_message, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isLocationEnabled();
                        }
                    });
            return false;
        }
        return true;
    }


}


