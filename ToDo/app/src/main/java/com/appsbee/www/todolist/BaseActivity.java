package com.appsbee.www.todolist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by root on 9/2/16.
 */
public class BaseActivity extends AppCompatActivity implements IBase {
    private ProgressDialog pd;
    public static int DEVICE_WIDTH = 0;
    public static int DEVICE_HEIGHT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        DEVICE_WIDTH = displaymetrics.widthPixels;
        DEVICE_HEIGHT = displaymetrics.heightPixels;
    }

    @Override
    public void showLoading(String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setMessage(message);
            pd.getProgress();
            pd.show();
            pd.setCancelable(false);
            pd.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override
                public void onCancel(DialogInterface dialog){
                    pd = null;
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
