package com.example.gpsdemo.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.Window;

import com.example.gpsdemo.R;

public class ShowProgressDialog {

    public static ProgressDialog progressDialog;

    public static void Show(Context context) {
        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle(msg);
//        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar_layout);
        System.out.println(progressDialog + " show called");
    }

    public static void Dismiss() {
        System.out.println(progressDialog + " dismiss called");
        if (progressDialog != null) {
            progressDialog.dismiss();
            System.out.println(progressDialog + " in dialog after dismiss" + Thread.currentThread().getId());
        }
        if (progressDialog != null && progressDialog.isShowing()) {

            //get the Context object that was used to great the dialog
            Context context = ((ContextWrapper) progressDialog.getContext()).getBaseContext();

            // if the Context used here was an activity AND it hasn't been finished or destroyed
            // then dismiss it
            if (context instanceof Activity) {

                // Api >=17
                if (!((Activity) context).isFinishing()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!((Activity) context).isDestroyed()) {
                            dismissWithExceptionHandling(progressDialog);
                        }
                    } else {
                        // Api < 17. Unfortunately cannot check for isDestroyed()
                        dismissWithExceptionHandling(progressDialog);
                    }
                }
            } else
                // if the Context used wasn't an Activity, then dismiss it too
                dismissWithExceptionHandling(progressDialog);
        }

    }

    public static void dismissWithExceptionHandling(ProgressDialog dialog) {
        try {
            dialog.dismiss();
        } catch (final IllegalArgumentException e) {
            // Do nothing.
        } catch (final Exception e) {
            // Do nothing.
        } finally {
            dialog = null;
        }
    }

    public static boolean isShowing() {
        boolean isShowing = false;
        if (progressDialog != null) {
            isShowing = progressDialog.isShowing();
        }
        return isShowing;
    }
}
