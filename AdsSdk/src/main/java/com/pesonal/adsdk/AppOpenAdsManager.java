package com.pesonal.adsdk;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import static com.pesonal.adsdk.CustomAdActivity.arrayItems;
import static com.pesonal.adsdk.CustomAdActivity.modelList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

/**
 * Prefetches App Open Ads.
 */
public class AppOpenAdsManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static final String LOG_TAG = "AppOpenAdsManager";
    private AppOpenAd appOpenAd = null;
    private static String AD_UNIT_ID;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private Activity currentActivity;
    //    public static boolean isShowingAd = false;
    final String IsRecentOpen_ad;
    private final Application myApplication;
    public static boolean isAD = false;
    public static int count_click = -1;
    public static int rewardedVideoClickCount;

    /**
     * Constructor
     */
    public AppOpenAdsManager(Application myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        SharedPreferences mysharedpreferences = myApplication.getSharedPreferences(myApplication.getPackageName(), Context.MODE_PRIVATE);
        AD_UNIT_ID = mysharedpreferences.getString("AppOpenID", "");
        IsRecentOpen_ad = mysharedpreferences.getString("app_recentAppOpenAdStatus", "-1");
        rewardedVideoClickCount = mysharedpreferences.getInt("rewardedVideoClickCount", 0);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    /**
     * Request an ad
     */
    public void fetchAd() {

        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return;
        }

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                AppOpenAdsManager.this.appOpenAd = appOpenAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d(LOG_TAG, "error in loading");
            }
        };

        AdRequest request = getAdRequest();
        AppOpenAd.load(myApplication, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    /**
     * Shows the ad if one isn't already showing.
     */
    public void showAdIfAvailable() {
        if (AppManage.getInstance(currentActivity).IsPurchased()) {

            return;
        }
        count_click++;
        Log.d(LOG_TAG, "showAdIfAvailable: " + count_click);
        Log.d(LOG_TAG, "showAdIfAvailable:1 " + rewardedVideoClickCount);
        if (rewardedVideoClickCount != 0) {

            if (count_click % rewardedVideoClickCount != 0) {


                return;
            }
        }
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (!AppOpenManager.isShowingAd && isAdAvailable()) {
            Log.d(LOG_TAG, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            Log.d(LOG_TAG, "onAdDismissedFullScreenContent");
                            // Set the reference to null so isAdAvailable() returns false.
                            AppOpenAdsManager.this.appOpenAd = null;
                            AppOpenManager.isShowingAd = false;
                            fetchAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            AppOpenManager.isShowingAd = true;
                        }
                    };

            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(currentActivity);

        } else {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();


            if (!AppOpenManager.isShowingAd && IsRecentOpen_ad.equals("1") && AD_UNIT_ID.isEmpty()) {
                CustomAppOpenAds customAppOpenAds = new CustomAppOpenAds(currentActivity, R.style.Theme_AppOpen_Dialog);
                customAppOpenAds.setCancelable(false);
                customAppOpenAds.setCanceledOnTouchOutside(false);
                customAppOpenAds.appOpenCallBack = new CustomAppOpenFun(null, customAppOpenAds);
                customAppOpenAds.show();
            }


        }
    }


    /**
     * Creates and returns ad request.
     */
    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */
    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
        Log.d("TAG2", "onActivityResumed: " + activity.getComponentName().getClassName());




    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        currentActivity = null;
    }

    /**
     * LifecycleObserver methods
     */
    @OnLifecycleEvent(ON_START)
    public void onStart() {


        Log.d(LOG_TAG, "onStart: " + IsRecentOpen_ad);
        if (isAD) {
            fetchAd();
        } else {
            if (IsRecentOpen_ad.equals("1")) {
                showAdIfAvailable();

            }
            Log.d(LOG_TAG, "onStart");
        }

    }
}
