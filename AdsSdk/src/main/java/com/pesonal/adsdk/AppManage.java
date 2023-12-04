package com.pesonal.adsdk;

import static com.applovin.sdk.AppLovinSdkUtils.runOnUiThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class AppManage {
    private static final String TAG = "AppManage";
    public static String ADMOB = "Admob";
    public static String APPLOVIN = "AppLovin";
    public static String CustomAd = "CustomAd";
    public static String BrowserAd = "BrowserAd";
    public static String WebViewAd = "WebViewAd";
    public static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 9999;
    public static boolean CHROME_CUSTOM = true;

    public static int count_banner = -1;
    public static int count_native = -1;
    public static int count_click = -1;

    public static int count_click_for_alt = -1;
    public static String app_privacyPolicyLink = "";
    public static String app_accountLink = "";
    public static int app_updateAppDialogStatus = 0;
    public static int app_dialogBeforeAdShow = 0;
    public static int app_redirectOtherAppStatus = 0;
    public static int rewardedVideoClickCount = 3;

    public static int app_adShowStatus = 1;
    public static int app_mainClickCntSwAd = 0;
    public static int app_innerClickCntSwAd = 0;
    public static String appNativeAdsCount = "5";
    public static String app_recentAppOpenAdStatus = "1";
    public static String ADMOB_APPID = "";
    public static String ADMOB_I1 = "";
    public static String ADMOB_I2 = "";
    public static String ADMOB_I3 = "";
    public static String ADMOB_I4 = "";
    public static String ADMOB_I5 = "";
    public static String ADMOB_B1 = "";
    public static String ADMOB_B2 = "";
    public static String ADMOB_B3 = "";
    public static String ADMOB_B4 = "";
    public static String ADMOB_B5 = "";
    public static String ADMOB_N1 = "";
    public static String ADMOB_N2 = "";
    public static String ADMOB_N3 = "";
    public static String ADMOB_N4 = "";
    public static String ADMOB_N5 = "";
    public static String ADMOB_RW1 = "";
    public static String ADMOB_RW2 = "";
    public static String ADMOB_RW3 = "";
    public static String ADMOB_RW4 = "";
    public static String ADMOB_RW5 = "";

    public static String APPLOVIN_APPID = "";
    public static String APPLOVIN_I1 = "3419371733ff4ea3";
    public static String APPLOVIN_B1 = "80de5fcd2875a731";
    public static String APPLOVIN_N1 = "b5fd59b72041e7a4";
    public static String APPLOVIN_R1 = "";

    public static String WEBVIEW_APPID = "";
    public static String WEBVIEW_B1 = "";

    public static String BROWSERAD_URL = "";
    public static String BROWSER_I1 = "";
    public static String BROWSER_I2 = "";
    public static String BROWSER_I3 = "";
    public static String BROWSER_I4 = "";
    public static String BROWSER_I5 = "";
    public static int BROWSER_I6 = 0;
    public static String BROWSER_I7 = "";
    public static String BROWSER_I8 = "";
    public static String BROWSER_I9 = "mod_pack";
    public static String BROWSER_I10 ;
    public static String BROWSER_I11 ="";


    public static String QUREKA_APPID = "";
    public static String QUREKA_B1 = "";
    public static String QUREKA_B2 = "";
    public static String QUREKA_B3 = "";
    public static String QUREKA2_APPID = "";
    public static String QUREKA2_B1 = "";
    public static int qurekaSecond_AdStatus = 0;
    public static String GAMEZOP_APPID = "";
    public static String GAMEZOP_B1 = "";
    public static String GAMEZOP_B2 = "";
    public static String GAMEZOP_B3 = "";
    public static String GAMEZOP_B4 = "";
    public static String ATMEGAME_APPID = "";
    public static String ATMEGAME_B1 = "";
    public static String ATMEGAME_B2 = "";
    public static String ATMEGAME_B3 = "";
    public static String MGLGAME_APPID = "";
    public static String MGLGAMEGAME_B1 = "";

    public static String PREDCHAMPGAME_APPID = "";
    public static String PREDCHAMPGAME_B1 = "";

    public static int browserAd_AdStatus = 0;
    public static int customAd_AdStatus = 0;
    public static int qureka_AdStatus = 0;
    public static int gamezop_AdStatus = 0;
    public static int atmegame_AdStatus = 0;
    public static int mglgame_AdStatus = 0;
    public static int predchampgame_AdStatus = 0;
    public static int admob_AdStatus = 0;
    public static int applovin_AdStatus = 0;
    public static int webview_AdStatus = 0;
    public static SharedPreferences mysharedpreferences;
    public static int ad_dialog_time_in_second = 1;
    static Activity activity;
    static MyInterStitialCallback intermyCallback;
    private static AppManage mInstance;

    private InterstitialAd mInterstitialAd;
    private MaxInterstitialAd appLovin_interstitialAd;
    private String google_i_pre = "";
    private String google_rw_pre = "";
    public static int reward_count_for_alt = -1;
    static MyRewardAdCallback rewardmyCallback;
    public static int rewardcount_click = -1;
    ArrayList<String> reward_sequence = new ArrayList<>();

    String admob_b;
    String admob_n;
    ArrayList<String> banner_sequence = new ArrayList<>();
    ArrayList<String> native_sequence = new ArrayList<>();
    ArrayList<String> interstitial_sequence = new ArrayList<>();
    private Dialog dialog;
    private boolean ISRewardErned = false;
    MaxRewardedAd maxRewardedAd;


    public AppManage(Activity activity) {
        AppManage.activity = activity;
        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        getResponseFromPref();
    }

    public static AppManage getInstance(Activity activity) {
        AppManage.activity = activity;
        if (mInstance == null) {
            mInstance = new AppManage(activity);
        }
        return mInstance;
    }

    public static boolean hasActiveInternetConnection(Context context) {
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void getResponseFromPref() {
        String response1 = mysharedpreferences.getString("response", "");
        if (!response1.isEmpty()) {
            try {
                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");

                app_accountLink = settingsJsonObject.getString("app_accountLink");
                app_privacyPolicyLink = settingsJsonObject.getString("app_privacyPolicyLink");
                app_updateAppDialogStatus = settingsJsonObject.getInt("app_updateAppDialogStatus");
                app_redirectOtherAppStatus = settingsJsonObject.getInt("app_redirectOtherAppStatus");
                app_dialogBeforeAdShow = settingsJsonObject.getInt("app_dialogBeforeAdShow");
                app_adShowStatus = settingsJsonObject.getInt("app_adShowStatus");
                app_mainClickCntSwAd = settingsJsonObject.getInt("app_mainClickCntSwAd");
                app_innerClickCntSwAd = settingsJsonObject.getInt("app_innerClickCntSwAd");
                appNativeAdsCount = settingsJsonObject.getString("appNativeAdsCount");
                app_recentAppOpenAdStatus = settingsJsonObject.getString("app_recentAppOpenAdStatus");
                rewardedVideoClickCount = settingsJsonObject.getInt("rewardedVideoClickCount");


                boolean app_AppOpenAdStatus;
                if (settingsJsonObject.getInt("app_AppOpenAdStatus") == 1) {
                    app_AppOpenAdStatus = true;
                } else {
                    app_AppOpenAdStatus = false;
                }

                SharedPreferences.Editor editor = mysharedpreferences.edit();
                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);
                editor.putInt("rewardedVideoClickCount", rewardedVideoClickCount);


                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));

                editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);
                editor.putString("appNativeAdsCount", appNativeAdsCount);

                editor.putBoolean("app_AppOpenAdStatus", app_AppOpenAdStatus);
                editor.commit();

                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                ADMOB_B1 = AdmobJsonObject.getString("Banner1");
                ADMOB_B2 = AdmobJsonObject.getString("Banner2");
                ADMOB_B3 = AdmobJsonObject.getString("Banner3");
                ADMOB_B4 = AdmobJsonObject.getString("Banner4");
                ADMOB_B5 = AdmobJsonObject.getString("Banner5");
                ADMOB_I1 = AdmobJsonObject.getString("Interstitial1");
                ADMOB_I2 = AdmobJsonObject.getString("Interstitial2");
                ADMOB_I3 = AdmobJsonObject.getString("Interstitial3");
                ADMOB_I4 = AdmobJsonObject.getString("Interstitial4");
                ADMOB_I5 = AdmobJsonObject.getString("Interstitial5");
                ADMOB_N1 = AdmobJsonObject.getString("Native1");
                ADMOB_N2 = AdmobJsonObject.getString("Native2");
                ADMOB_N3 = AdmobJsonObject.getString("Native3");
                ADMOB_N4 = AdmobJsonObject.getString("Native4");
                ADMOB_N5 = AdmobJsonObject.getString("Native5");
                ADMOB_RW1 = AdmobJsonObject.getString("RewardedVideo1");
                ADMOB_RW2 = AdmobJsonObject.getString("RewardedVideo2");
                ADMOB_RW3 = AdmobJsonObject.getString("RewardedVideo3");
                ADMOB_RW4 = AdmobJsonObject.getString("RewardedVideo4");
                ADMOB_RW5 = AdmobJsonObject.getString("RewardedVideo5");

                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen"));
                editor1.commit();


                JSONObject AppLovinJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("AppLovin");
                applovin_AdStatus = AppLovinJsonObject.getInt("ad_showAdStatus");
                APPLOVIN_APPID = AppLovinJsonObject.getString("AppID");
                APPLOVIN_B1 = AppLovinJsonObject.getString("Banner1");
                APPLOVIN_I1 = AppLovinJsonObject.getString("Interstitial1");
                APPLOVIN_N1 = AppLovinJsonObject.getString("Native1");
                APPLOVIN_R1 = AppLovinJsonObject.getString("RewardedVideo1");

                JSONObject WebviewJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("WebViewAd");
                webview_AdStatus = WebviewJsonObject.getInt("ad_showAdStatus");
                WEBVIEW_APPID = WebviewJsonObject.getString("AppID");
                WEBVIEW_B1 = WebviewJsonObject.getString("Banner1");





                JSONObject QurekaJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Qureka");
                qureka_AdStatus = QurekaJsonObject.getInt("ad_showAdStatus");
                QUREKA_APPID = QurekaJsonObject.getString("AppID");
                QUREKA_B1 = QurekaJsonObject.getString("Banner1");
                QUREKA_B2 = QurekaJsonObject.getString("Banner2");
                QUREKA_B3 = QurekaJsonObject.getString("Banner3");


                JSONObject QurekaSecondJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("QurekaSecond");
                qurekaSecond_AdStatus = QurekaSecondJsonObject.getInt("ad_showAdStatus");
                QUREKA2_APPID = QurekaSecondJsonObject.getString("AppID");
                QUREKA2_B1 = QurekaSecondJsonObject.getString("Banner1");

                JSONObject GZJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("GameZop");
                gamezop_AdStatus = GZJsonObject.getInt("ad_showAdStatus");
                GAMEZOP_APPID = GZJsonObject.getString("AppID");
                GAMEZOP_B1 = GZJsonObject.getString("Banner1");
                GAMEZOP_B2 = GZJsonObject.getString("Banner2");
                GAMEZOP_B3 = GZJsonObject.getString("Banner3");
                GAMEZOP_B4 = GZJsonObject.getString("Banner4");

                JSONObject AGJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Atmegame");
                atmegame_AdStatus = AGJsonObject.getInt("ad_showAdStatus");
                ATMEGAME_APPID = AGJsonObject.getString("AppID");
                ATMEGAME_B1 = AGJsonObject.getString("Banner1");
                ATMEGAME_B2 = AGJsonObject.getString("Banner2");
                ATMEGAME_B3 = AGJsonObject.getString("Banner3");

                JSONObject MGL_GJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MGLGamez");
                mglgame_AdStatus = MGL_GJsonObject.getInt("ad_showAdStatus");
                MGLGAME_APPID = MGL_GJsonObject.getString("AppID");
                MGLGAMEGAME_B1 = MGL_GJsonObject.getString("Banner1");


                JSONObject Predchamp_GJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Predchamp");
                predchampgame_AdStatus = Predchamp_GJsonObject.getInt("ad_showAdStatus");
                PREDCHAMPGAME_APPID = Predchamp_GJsonObject.getString("AppID");
                PREDCHAMPGAME_B1 = Predchamp_GJsonObject.getString("Banner1");
                JSONObject CAJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("CustomAd");
                customAd_AdStatus = CAJsonObject.getInt("ad_showAdStatus");

                JSONObject BAJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("BrowserAd");
                browserAd_AdStatus = BAJsonObject.getInt("ad_showAdStatus");
                BROWSER_I1 = BAJsonObject.getString("Interstitial1");
                BROWSER_I2 = BAJsonObject.getString("Interstitial2");
                BROWSER_I3 = BAJsonObject.getString("Interstitial3");
                BROWSER_I4 = BAJsonObject.getString("Interstitial4");
                BROWSER_I5 = BAJsonObject.getString("Interstitial5");
                BROWSER_I6 = BAJsonObject.getInt("Interstitial6");
                BROWSER_I7 = BAJsonObject.getString("Interstitial7");
                BROWSER_I8 = BAJsonObject.getString("Interstitial8");
                BROWSER_I9 = BAJsonObject.getString("Interstitial9");
                BROWSER_I10 = BAJsonObject.getString("Interstitial10");
                BROWSER_I11 = BAJsonObject.getString("Interstitial11");


            } catch (Exception e) {
                Log.e(TAG, "getResponseFromPref: " + e.getLocalizedMessage());
            }

        }

    }

    private static void initAd() {

        if (AppManage.getInstance(activity).IsPurchased()) {
            return;
        }

        if (admob_AdStatus == 1) {
            MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
        }

        if (applovin_AdStatus == 1) {
            AppLovinSdk.getInstance(activity).setMediationProvider("max");
            AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    Log.e(TAG, "onSdkInitialized: " + configuration.getConsentDialogState());
                }
            });


            AppLovinSdk.getInstance(activity).getSettings().setVerboseLogging(true);
            AppLovinSdk.getInstance(activity).getSettings().setTestDeviceAdvertisingIds(
                    Arrays.asList("e6e75aed-5721-4a1b-9758-6604c6ebd08b",
                            "0739e980-8ad7-4bdb-8ed5-08f459d6af10",
                            "319e59a3-6091-4a7f-a887-a1c6eaadeb95"
                            , "4385152f-2dae-482e-8019-8e48a979f062"));
        }


        AppManage.getInstance(activity).loadInterstitialAd(activity);
//        AppManage.getInstance(activity).PreLoadNative();
    }

    private static boolean checkUpdate(int cversion) {


        if (mysharedpreferences.getInt("app_updateAppDialogStatus", 0) == 1) {
            String versions = mysharedpreferences.getString("app_versionCode", "");
            String str[] = versions.split(",");

            try {
                if (Arrays.asList(str).contains(cversion + "")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public void getResponseFromPref(getDataListner listner, int cversion) {
        String response1 = mysharedpreferences.getString("response", "");
        if (!response1.isEmpty()) {
            try {
                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");

                app_accountLink = settingsJsonObject.getString("app_accountLink");
                app_privacyPolicyLink = settingsJsonObject.getString("app_privacyPolicyLink");
                app_updateAppDialogStatus = settingsJsonObject.getInt("app_updateAppDialogStatus");
                app_redirectOtherAppStatus = settingsJsonObject.getInt("app_redirectOtherAppStatus");
                app_dialogBeforeAdShow = settingsJsonObject.getInt("app_dialogBeforeAdShow");
                app_adShowStatus = settingsJsonObject.getInt("app_adShowStatus");
                app_mainClickCntSwAd = settingsJsonObject.getInt("app_mainClickCntSwAd");
                app_innerClickCntSwAd = settingsJsonObject.getInt("app_innerClickCntSwAd");
                appNativeAdsCount = settingsJsonObject.getString("appNativeAdsCount");
                app_recentAppOpenAdStatus = settingsJsonObject.getString("app_recentAppOpenAdStatus");
                rewardedVideoClickCount = settingsJsonObject.getInt("rewardedVideoClickCount");

                boolean app_AppOpenAdStatus;
                if (settingsJsonObject.getInt("app_AppOpenAdStatus") == 1) {
                    app_AppOpenAdStatus = true;
                } else {
                    app_AppOpenAdStatus = false;
                }

                SharedPreferences.Editor editor = mysharedpreferences.edit();
                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);

                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));

                editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);
                editor.putString("appNativeAdsCount", appNativeAdsCount);
                editor.putBoolean("app_AppOpenAdStatus", app_AppOpenAdStatus);
                editor.putString("app_recentAppOpenAdStatus", app_recentAppOpenAdStatus);
                editor.putInt("rewardedVideoClickCount", rewardedVideoClickCount);

                editor.commit();

                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                ADMOB_B1 = AdmobJsonObject.getString("Banner1");
                ADMOB_B2 = AdmobJsonObject.getString("Banner2");
                ADMOB_B3 = AdmobJsonObject.getString("Banner3");
                ADMOB_B4 = AdmobJsonObject.getString("Banner4");
                ADMOB_B5 = AdmobJsonObject.getString("Banner5");
                ADMOB_I1 = AdmobJsonObject.getString("Interstitial1");
                ADMOB_I2 = AdmobJsonObject.getString("Interstitial2");
                ADMOB_I3 = AdmobJsonObject.getString("Interstitial3");
                ADMOB_I4 = AdmobJsonObject.getString("Interstitial4");
                ADMOB_I5 = AdmobJsonObject.getString("Interstitial5");
                ADMOB_N1 = AdmobJsonObject.getString("Native1");
                ADMOB_N2 = AdmobJsonObject.getString("Native2");
                ADMOB_N3 = AdmobJsonObject.getString("Native3");
                ADMOB_N4 = AdmobJsonObject.getString("Native4");
                ADMOB_N5 = AdmobJsonObject.getString("Native5");
                ADMOB_RW1 = AdmobJsonObject.getString("RewardedVideo1");
                ADMOB_RW2 = AdmobJsonObject.getString("RewardedVideo2");
                ADMOB_RW3 = AdmobJsonObject.getString("RewardedVideo3");
                ADMOB_RW4 = AdmobJsonObject.getString("RewardedVideo4");
                ADMOB_RW5 = AdmobJsonObject.getString("RewardedVideo5");

                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen"));
                editor1.commit();

                JSONObject AppLovinJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("AppLovin");
                applovin_AdStatus = AppLovinJsonObject.getInt("ad_showAdStatus");
                APPLOVIN_APPID = AppLovinJsonObject.getString("AppID");
                APPLOVIN_B1 = AppLovinJsonObject.getString("Banner1");
                APPLOVIN_I1 = AppLovinJsonObject.getString("Interstitial1");
                APPLOVIN_N1 = AppLovinJsonObject.getString("Native1");
                APPLOVIN_R1 = AppLovinJsonObject.getString("RewardedVideo1");

                JSONObject WebviewJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("WebViewAd");
                webview_AdStatus = WebviewJsonObject.getInt("ad_showAdStatus");
                WEBVIEW_APPID = WebviewJsonObject.getString("AppID");
                WEBVIEW_B1 = WebviewJsonObject.getString("Banner1");


                JSONObject QurekaJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Qureka");
                qureka_AdStatus = QurekaJsonObject.getInt("ad_showAdStatus");
                QUREKA_APPID = QurekaJsonObject.getString("AppID");
                QUREKA_B1 = QurekaJsonObject.getString("Banner1");
                QUREKA_B2 = QurekaJsonObject.getString("Banner2");
                QUREKA_B3 = QurekaJsonObject.getString("Banner3");

                JSONObject QurekaSecondJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("QurekaSecond");
                qurekaSecond_AdStatus = QurekaSecondJsonObject.getInt("ad_showAdStatus");
                QUREKA2_APPID = QurekaSecondJsonObject.getString("AppID");
                QUREKA2_B1 = QurekaSecondJsonObject.getString("Banner1");

                JSONObject GZJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("GameZop");
                gamezop_AdStatus = GZJsonObject.getInt("ad_showAdStatus");
                GAMEZOP_APPID = GZJsonObject.getString("AppID");
                GAMEZOP_B1 = GZJsonObject.getString("Banner1");
                GAMEZOP_B2 = GZJsonObject.getString("Banner2");
                GAMEZOP_B3 = GZJsonObject.getString("Banner3");
                GAMEZOP_B4 = GZJsonObject.getString("Banner4");

                JSONObject AGJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Atmegame");
                atmegame_AdStatus = AGJsonObject.getInt("ad_showAdStatus");
                ATMEGAME_APPID = AGJsonObject.getString("AppID");
                ATMEGAME_B1 = AGJsonObject.getString("Banner1");
                ATMEGAME_B2 = AGJsonObject.getString("Banner2");
                ATMEGAME_B3 = AGJsonObject.getString("Banner3");
                JSONObject MGL_GJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MGLGamez");
                mglgame_AdStatus = MGL_GJsonObject.getInt("ad_showAdStatus");
                MGLGAME_APPID = MGL_GJsonObject.getString("AppID");
                MGLGAMEGAME_B1 = MGL_GJsonObject.getString("Banner1");


                JSONObject Predchamp_GJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Predchamp");
                predchampgame_AdStatus = Predchamp_GJsonObject.getInt("ad_showAdStatus");
                PREDCHAMPGAME_APPID = Predchamp_GJsonObject.getString("AppID");
                PREDCHAMPGAME_B1 = Predchamp_GJsonObject.getString("Banner1");

                JSONObject CAJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("CustomAd");
                customAd_AdStatus = CAJsonObject.getInt("ad_showAdStatus");

                JSONObject BAJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("BrowserAd");
                browserAd_AdStatus = BAJsonObject.getInt("ad_showAdStatus");
                BROWSER_I1 = BAJsonObject.getString("Interstitial1");
                BROWSER_I2 = BAJsonObject.getString("Interstitial2");
                BROWSER_I3 = BAJsonObject.getString("Interstitial3");
                BROWSER_I4 = BAJsonObject.getString("Interstitial4");
                BROWSER_I5 = BAJsonObject.getString("Interstitial5");
                BROWSER_I6 = BAJsonObject.getInt("Interstitial6");
                BROWSER_I7 = BAJsonObject.getString("Interstitial7");
                BROWSER_I8 = BAJsonObject.getString("Interstitial8");
                BROWSER_I9 = BAJsonObject.getString("Interstitial9");
                BROWSER_I10 = BAJsonObject.getString("Interstitial10");
                BROWSER_I11 = BAJsonObject.getString("Interstitial11");

                try {
                    listner.onGetExtradata(response.getString("EXTRA_DATA"));
                    Log.e("EXTRA_DATA", response.getString("EXTRA_DATA").toString());
                } catch (Exception e) {
                    Log.e("extradata_error", e.getMessage());
                }


            } catch (Exception e) {
                Log.e(TAG, "getResponseFromPref: " + e.getLocalizedMessage());
            }

            if (app_redirectOtherAppStatus == 1) {
                String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
                listner.onRedirect(redirectNewPackage);
            } else if (app_updateAppDialogStatus == 1 && checkUpdate(cversion)) {
                listner.onUpdate("https://play.google.com/store/apps/details?id=" + activity.getPackageName());
            } else {
                Log.d("AppOpenSplashAds", "onSuccess : 11");
                listner.onSuccess();
                initAd();
                if (intermyCallback != null) {
                    intermyCallback.callbackCall();
                    intermyCallback = null;
                }
            }
        }
    }


    public void loadInterstitialAd(Activity activity) {

        if (!hasActiveInternetConnection(activity)) {
            return;
        }

        if (app_adShowStatus == 0) {
            return;
        }

        if (AppManage.getInstance(activity).IsPurchased()) {
            return;
        }

        SharedPreferences.Editor nativeEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsInterSwipeCount", -1) == 1) {
            google_i_pre = ADMOB_I1;
            nativeEditor.putInt("AdsInterSwipeCount", 2);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsInterSwipeCount", -1) == 2) {
            google_i_pre = ADMOB_I2;
            nativeEditor.putInt("AdsInterSwipeCount", 3);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsInterSwipeCount", -1) == 3) {
            google_i_pre = ADMOB_I3;
            nativeEditor.putInt("AdsInterSwipeCount", 4);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsInterSwipeCount", -1) == 4) {
            google_i_pre = ADMOB_I4;
            nativeEditor.putInt("AdsInterSwipeCount", 5);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsInterSwipeCount", -1) == 5) {
            google_i_pre = ADMOB_I5;
            nativeEditor.putInt("AdsInterSwipeCount", 1);
            nativeEditor.commit();
        } else {
            google_i_pre = ADMOB_I1;
            nativeEditor.putInt("AdsInterSwipeCount", 2);
            nativeEditor.commit();
        }


        count_click_for_alt++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String adSequence[] = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String alernateAd[] = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String adSequence[] = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            Log.e(TAG, "LoadInterstitialAds Else: ");
        }

        if (interstitial_sequence.size() != 0) {
            FinalLoadInterstitial(interstitial_sequence.get(0));
        }

    }

    public void FinalLoadInterstitial(String platform) {
        Log.d(TAG, "FinalLoadInterstitial interstitial_sequence: " + platform);

        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (!google_i_pre.isEmpty() && mInterstitialAd == null) {

                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(activity, google_i_pre, adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                loadInterstitialAd(activity);
                                interstitialCallBack();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                loadNextInterstitialPlatform();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
//                        loadInterstitialAd(activity);
                        loadNextInterstitialPlatform();
                    }
                });

            }
        } else if (platform.equals("AppLovin") && applovin_AdStatus == 1) {
            if (appLovin_interstitialAd == null || !appLovin_interstitialAd.isReady()) {

                appLovin_interstitialAd = new MaxInterstitialAd(APPLOVIN_I1, activity);
                appLovin_interstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {

                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {
                        appLovin_interstitialAd = null;
                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        loadInterstitialAd(activity);
                        interstitialCallBack();
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {
                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        appLovin_interstitialAd = null;
                        loadNextInterstitialPlatform();
                        Log.e(TAG, "onAdLoadFailed: " + error.getAdLoadFailureInfo());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        Log.e(TAG, "onAdDisplayFailed: " + error.getAdLoadFailureInfo());
                        loadNextInterstitialPlatform();
                    }
                });
                appLovin_interstitialAd.loadAd();

            } else if (platform.equals(CustomAd) && customAd_AdStatus == 1) {
                Log.d(TAG, "FinalLoadInterstitial: Loaded Custom Interstitial");
            } else if (platform.equals(BrowserAd) && browserAd_AdStatus == 1) {
                Log.d(TAG, "FinalLoadInterstitial: Loaded BrowserAd Interstitial");
            }else if (platform.equals(WebViewAd) && webview_AdStatus == 1) {
                Log.d(TAG, "FinalLoadInterstitial: Loaded WebViewAd Interstitial");
            }
        }

    }

    private void loadNextInterstitialPlatform() {

        if (interstitial_sequence.size() != 0) {
            interstitial_sequence.remove(0);

            if (interstitial_sequence.size() != 0 && interstitial_sequence.get(0) != null) {
                FinalLoadInterstitial(interstitial_sequence.get(0));
            } else {
                Log.d(TAG, "loadNextInterstitialPlatform: " + interstitial_sequence);
            }

        }
    }

    public void showInterstitialAd(Context context, MyInterStitialCallback myCallback) {
        turnInterstitialAd(context, myCallback, 0);
    }

    public void showInterstitialAd(Context context, MyInterStitialCallback myCallback, int how_many_clicks) {
        turnInterstitialAd(context, myCallback, how_many_clicks);

    }

    public void turnInterstitialAd(Context context, MyInterStitialCallback myCallback2, int how_many_clicks) {
        intermyCallback = myCallback2;


        count_click++;

        if (AppManage.getInstance(activity).IsPurchased()) {
            if (intermyCallback != null) {
                intermyCallback.callbackCall();
                intermyCallback = null;
            }
            return;
        }

        if (app_adShowStatus == 0) {
            if (intermyCallback != null) {
                intermyCallback.callbackCall();
                intermyCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (intermyCallback != null) {
                    intermyCallback.callbackCall();
                    intermyCallback = null;
                }
                return;
            }
        }


        if (interstitial_sequence.size() != 0) {
            displayInterstitialAd(interstitial_sequence.get(0), context);
        } else {
            interstitialCallBack();
        }

    }

    private void displayInterstitialAd(String platform, final Context context) {
        dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialognew, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (app_dialogBeforeAdShow == 1) {
                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show((Activity) context);
                        } else {
                            interstitialCallBack();
                            loadInterstitialAd(activity);
                        }

                    }
                }.start();

            } else {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show((Activity) context);
                } else {
                    interstitialCallBack();
                    loadInterstitialAd(activity);
                }
            }
        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {
            if (app_dialogBeforeAdShow == 1) {
                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        if (appLovin_interstitialAd.isReady()) {
                            appLovin_interstitialAd.showAd();
                        } else {
                            interstitialCallBack();
                            loadInterstitialAd(activity);
                        }

                    }
                }.start();

            } else {
                if (appLovin_interstitialAd.isReady()) {
                    appLovin_interstitialAd.showAd();
                } else {
                    interstitialCallBack();
                    loadInterstitialAd(activity);
                }
            }
        } else if (platform.equals("CustomAd") && customAd_AdStatus == 1) {

            if (app_dialogBeforeAdShow == 1) {

                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        activity.startActivity(new Intent(activity, CustomAdActivity.class));
                    }
                }.start();

            } else {
                activity.startActivity(new Intent(activity, CustomAdActivity.class));
            }

        } else if (platform.equals(WebViewAd) && webview_AdStatus == 1) {

            if (app_dialogBeforeAdShow == 1) {

                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        activity.startActivity(new Intent(activity, WebViewActivity.class).putExtra("adType", "Reward"));
                    }
                }.start();

            } else {
                activity.startActivity(new Intent(activity, WebViewActivity.class).putExtra("adType", "Reward"));
            }

        } else if (platform.equals(BrowserAd) && browserAd_AdStatus == 1) {


            if (app_dialogBeforeAdShow == 1) {

                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        showCustomAds();
                    }
                }.start();

            } else {
                showCustomAds();
            }

        } else {
            interstitialCallBack();
            loadInterstitialAd(activity);
        }
    }


    public void showInterstitialAd_Custom(Context context, MyInterStitialCallback myCallback){
        intermyCallback = myCallback;
        activity.startActivity(new Intent(activity, CustomAdActivity.class));
    }


    public void showInterstitialAd_splash(Context context, MyInterStitialCallback myCallback, int how_many_clicks) {
        turnInterstitialAd_splash(context, myCallback, how_many_clicks);

    }


    public void turnInterstitialAd_splash(Context context, MyInterStitialCallback myCallback2, int how_many_clicks) {
        intermyCallback = myCallback2;

        count_click++;

        if (app_adShowStatus == 0) {
            if (intermyCallback != null) {
                intermyCallback.callbackCall();
                intermyCallback = null;
            }
            return;
        }

        if (AppManage.getInstance(activity).IsPurchased()) {
            if (intermyCallback != null) {
                intermyCallback.callbackCall();
                intermyCallback = null;
            }
            return;
        }


        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (intermyCallback != null) {
                    intermyCallback.callbackCall();
                    intermyCallback = null;
                }
                return;
            }
        }


        if (interstitial_sequence.size() != 0) {
            displayInterstitialAd_splash(interstitial_sequence.get(0), context);
        } else {
            interstitialCallBack();
        }

    }

    private void displayInterstitialAd_splash(String platform, final Context context) {

        dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialognew, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
       /* for (int i = 0; i < interstitial_sequence.size(); i++) {
            Log.d(TAG, "displayInterstitialAd_splash:seq "+interstitial_sequence.get(i));
        }*/
        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (app_dialogBeforeAdShow == 1) {
                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show((Activity) context);
                        } else {
                            interstitialCallBack();
                            loadInterstitialAd(activity);
                        }

                    }
                }.start();

            } else {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show((Activity) context);
                } else {
                    interstitialCallBack();
                    loadInterstitialAd(activity);
                }
            }
        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {
            if (app_dialogBeforeAdShow == 1) {
                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        if (appLovin_interstitialAd.isReady()) {
                            appLovin_interstitialAd.showAd();
                        } else {
                            interstitialCallBack();
                            loadInterstitialAd(activity);
                        }

                    }
                }.start();

            } else {
                if (appLovin_interstitialAd.isReady()) {
                    appLovin_interstitialAd.showAd();
                } else {
                    interstitialCallBack();
                    loadInterstitialAd(activity);
                }
            }
        } else if (platform.equals("CustomAd") && customAd_AdStatus == 1) {

            if (app_dialogBeforeAdShow == 1) {

                dialog.show();

                new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        activity.startActivity(new Intent(activity, CustomAdActivity.class));
                    }
                }.start();

            } else {
                activity.startActivity(new Intent(activity, CustomAdActivity.class));
            }

        } else if (platform.equals(BrowserAd) && browserAd_AdStatus == 1) {
            Log.d(TAG, "displayInterstitialAd_splash: replace br to admob");

            if (interstitial_sequence.size() != 0) {
                interstitial_sequence.remove(0);

                if (interstitial_sequence.size() != 0 && interstitial_sequence.get(0) != null) {
                    FinalLoadInterstitial(interstitial_sequence.get(0));
                    platform = interstitial_sequence.get(0);
                }
            }
            String finalPlatform = platform;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalPlatform.equals("Admob") && admob_AdStatus == 1) {
                        if (app_dialogBeforeAdShow == 1) {
                            dialog.show();

                            new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                                }

                                @Override
                                public void onFinish() {
                                    dialog.dismiss();
                                    if (mInterstitialAd != null) {
                                        mInterstitialAd.show((Activity) context);
                                    } else {
                                        interstitialCallBack();
                                        loadInterstitialAd(activity);
                                    }

                                }
                            }.start();

                        } else {
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show((Activity) context);
                            } else {
                                interstitialCallBack();
                                loadInterstitialAd(activity);
                            }
                        }
                    } else if (finalPlatform.equals(APPLOVIN) && applovin_AdStatus == 1) {
                        if (app_dialogBeforeAdShow == 1) {
                            dialog.show();

                            new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                                }

                                @Override
                                public void onFinish() {
                                    dialog.dismiss();
                                    if (appLovin_interstitialAd.isReady()) {
                                        appLovin_interstitialAd.showAd();
                                    } else {
                                        interstitialCallBack();
                                        loadInterstitialAd(activity);
                                    }

                                }
                            }.start();

                        } else {
                            if (appLovin_interstitialAd.isReady()) {
                                appLovin_interstitialAd.showAd();
                            } else {
                                interstitialCallBack();
                                loadInterstitialAd(activity);
                            }
                        }
                    } else if (finalPlatform.equals("CustomAd") && customAd_AdStatus == 1) {

                        if (app_dialogBeforeAdShow == 1) {

                            dialog.show();

                            new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                                }

                                @Override
                                public void onFinish() {
                                    dialog.dismiss();
                                    activity.startActivity(new Intent(activity, CustomAdActivity.class));
                                }
                            }.start();

                        } else {
                            activity.startActivity(new Intent(activity, CustomAdActivity.class));
                        }

                    } else {
                        if (admob_AdStatus == 1) {
                            if (!google_i_pre.isEmpty() /*&& mInterstitialAd == null*/) {

                                AdRequest adRequest = new AdRequest.Builder().build();

                                InterstitialAd.load(activity, google_i_pre, adRequest, new InterstitialAdLoadCallback() {
                                    @Override
                                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                        // The mInterstitialAd reference will be null until
                                        // an ad is loaded.
                                        mInterstitialAd = interstitialAd;
                                        Log.i(TAG, "2onAdLoaded");
                                        mInterstitialAd.show((Activity) context);
                                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                            @Override
                                            public void onAdDismissedFullScreenContent() {
                                                // Called when fullscreen content is dismissed.
                                                Log.d("TAG", "The ad was dismissed.");
                                                loadInterstitialAd(activity);
                                                interstitialCallBack();
                                            }

                                            @Override
                                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                                // Called when fullscreen content failed to show.
                                                Log.d("TAG", "The ad failed to show.");
                                                interstitialCallBack();
                                                loadInterstitialAd(activity);

                                            }

                                            @Override
                                            public void onAdShowedFullScreenContent() {
                                                // Called when fullscreen content is shown.
                                                // Make sure to set your reference to null so you don't
                                                // show it a second time.
                                                mInterstitialAd = null;
                                                Log.d("TAG", "The ad was shown.");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                        // Handle the error
                                        Log.i(TAG, loadAdError.getMessage());
                                        interstitialCallBack();
                                        loadInterstitialAd(activity);

                                        mInterstitialAd = null;
                                    }
                                });

                            }
                        } else {
                            interstitialCallBack();
                            loadInterstitialAd(activity);

                        }

                    }

                }
            }, 2000);
        } else {
            interstitialCallBack();
            loadInterstitialAd(activity);
        }
    }


    public void showCustomAds() {

        SharedPreferences.Editor customAdsEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsBrowserSwipeCount", -1) == 1) {
            BROWSERAD_URL = BROWSER_I1;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 2);
            customAdsEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBrowserSwipeCount", -1) == 2) {
            BROWSERAD_URL = BROWSER_I2;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 3);
            customAdsEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBrowserSwipeCount", -1) == 3) {
            BROWSERAD_URL = BROWSER_I3;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 4);
            customAdsEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBrowserSwipeCount", -1) == 4) {
            BROWSERAD_URL = BROWSER_I4;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 5);
            customAdsEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBrowserSwipeCount", -1) == 5) {
            BROWSERAD_URL = BROWSER_I5;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 1);
            customAdsEditor.commit();
        } else {
            BROWSERAD_URL = BROWSER_I1;
            customAdsEditor.putInt("AdsBrowserSwipeCount", 2);
            customAdsEditor.commit();
        }
        loadInterstitialAd(activity);
        if (CHROME_CUSTOM) {
            interstitialCallBack();

            if (!BROWSERAD_URL.isEmpty() && !BROWSERAD_URL.equals("PLACEMENT ID")) {
                try {

                    Intent intent = new Intent("android.intent.action.VIEW");
                    Bundle bundle = new Bundle();
                    bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
                    intent.putExtras(bundle);
                    intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", Color.parseColor("#000000"));
                    intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
                    intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
                    intent.setPackage("com.android.chrome");
                    intent.setData(Uri.parse(BROWSERAD_URL));
                    activity.startActivity(intent);
                } catch (Exception ignored1) {
                }
            }
        } else {
            Log.d(TAG, "showCustomAds:chorme ");
            if (!BROWSERAD_URL.isEmpty() && !BROWSERAD_URL.equals("PLACEMENT ID")) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    Bundle bundle = new Bundle();
                    bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
                    intent.putExtras(bundle);
                    intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", Color.parseColor("#000000"));
                    intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
                    intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
                    intent.setPackage("com.android.chrome");
                    intent.setData(Uri.parse(BROWSERAD_URL));
                    activity.startActivityForResult(intent, CHROME_CUSTOM_TAB_REQUEST_CODE);
                    AppManage.CHROME_CUSTOM = true;
                } catch (Exception ignored1) {
                }
            }
        }


    }

    public void interstitialCallBack() {

        if (intermyCallback != null) {
            intermyCallback.callbackCall();
            intermyCallback = null;
        }
    }

    public void showBanner(ViewGroup banner_container) {


        if (!hasActiveInternetConnection(activity)) {
            return;
        }

        if (app_adShowStatus == 0) {
            return;
        }

        if (AppManage.getInstance(activity).IsPurchased()) {
            return;
        }

        SharedPreferences.Editor nativeEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsBannerSwipeCount", -1) == 1) {
            this.admob_b = ADMOB_B1;
            nativeEditor.putInt("AdsBannerSwipeCount", 2);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBannerSwipeCount", -1) == 2) {
            this.admob_b = ADMOB_B2;
            nativeEditor.putInt("AdsBannerSwipeCount", 3);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBannerSwipeCount", -1) == 3) {
            this.admob_b = ADMOB_B3;
            nativeEditor.putInt("AdsBannerSwipeCount", 4);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBannerSwipeCount", -1) == 4) {
            this.admob_b = ADMOB_B4;
            nativeEditor.putInt("AdsBannerSwipeCount", 5);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsBannerSwipeCount", -1) == 5) {
            this.admob_b = ADMOB_B5;
            nativeEditor.putInt("AdsBannerSwipeCount", 1);
            nativeEditor.commit();
        } else {
            this.admob_b = ADMOB_B1;
            nativeEditor.putInt("AdsBannerSwipeCount", 2);
            nativeEditor.commit();
        }

        count_banner++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdBanner", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceBanner", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowBanner", "");


        banner_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String adSequence[] = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                banner_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String alernateAd[] = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_banner % alernateAd.length == j) {
                    index = j;
                    banner_sequence.add(alernateAd[index]);
                }
            }

            String adSequence[] = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (banner_sequence.size() != 0) {
                    if (!banner_sequence.get(0).equals(adSequence[j])) {
                        banner_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (banner_sequence.size() != 0) {
            displayBanner(banner_sequence.get(0), banner_container);
        }

    }

    public void displayBanner(String platform, ViewGroup banner_container) {
        banner_container.setVisibility(View.VISIBLE);
        if (platform.equals("Admob") && admob_AdStatus == 1) {
            showAdmobBanner(banner_container);
        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {
            showAppLovinBanner(banner_container);
        } else if (platform.equals(CustomAd) && customAd_AdStatus == 1) {
            banner_container.removeAllViews();
            CustomBannerAds.getInstance(activity).showCostomBannerAds(banner_container);
        } else {
            nextBannerPlatform(banner_container);
        }
    }

    private void nextBannerPlatform(ViewGroup banner_container) {
        if (banner_sequence.size() != 0) {
            banner_sequence.remove(0);
            if (banner_sequence.size() != 0) {
                displayBanner(banner_sequence.get(0), banner_container);
            }
        }
    }

    public static AdSize getAdSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    private void showAdmobBanner(final ViewGroup banner_container) {
        if (admob_b.isEmpty() || admob_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }

        final AdView mAdView = new AdView(activity);
        mAdView.setAdSize(getAdSize(activity));
        mAdView.setAdUnitId(admob_b);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                banner_container.removeAllViews();

                nextBannerPlatform(banner_container);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                banner_container.removeAllViews();
                banner_container.addView(mAdView);
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.d(TAG, "onAdImpression: AdmobBanner");
            }
        });

    }

    private void showAppLovinBanner(final ViewGroup banner_container) {
        if (applovin_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }

        MaxAdView adView = new MaxAdView(APPLOVIN_B1, activity);
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                Log.e(TAG, "onAdLoadFailed: " + error.getAdLoadFailureInfo());
                banner_container.removeAllViews();

                nextBannerPlatform(banner_container);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                Log.e(TAG, "onAdDisplayFailed: " + error.getAdLoadFailureInfo());
            }
        });


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = activity.getResources().getDimensionPixelSize(R.dimen.banner_height);
        adView.setLayoutParams(new FrameLayout.LayoutParams(width, height, Gravity.BOTTOM));
        adView.setBackgroundColor(Color.WHITE);
        banner_container.addView(adView);
        adView.loadAd();

    }

    public void showNative(ViewGroup nativeAdContainer, boolean b, int k) {

        if (app_adShowStatus == 0) {
            return;
        }

        SharedPreferences.Editor nativeEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 1) {
            this.admob_n = ADMOB_N1;
            nativeEditor.putInt("AdsNativeSwipeCount", 2);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 2) {
            this.admob_n = ADMOB_N2;
            nativeEditor.putInt("AdsNativeSwipeCount", 3);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 3) {
            this.admob_n = ADMOB_N3;
            nativeEditor.putInt("AdsNativeSwipeCount", 4);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 4) {
            this.admob_n = ADMOB_N4;
            nativeEditor.putInt("AdsNativeSwipeCount", 5);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 5) {
            this.admob_n = ADMOB_N5;
            nativeEditor.putInt("AdsNativeSwipeCount", 1);
            nativeEditor.commit();
        } else {
            this.admob_n = ADMOB_N1;
            nativeEditor.putInt("AdsNativeSwipeCount", 2);
            nativeEditor.commit();
        }

        count_native++;

        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdNative", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceNative", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowNative", "");


        native_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String adSequence[] = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                native_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String alernateAd[] = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_native % alernateAd.length == j) {
                    index = j;
                    native_sequence.add(alernateAd[index]);
                }
            }

            String adSequence[] = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (native_sequence.size() != 0) {
                    if (!native_sequence.get(0).equals(adSequence[j])) {
                        native_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (native_sequence.size() != 0) {
            displayNative(native_sequence.get(0), nativeAdContainer, b, k);
        }

    }

    public void displayNative(String platform, ViewGroup nativeAdContainer, boolean b, int k) {

        if (platform.equals("Admob") && admob_AdStatus == 1) {
            showAdmobNative(nativeAdContainer, b, k);
        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showAppLovinNative(nativeAdContainer, b, k);
                }
            });

        } else if (platform.equals(CustomAd) && customAd_AdStatus == 1) {
            nativeAdContainer.setVisibility(View.VISIBLE);
            CustomNativeAds.getInstance(activity).showCostomNativeAds(nativeAdContainer, b, k);
        } else {
            nextNativePlatform(nativeAdContainer, b, k);
        }
    }

    private void nextNativePlatform(ViewGroup nativeAdContainer, boolean b, int k) {
        if (native_sequence.size() != 0) {
            native_sequence.remove(0);
            if (native_sequence.size() != 0) {
                displayNative(native_sequence.get(0), nativeAdContainer, b, k);
            }
        }
    }

    private void showAdmobNative(final ViewGroup nativeAdContainer, boolean b, int k) {
        if (admob_n.isEmpty() || admob_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer, b, k);
            return;
        }

        final AdLoader adLoader = new AdLoader.Builder(activity, admob_n)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        // Show the ad.
                        new Inflate_ADS(activity).inflate_NATIV_ADMOB(nativeAd, nativeAdContainer, b, k);

                        Log.d(TAG, "onAdImpression: AdmobNative");

                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        nextNativePlatform(nativeAdContainer, b, k);
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    private MaxAd nativeAd;
    private MaxNativeAdLoader nativeAdLoader;

    private void showAppLovinNative(final ViewGroup nativeAdContainer, boolean b, int k) {
        if (applovin_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer, b, k);
            return;
        }

        nativeAdLoader = new MaxNativeAdLoader(APPLOVIN_N1, activity);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }
                nativeAdContainer.setVisibility(View.VISIBLE);
//                nativeAdContainer.setBackgroundColor(activity.getResources().getColor(R.color.colorWhite));
                // Save ad for cleanup.
                nativeAd = ad;
                // Add ad view to view.
                nativeAdContainer.removeAllViews();

                if (b) {
                    final float scale = activity.getResources().getDisplayMetrics().density;
                    int pixels = (int) (260 * scale + 0.5f);

                    nativeAdContainer.getLayoutParams().height = pixels;
                    nativeAdContainer.requestLayout();

//                    nativeAdContainer.getLayoutParams().height = 600;
//                    nativeAdContainer.requestLayout();
                }
                nativeAdContainer.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                Log.e(TAG, "onNativeAdLoadFailed: " + error.getMessage());
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                // Optional click callback
            }
        });
        nativeAdLoader.loadAd();

    }

    NativeAd preNativeAd = null;

    MaxNativeAdView prenativeAdView;

    public void PreLoadNative() {

        if (app_adShowStatus == 0) {
            return;
        }

        /*if (AppManage.getInstance(activity).IsPurchased()) {
            return;
        }
*/
        SharedPreferences.Editor nativeEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 1) {
            this.admob_n = ADMOB_N1;
            nativeEditor.putInt("AdsNativeSwipeCount", 2);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 2) {
            this.admob_n = ADMOB_N2;
            nativeEditor.putInt("AdsNativeSwipeCount", 3);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 3) {
            this.admob_n = ADMOB_N3;
            nativeEditor.putInt("AdsNativeSwipeCount", 4);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 4) {
            this.admob_n = ADMOB_N4;
            nativeEditor.putInt("AdsNativeSwipeCount", 5);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsNativeSwipeCount", -1) == 5) {
            this.admob_n = ADMOB_N5;
            nativeEditor.putInt("AdsNativeSwipeCount", 1);
            nativeEditor.commit();
        } else {
            this.admob_n = ADMOB_N1;
            nativeEditor.putInt("AdsNativeSwipeCount", 2);
            nativeEditor.commit();
        }

        count_native++;

        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdNative", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceNative", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowNative", "");


        native_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String adSequence[] = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                native_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String alernateAd[] = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_native % alernateAd.length == j) {
                    index = j;
                    native_sequence.add(alernateAd[index]);
                }
            }

            String adSequence[] = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (native_sequence.size() != 0) {
                    if (!native_sequence.get(0).equals(adSequence[j])) {
                        native_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (native_sequence.size() != 0) {
            LoadPreloadNative(native_sequence.get(0));
        }


    }


    public void LoadPreloadNative(String platform) {

        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (admob_n.isEmpty() || admob_AdStatus == 0) {
                return;
            }

            if (preNativeAd == null) {
                final AdLoader adLoader = new AdLoader.Builder(activity, admob_n)
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                // Show the ad.
                                preNativeAd = nativeAd;

                                Log.d(TAG, "PreLoadNative: AdmobNative");

                            }
                        })
                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                nextPreNativePlatform();
                                // Handle the failure by logging, altering the UI, and so on.
                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder()
                                // Methods in the NativeAdOptions.Builder class can be
                                // used here to specify individual options settings.
                                .build())
                        .build();

                adLoader.loadAd(new AdRequest.Builder().build());
            } else {
                Log.d(TAG, "PreLoadNative: AlreadyLoded");
            }
        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {

            if (prenativeAdView == null) {
                nativeAdLoader = new MaxNativeAdLoader(APPLOVIN_N1, activity);
                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                    @Override
                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                        prenativeAdView = nativeAdView;
                    }

                    @Override
                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                        Log.e(TAG, "onNativeAdLoadFailed: " + error.getMessage());
                        nextPreNativePlatform();
                    }

                    @Override
                    public void onNativeAdClicked(final MaxAd ad) {
                        // Optional click callback
                    }
                });
                nativeAdLoader.loadAd();
            } else {
                Log.d(TAG, "PreLoadNative: AppLovinAlreadyLoded");
            }
        } else if (platform.equals(CustomAd) && customAd_AdStatus == 1) {
            Log.d(TAG, "LoadPreloadNative:  CustomeNative");
        } else {
            nextPreNativePlatform();
        }
    }

    private void nextPreNativePlatform() {
        if (native_sequence.size() != 0) {
            native_sequence.remove(0);
            if (native_sequence.size() != 0) {
                LoadPreloadNative(native_sequence.get(0));
            }
        }
    }


    public void PreLoadShowNative(ViewGroup nativeAdContainer, boolean b, int k) {


        if (native_sequence.size() != 0) {
            if (preNativeAd != null && native_sequence.get(0).equals(ADMOB) && admob_AdStatus == 1) {
                Log.d(TAG, "PreLoadNative: ShowLoded");
                new Inflate_ADS(activity).inflate_NATIV_ADMOB(preNativeAd, nativeAdContainer, b, k);
            } else if (prenativeAdView != null && native_sequence.get(0).equals(APPLOVIN) && applovin_AdStatus == 1) {
                Log.d(TAG, "PreLoadNative: AppLovinShowLoded");
                nativeAdContainer.setVisibility(View.VISIBLE);
                nativeAdContainer.setBackgroundColor(activity.getResources().getColor(R.color.colorWhite));

                nativeAdContainer.removeAllViews();
                if (b) {
                    final float scale = activity.getResources().getDisplayMetrics().density;
                    int pixels = (int) (260 * scale + 0.5f);

                    nativeAdContainer.getLayoutParams().height = pixels;
                    nativeAdContainer.requestLayout();

//                    nativeAdContainer.getLayoutParams().height = 450;
//                    nativeAdContainer.requestLayout();
                }
                try {
                    nativeAdContainer.addView(prenativeAdView);
                } catch (Exception e) {
                    if (prenativeAdView.getParent() != null) {
                        ((ViewGroup) prenativeAdView.getParent()).removeView(prenativeAdView); // <- fix
                    }
                    nativeAdContainer.addView(prenativeAdView);
                    Log.d(TAG, "PreLoadNative applovin : " + e.getMessage());
                }
            } else if (native_sequence.get(0).equals(CustomAd) && customAd_AdStatus == 1) {
                nativeAdContainer.setVisibility(View.VISIBLE);
                CustomNativeAds.getInstance(activity).showCostomNativeAds(nativeAdContainer, b, k);
            } else {
                Log.d(TAG, "PreLoadNative: NotLoded");
                PreLoadNative();
            }
        }

    }

    public void showmgl_Game(final ImageView mglAdContainer) {
        if (MGLGAME_APPID.isEmpty() || mglgame_AdStatus == 0) {
            mglAdContainer.setVisibility(View.GONE);
            return;
        } else {

            if (!MGLGAMEGAME_B1.isEmpty() && !MGLGAMEGAME_B1.equals("PLACEMENT ID")) {
                Glide.with(activity).load(MGLGAMEGAME_B1).into(mglAdContainer);
            }

            mglAdContainer.setVisibility(View.VISIBLE);
            mglAdContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                                .setShowTitle(false)
                                .enableUrlBarHiding()
                                .setToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                                .setSecondaryToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                                .build();
                        customTabsIntent.intent.setPackage("com.android.chrome");
                        customTabsIntent.launchUrl(activity, Uri.parse(MGLGAME_APPID));
                    } catch (Exception ignored1) {
                        Log.e(TAG, "showQureka onClick: " + ignored1);
                    }
                }
            });

        }
    }

    public void showpredchamp_Game(final ImageView predchampAdContainer) {
        if (PREDCHAMPGAME_APPID.isEmpty() || predchampgame_AdStatus == 0) {
            predchampAdContainer.setVisibility(View.GONE);
            return;
        } else {

            if (!PREDCHAMPGAME_B1.isEmpty() && !PREDCHAMPGAME_B1.equals("PLACEMENT ID")) {
                Glide.with(activity).load(PREDCHAMPGAME_B1).into(predchampAdContainer);
            }

            predchampAdContainer.setVisibility(View.VISIBLE);
            predchampAdContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                                .setShowTitle(false)
                                .enableUrlBarHiding()
                                .setToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                                .setSecondaryToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                                .build();
                        customTabsIntent.intent.setPackage("com.android.chrome");
                        customTabsIntent.launchUrl(activity, Uri.parse(PREDCHAMPGAME_APPID));
                    } catch (Exception ignored1) {
                        Log.e(TAG, "showQureka onClick: " + ignored1);
                    }
                }
            });

        }
    }

    public void showLoadRewardAd(Context context, MyRewardAdCallback myRewardCallback) {
        loadAndNRewardAd(context, myRewardCallback, 0);
    }

    public void showLoadRewardAd(Context context, MyRewardAdCallback myRewardCallback, int how_many_clicks) {
        loadAndNRewardAd(context, myRewardCallback, how_many_clicks);
    }

    protected void loadAndNRewardAd(Context context, MyRewardAdCallback myCallback2, int how_many_clicks) {
        if (!hasActiveInternetConnection(context)) {
            Toast.makeText(activity, "Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }

        rewardmyCallback = myCallback2;

        rewardcount_click++;

        if (app_adShowStatus == 0) {
            if (rewardmyCallback != null) {
                rewardmyCallback.callbackCall();
                rewardmyCallback = null;
            }
            return;
        }

        if (how_many_clicks != 0) {
            if (rewardcount_click % how_many_clicks != 0) {
                if (rewardmyCallback != null) {
                    rewardmyCallback.callbackCall();
                    rewardmyCallback = null;
                }
                return;
            }
        }

        SharedPreferences.Editor nativeEditor = mysharedpreferences.edit();

        if (mysharedpreferences.getInt("AdsRewardSwipeCount", -1) == 1) {
            google_rw_pre = ADMOB_RW1;
            nativeEditor.putInt("AdsRewardSwipeCount", 2);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsRewardSwipeCount", -1) == 2) {
            google_rw_pre = ADMOB_RW2;
            nativeEditor.putInt("AdsRewardSwipeCount", 3);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsRewardSwipeCount", -1) == 3) {
            google_rw_pre = ADMOB_RW3;
            nativeEditor.putInt("AdsRewardSwipeCount", 4);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsRewardSwipeCount", -1) == 4) {
            google_rw_pre = ADMOB_RW4;
            nativeEditor.putInt("AdsRewardSwipeCount", 5);
            nativeEditor.commit();
        } else if (mysharedpreferences.getInt("AdsRewardSwipeCount", -1) == 5) {
            google_rw_pre = ADMOB_RW5;
            nativeEditor.putInt("AdsRewardSwipeCount", 1);
            nativeEditor.commit();
        } else {
            google_rw_pre = ADMOB_RW1;
            nativeEditor.putInt("AdsRewardSwipeCount", 2);
            nativeEditor.commit();
        }


        reward_count_for_alt++;

        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        Log.d(TAG, alernateAdShow + "- loadAndNRewardAd: " + adPlatformSequence);
        reward_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String adSequence[] = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                reward_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String alernateAd[] = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (reward_count_for_alt % alernateAd.length == j) {
                    index = j;
                    reward_sequence.add(alernateAd[index]);
                }
            }

            String adSequence[] = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (reward_sequence.size() != 0) {
                    if (!reward_sequence.get(0).equals(adSequence[j])) {
                        reward_sequence.add(adSequence[j]);
                    }
                }
            }
        } else {
            Log.e(TAG, "loadRewardAd Else: ");
        }


        if (reward_sequence.size() != 0) {
            FinalLoadShowReward(reward_sequence.get(0));
        }

    }
    protected void FinalLoadShowReward1(String platform) {

        dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        Log.d(TAG, "- loadAndNRewardAd platform: " + platform);
        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (!google_rw_pre.isEmpty()) {
                dialog.show();
                AdRequest adRequest = new AdRequest.Builder().build();
                RewardedAd.load(activity, google_rw_pre, adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        dialog.dismiss();
                        loadShowNextRewardPlatform();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Log.d(TAG, "The user earned the reward.");
                                ISRewardErned = true;
                                dialog.dismiss();
                            }
                        });
                        Log.d(TAG, "Ad was loaded.");
                        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");

                                dialog.dismiss();

                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {

                                dialog.dismiss();

                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");
                                if (ISRewardErned) {
                                    ISRewardErned = false;
                                    rewardCallBack();
                                }
                                super.onAdDismissedFullScreenContent();
                            }
                        });
                    }
                });

            }

        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {
            if (!APPLOVIN_R1.isEmpty()) {
                dialog.show();
                maxRewardedAd = MaxRewardedAd.getInstance(APPLOVIN_R1, activity);
                maxRewardedAd.loadAd();

                maxRewardedAd.setListener(new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {
                        Log.d(TAG, "onRewardedVideoStarted: ");
                        dialog.dismiss();
                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        Log.d(TAG, "onRewardedVideoCompleted: ");

                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {
                        Log.d(TAG, "onUserRewarded: ");
                        ISRewardErned = true;
                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        Log.d(TAG, "onAdLoaded: ");
                        if (maxRewardedAd.isReady()) {
                            maxRewardedAd.showAd();
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {
                        Log.d(TAG, "onAdDisplayed: ");

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        Log.d(TAG, "onAdHidden: ");
                        if (ISRewardErned) {
                            ISRewardErned = false;
                            rewardCallBack();
                        }
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {
                        Log.d(TAG, "onAdClicked: ");
                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        dialog.dismiss();
                        Log.e(TAG, "onAdLoadFailed: " + error);
                        loadShowNextRewardPlatform();
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        dialog.dismiss();
                        Log.e(TAG, "onRewardedVideoAdShowFailed: " + error);
                        loadShowNextRewardPlatform();
                    }
                });


                /*} else {

//                    dialog.dismiss();
                    maxRewardedAd = MaxRewardedAd.getInstance(APPLOVIN_R1, activity);
                    loadShowNextRewardPlatform();

                }*/
            }
        } else if (platform.equals(CustomAd) || platform.equals(BrowserAd)) {
            loadShowNextRewardPlatform();

        } else {

            dialog.dismiss();
            Toast.makeText(activity, "Video Ads disabled yet, please try later", Toast.LENGTH_SHORT).show();

        }
    }

    protected void FinalLoadShowReward(String platform) {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(1);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialognew, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Log.d(TAG, "- loadAndNRewardAd platform: " + platform);
        if (platform.equals("Admob") && admob_AdStatus == 1) {
            if (!google_rw_pre.isEmpty()) {
                dialog.show();
                AdRequest adRequest = new AdRequest.Builder().build();
                RewardedAd.load(activity, google_rw_pre, adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        dialog.dismiss();
                        loadShowNextRewardPlatform();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Log.d(TAG, "The user earned the reward.");
                                ISRewardErned = true;
                                dialog.dismiss();
                            }
                        });
                        Log.d(TAG, "Ad was loaded.");
                        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");

                                dialog.dismiss();

                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {

                                dialog.dismiss();

                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                dialog.dismiss();
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");
                                if (ISRewardErned) {
                                    ISRewardErned = false;
                                    rewardCallBack();
                                }
                                super.onAdDismissedFullScreenContent();
                            }
                        });
                    }
                });

            }

        } else if (platform.equals(APPLOVIN) && applovin_AdStatus == 1) {

            if (!APPLOVIN_R1.isEmpty()) {

                dialog.show();
                maxRewardedAd = MaxRewardedAd.getInstance(APPLOVIN_R1, activity);
                maxRewardedAd.loadAd();

//                if (maxRewardedAd.isReady()) {
//                    maxRewardedAd.showAd();
//                    Toast.makeText(activity, "...", Toast.LENGTH_SHORT).show();
//
//                }

                if (maxRewardedAd.isReady()) {

                    maxRewardedAd.setListener(new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {
                            Log.d(TAG, "onRewardedVideoStarted: ");
                            dialog.dismiss();
                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            Log.d(TAG, "onRewardedVideoCompleted: ");
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {
                            Log.d(TAG, "onUserRewarded: ");
//                        if (ISRewardErned) {
                            ISRewardErned = true;
//                            rewardCallBack();
//                        }
                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {
                            Log.d(TAG, "onAdLoaded: ");
                            if (maxRewardedAd.isReady()) {
                                maxRewardedAd.showAd();
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {
                            Log.d(TAG, "onAdDisplayed: ");

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            Log.d(TAG, "onAdHidden: ");
                            if (ISRewardErned) {
                                ISRewardErned = false;
                                rewardCallBack();
                            }
                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {
                            Log.d(TAG, "onAdClicked: ");
                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            Log.d(TAG, "Reward failedToReceiveAd: ");
                            dialog.dismiss();
                            loadShowNextRewardPlatform();
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            Log.d(TAG, "Reward failedToReceiveAd: ");
                            dialog.dismiss();
                            loadShowNextRewardPlatform();
                        }
                    });
                    maxRewardedAd.showAd();
                } else {
//                    dialog.dismiss();
//                    loadShowNextRewardPlatform();

                    maxRewardedAd.loadAd();
                    maxRewardedAd.setListener(new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {
                            Log.d(TAG, "onRewardedVideoStarted: ");
                            dialog.dismiss();
                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            Log.d(TAG, "onRewardedVideoCompleted: ");
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {
                            Log.d(TAG, "onUserRewarded: ");
//                        if (ISRewardErned) {
                            ISRewardErned = true;
//                            rewardCallBack();
//                        }
                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {
                            Log.d(TAG, "onAdLoaded: ");
                            if (maxRewardedAd.isReady()) {
                                maxRewardedAd.showAd();
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {
                            Log.d(TAG, "onAdDisplayed: ");

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            Log.d(TAG, "onAdHidden: ");
                            if (ISRewardErned) {
                                ISRewardErned = false;
                                rewardCallBack();
                            }
                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {
                            Log.d(TAG, "onAdClicked: ");
                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            Log.d(TAG, "Reward failedToReceiveAd: ");
                            dialog.dismiss();
                            loadShowNextRewardPlatform();
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            Log.d(TAG, "Reward failedToReceiveAd: ");
                            dialog.dismiss();
                            loadShowNextRewardPlatform();
                        }
                    });
                }



            }
        } else if (platform.equals(CustomAd) || platform.equals(BrowserAd)) {
            loadShowNextRewardPlatform();

        } else {
            dialog.dismiss();
            Toast.makeText(activity, "Video Ads disabled yet, please try later1", Toast.LENGTH_SHORT).show();

        }
    }

    private void loadShowNextRewardPlatform() {

        if (reward_sequence.size() != 0) {
            reward_sequence.remove(0);

            if (reward_sequence.size() != 0) {
                FinalLoadShowReward(reward_sequence.get(0));
            } else {
                Toast.makeText(activity, "Video Ads disabled yet, please try later...", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "loadnextRewardPlatform: " + reward_sequence);
            }
        }
    }

    public interface MyInterStitialCallback {
        void callbackCall();
    }

    public interface MyRewardAdCallback {
        void callbackCall();
    }

    public void savePurchases(boolean state) {
        SharedPreferences.Editor editor1 = mysharedpreferences.edit();
        editor1.putBoolean("ISPURCHASED", state);
        editor1.commit();
    }

    public void rewardCallBack() {

        if (rewardmyCallback != null) {
            rewardmyCallback.callbackCall();
            rewardmyCallback = null;
        }
    }

    public boolean IsPurchased() {
        return mysharedpreferences.getBoolean("ISPURCHASED", false);
    }

}
