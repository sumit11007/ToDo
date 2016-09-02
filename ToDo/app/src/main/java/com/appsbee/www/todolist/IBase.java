package com.appsbee.www.todolist;

import android.content.Context;

/**
 * Created by root on 9/2/16.
 */
public interface IBase {

    void showLoading(String message);

    void hideLoading();

    void showToastMessage(String message);

    void finish();

    void onBackPressed();

    Context getContext();

}
