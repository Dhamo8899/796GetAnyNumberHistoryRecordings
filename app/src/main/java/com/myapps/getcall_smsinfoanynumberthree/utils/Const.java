package com.myapps.getcall_smsinfoanynumberthree.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;

public class Const {
    public static String MAIN_BASE_URL="https://signal.makemyapps.co/signal/";
    public static String MOBILE_NUMBER="MOBILE_NUMBER";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean isOnline(Context context) {
        if (context == null)
            return false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public static void saveDataList(Context context,String key, ResponseDataList object) {
        String myObject = new Gson().toJson(object);
        SharedPreferences.Editor editor = context.getSharedPreferences("CallHistory", Context.MODE_PRIVATE).edit();
        editor.putString(key, myObject);
        editor.apply();
    }

    public static ResponseDataList getDataList(Context context,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CallHistory", Context.MODE_PRIVATE);
        String myObject = sharedPreferences.getString(key, "");
        return new Gson().fromJson(myObject, ResponseDataList.class);
    }
}
