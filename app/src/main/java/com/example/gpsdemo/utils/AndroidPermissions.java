package com.example.gpsdemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper to handle the new Android 6.0 Permissions requests.
 *
 * @author gotev (Aleksandar Gotev)
 */
public class AndroidPermissions {

    private final AndroidPermissions androidPermissions;
    private Activity activity;
    private String[] requiredPermissions;
    private List<String> mPermissionsToRequest = new ArrayList<>();

    private AndroidPermissions(Activity context, String... requiredPermissions) {
        activity = context;
        this.requiredPermissions = requiredPermissions;
        androidPermissions = this;
    }

    public AndroidPermissions(Activity context) {
        activity = context;
        androidPermissions = this;
    }

    /**
     * Checks if all the required permissions are granted.
     *
     * @return true if all the required permissions are granted, otherwise false
     */
    private boolean checkPermissions() {
        mPermissionsToRequest.clear();
        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionsToRequest.add(permission);
            }
        }

        return mPermissionsToRequest.isEmpty();

    }

    /**
     * Requests the missing permissions.
     * The activity from which this method is called has to implement
     * {@link Activity#onRequestPermissionsResult(int, String[], int[])}
     * and then, inside it, it has to call the method
     * {@link AndroidPermissions#areAllRequiredPermissionsGranted(int[])} to check that all the
     * requested permissions are granted by the user
     *
     * @param requestCode request code used by the activity
     */
    private void requestPermissions(int requestCode) {
        String[] request = mPermissionsToRequest.toArray(new String[mPermissionsToRequest.size()]);

        StringBuilder log = new StringBuilder();
        log.append("Requesting permissions:\n");

        for (String permission : request) {
            log.append(permission).append("\n");
        }

        Log.i(getClass().getSimpleName(), log.toString());

        ActivityCompat.requestPermissions(activity, request, requestCode);
    }

    /**
     * Method to call inside
     * {@link Activity#onRequestPermissionsResult(int, String[], int[])}, to check if the
     * required permissions are granted.
     *
     * @param grantResults results
     * @return true if all the required permissions are granted, otherwise false
     */
    public boolean areAllRequiredPermissionsGranted(int[] grantResults) {
        if (grantResults == null || grantResults.length == 0) {
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to request contact permission.
     */
    public boolean requestLocationPermission() {
        this.requiredPermissions = new String[]{ Manifest.permission.ACCESS_FINE_LOCATION}; // Manifest.permission.ACCESS_COARSE_LOCATION it already included if you access fine location.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkPermissions()) {
            return true;
        } else {
            androidPermissions.requestPermissions(PERMISSIONS_REQUEST_LOCATION_SERVICES);
            return false;
        }
    }

    /**
     * Method to request gallery or images permission.
     */
    public boolean requestCallPermission() {
        boolean returnValue;
        this.requiredPermissions = new String[]{Manifest.permission.CALL_PHONE};
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkPermissions()) {
            returnValue = true;
        } else {
            androidPermissions.requestPermissions(PERMISSIONS_REQUEST_CALL);
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Method to request camera and gallery or images permission.
     */
    public boolean requestCameraAndGalleryPermission() {
        boolean returnValue;
        this.requiredPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkPermissions()) {
            returnValue = true;
        } else {
            androidPermissions.requestPermissions(PERMISSIONS_REQUEST_CAMERA_AND_READ_WRITE_GALLERY);
            returnValue = false;
        }
        return returnValue;
    }
    public static final int PERMISSIONS_REQUEST_LOCATION_SERVICES = 1;
    public static final int PERMISSIONS_REQUEST_READ_WRITE_GALLERY = 2;
    public static final int PERMISSIONS_REQUEST_CAMERA_AND_READ_WRITE_GALLERY = 3;
    public static final int PERMISSIONS_REQUEST_CALL = 4;


}