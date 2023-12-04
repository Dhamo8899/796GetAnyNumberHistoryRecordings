package com.myapps.getcall_smsinfoanynumberthree;


import android.app.Application;
import com.pesonal.adsdk.AppOpenAdsManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new AppOpenAdsManager(this);
    }

}
