package com.pesonal.adsdk;

import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ADS_SplashActivity extends AppCompatActivity {
    private static final String TAG = "ADS_SplashActivity";
    public static boolean need_internet = false;
    String bytemode = "https://signal.makemyapps.co/signal/796_callHistoryMainAppData.json";
    boolean is_retry;
    boolean is_splash_ad_loaded = false;
    boolean on_sucess = false;
    private Runnable runnable;
    private Handler refreshHandler;
    private AppOpenManager manager;

    public static String[] CustomB_TitleArray, CustomB_SubTitleArray, CustomB_IconUrlArray, CustomB_ThumbUrlArray, CustomB_RedirectUrlArray;
    public static String[] CustomN_TitleArray, CustomN_SubTitleArray, CustomN_IconUrlArray, CustomN_ThumbUrlArray, CustomN_RedirectUrlArray;
    public static String[] CustomI_TitleArray, CustomI_SubTitleArray, CustomI_IconUrlArray, CustomI_ThumbUrlArray, CustomI_RedirectUrlArray;
    public static String[] CustomA_TitleArray, CustomA_SubTitleArray, CustomA_IconUrlArray, CustomA_ThumbUrlArray, CustomA_RedirectUrlArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_splash);


    }

    public void ADSinit(final Activity activity, final int cversion, final getDataListner myCallback1) {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.retry_layout, null);
        dialog.setContentView(view);
        final TextView retry_buttton = view.findViewById(R.id.retry_buttton);

        final SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        final SharedPreferences.Editor editor_AD_PREF = preferences.edit();

        need_internet = preferences.getBoolean("need_internet", need_internet);

        if (!isNetworkAvailable() && need_internet) {
            is_retry = false;
            dialog.show();
        }

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        final boolean splash_ad = mysharedpreferences.getBoolean("app_AppOpenAdStatus", false);
        final String app_open_id = mysharedpreferences.getString("AppOpenID", "");
        if (splash_ad && !app_open_id.isEmpty() && isNetworkAvailable()) {
            loadAppOpenAd(activity, myCallback1);
        }


        dialog.dismiss();
        refreshHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                    is_retry = true;
                    retry_buttton.setText(activity.getString(R.string.retry));
                } else if (need_internet) {
                    dialog.show();
                    is_retry = false;
                    retry_buttton.setText(activity.getString(R.string.connect_internet));
                }
                refreshHandler.postDelayed(this, 1000);
            }
        };

        refreshHandler.postDelayed(runnable, 1000);

        retry_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_retry) {
                    if (need_internet) {
                        myCallback1.onReload();
                    } else {
                        myCallback1.onSuccess();
                    }


                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });

    /*
        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String currentDate = df.format(calender.getTime());


        int addfdsf123;
        String status = mysharedpreferences.getString("firsttime", "true");
        final SharedPreferences.Editor editor = mysharedpreferences.edit();
        if (status.equals("true")) {
            editor.putString("date", currentDate).apply();
            editor.putString("firsttime", "false").apply();
            addfdsf123 = 13421;


        } else {
            String date = mysharedpreferences.getString("date", "");
            if (!currentDate.equals(date)) {
                editor.putString("date", currentDate).apply();
                addfdsf123 = 26894;
            } else {
                addfdsf123 = 87332;
            }
        }

        String akbsvl679056 = "92099C16D0C139A1A7D0CBFFF20786C8FB782B72AA1DF71AA6D58F160310EB0E717E2DDE21C74DAC5B4AD320403590715F897D2CE28E441E2ED905B18ED38C3A";

        try {
            bytemode = AESSUtils.decryptA(activity, akbsvl679056);

        } catch (Exception e) {
            e.printStackTrace();
        }

        bytemode = bytemode + "?PHSUGSG6783019KG=" + activity.getPackageName();
        bytemode = bytemode + "&AFHJNTGDGD563200K=" + getKeyHash(activity);
        bytemode = bytemode + "&DTNHGNH7843DFGHBSA=" + addfdsf123;

        if (BuildConfig.DEBUG) {
            bytemode = bytemode + "&DBMNBXRY4500991G=TRSOFTAG12789I";
        } else {
            bytemode = bytemode + "&DBMNBXRY4500991G=TRSOFTAG82382I";
        }*/

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, bytemode, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status = response.getBoolean("STATUS");
                    if (status) {


                        String need_in = response.getJSONObject("APP_SETTINGS").getString("app_needInternet");
                        if (need_in.endsWith("1")) {
                            need_internet = true;
                        } else {
                            need_internet = false;
                        }
                        editor_AD_PREF.putBoolean("need_internet", need_internet).apply();
                        editor_AD_PREF.putString("MORE_APP", response.getJSONArray("MORE_APP").toString()).apply();
                        editor_AD_PREF.putString("MORE_APP_SPLASH", response.getJSONArray("MORE_APP_SPLASH").toString()).apply();
                        editor_AD_PREF.putString("MORE_APP_EXIT", response.getJSONArray("MORE_APP_EXIT").toString()).apply();

                        SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                        editor1.putString("response", response.toString());
                        editor1.apply();

                        JSONObject CAJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("CustomAd");
                        CustomB_TitleArray = CAJsonObject.getString("Banner1").split("#");
                        CustomB_SubTitleArray = CAJsonObject.getString("Banner2").split("#");
                        CustomB_IconUrlArray = CAJsonObject.getString("Banner3").split("#");
                        CustomB_ThumbUrlArray = CAJsonObject.getString("Banner4").split("#");
                        CustomB_RedirectUrlArray = CAJsonObject.getString("Banner5").split("#");

                        CustomN_TitleArray = CAJsonObject.getString("Native1").split("#");
                        CustomN_SubTitleArray = CAJsonObject.getString("Native2").split("#");
                        CustomN_IconUrlArray = CAJsonObject.getString("Native3").split("#");
                        CustomN_ThumbUrlArray = CAJsonObject.getString("Native4").split("#");
                        CustomN_RedirectUrlArray = CAJsonObject.getString("Native5").split("#");

                        CustomI_TitleArray = CAJsonObject.getString("Interstitial1").split("#");
                        CustomI_SubTitleArray = CAJsonObject.getString("Interstitial2").split("#");
                        CustomI_IconUrlArray = CAJsonObject.getString("Interstitial3").split("#");
                        CustomI_ThumbUrlArray = CAJsonObject.getString("Interstitial4").split("#");
                        CustomI_RedirectUrlArray = CAJsonObject.getString("Interstitial5").split("#");

                        CustomA_TitleArray = CAJsonObject.getString("RewardedVideo1").split("#");
                        CustomA_SubTitleArray = CAJsonObject.getString("RewardedVideo2").split("#");
                        CustomA_IconUrlArray = CAJsonObject.getString("RewardedVideo3").split("#");
                        CustomA_ThumbUrlArray = CAJsonObject.getString("RewardedVideo4").split("#");
                        CustomA_RedirectUrlArray = CAJsonObject.getString("RewardedVideo5").split("#");

                        List<AdModel> bannerArrayList = new ArrayList<>();
                        for (int i = 0; i < CustomB_TitleArray.length; i++) {
                            AdModel gVar = new AdModel();
                            gVar.setTitle(CustomB_TitleArray[i]);
                            gVar.setSubTitle(CustomB_SubTitleArray[i]);
                            gVar.setUrl(CustomB_IconUrlArray[i]);
                            gVar.setThumbnailUrl(CustomB_ThumbUrlArray[i]);
                            gVar.setRedirectUrl(CustomB_RedirectUrlArray[i]);
                            bannerArrayList.add(gVar);
                            Log.d("TAG1", "onResponse: json" + bannerArrayList);
                        }
                        saveListInLocal(bannerArrayList, "BANNER_KEY");

                        List<AdModel> nativeArrayList = new ArrayList<>();
                        for (int i = 0; i < CustomN_TitleArray.length; i++) {
                            AdModel gVar = new AdModel();
                            gVar.setTitle(CustomN_TitleArray[i]);
                            gVar.setSubTitle(CustomN_SubTitleArray[i]);
                            gVar.setUrl(CustomN_IconUrlArray[i]);
                            gVar.setThumbnailUrl(CustomN_ThumbUrlArray[i]);
                            gVar.setRedirectUrl(CustomN_RedirectUrlArray[i]);
                            nativeArrayList.add(gVar);
                            Log.d("TAG1", "nativeArrayList onResponse: json" + nativeArrayList);
                        }
                        saveListInLocal(nativeArrayList, "NATIVE_KEY");

                        List<AdModel> interArrayList = new ArrayList<>();
                        for (int i = 0; i < CustomI_TitleArray.length; i++) {
                            AdModel gVar = new AdModel();
                            gVar.setTitle(CustomI_TitleArray[i]);
                            gVar.setSubTitle(CustomI_SubTitleArray[i]);
                            gVar.setUrl(CustomI_IconUrlArray[i]);
                            gVar.setThumbnailUrl(CustomI_ThumbUrlArray[i]);
                            gVar.setRedirectUrl(CustomI_RedirectUrlArray[i]);
                            interArrayList.add(gVar);
                            Log.d("TAG1", "interArrayList onResponse: json" + interArrayList);
                        }
                        saveListInLocal(interArrayList, "INTERSTITIAL_KEY");

                        List<AdModel> appOpenArrayList = new ArrayList<>();
                        for (int i = 0; i < CustomA_TitleArray.length; i++) {
                            AdModel gVar = new AdModel();
                            gVar.setTitle(CustomA_TitleArray[i]);
                            gVar.setSubTitle(CustomA_SubTitleArray[i]);
                            gVar.setUrl(CustomA_IconUrlArray[i]);
                            gVar.setThumbnailUrl(CustomA_ThumbUrlArray[i]);
                            gVar.setRedirectUrl(CustomA_RedirectUrlArray[i]);
                            appOpenArrayList.add(gVar);
                            Log.d("TAG1", "appOpenArrayList onResponse: json" + appOpenArrayList);
                        }
                        saveListInLocal(appOpenArrayList, "APPOPEN_KEY");

                    } else {
                    }


                } catch (Exception e) {
                    Log.e(TAG, "onResponse Exception : "+e.getLocalizedMessage() );
                    if (need_internet) {
                        dialog.dismiss();
                        refreshHandler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (isNetworkAvailable()) {
                                    is_retry = true;
                                    retry_buttton.setText(activity.getString(R.string.retry));
                                } else {
                                    dialog.show();
                                    is_retry = false;
                                    retry_buttton.setText(activity.getString(R.string.connect_internet));
                                }
                                refreshHandler.postDelayed(this, 1000);
                            }
                        };
                    } else {
                        myCallback1.onSuccess();
                    }
                }

                AppManage.getInstance(activity).getResponseFromPref(new getDataListner() {
                    @Override
                    public void onSuccess() {
                      /*  boolean splash_ad2 = mysharedpreferences.getBoolean("app_AppOpenAdStatus", false);
                        String app_open_id2 = mysharedpreferences.getString("AppOpenID", "");
                        if (splash_ad && !app_open_id.isEmpty() && isNetworkAvailable()) {
                            if (is_splash_ad_loaded) {
                                manager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
                                    @Override
                                    public void onSuccess() {
                                        myCallback1.onSuccess();
                                    }

                                    @Override
                                    public void onError(String error) {
                                        myCallback1.onSuccess();
                                    }
                                });
                            } else {
                                on_sucess = true;
                            }
                        } else if (splash_ad2 && !app_open_id2.isEmpty() && isNetworkAvailable()) {
                            on_sucess = true;
                            loadAppOpenAd(activity, myCallback1);
                        }else if(splash_ad2 && app_open_id2.isEmpty() && isNetworkAvailable()){
                            CustomAppOpenAds customAppOpenAds=new CustomAppOpenAds(activity, R.style.Theme_AppOpen_Dialog);
                            customAppOpenAds.setCancelable(false);
                            customAppOpenAds.setCanceledOnTouchOutside(false);
                            customAppOpenAds.appOpenCallBack =new CustomAppOpenFun(myCallback1,customAppOpenAds);
                            customAppOpenAds.show();
                        } else {*/
                            myCallback1.onSuccess();
//                        }
                    }

                    @Override
                    public void onUpdate(String url) {
                        myCallback1.onUpdate(url);
                    }

                    @Override
                    public void onRedirect(String url) {
                        myCallback1.onRedirect(url);
                    }

                    @Override
                    public void onReload() {
                    }

                    @Override
                    public void onGetExtradata(String extraData) {
                        myCallback1.onGetExtradata(extraData);

                    }
                }, cversion);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (need_internet) {
                    dialog.dismiss();
                    refreshHandler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (isNetworkAvailable()) {
                                is_retry = true;
                                retry_buttton.setText(activity.getString(R.string.retry));
                            } else {
                                dialog.show();
                                is_retry = false;
                                retry_buttton.setText(activity.getString(R.string.connect_internet));
                            }
                            refreshHandler.postDelayed(this, 1000);
                        }
                    };
                } else {
                    myCallback1.onSuccess();
                }
            }
        });
        jsonObjectRequest.setShouldCache(false);
        requestQueue.add(jsonObjectRequest);
    }

    public void saveListInLocal(List<AdModel> list, String key) {
        SharedPreferences.Editor editor = mysharedpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    private void loadAppOpenAd(Activity activity, final getDataListner myCallback1) {

        manager = new AppOpenManager(activity);
        manager.fetchAd(new AppOpenManager.splshADlistner() {
            @Override
            public void onSuccess() {
                is_splash_ad_loaded = true;
                if (on_sucess) {
                    manager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
                        @Override
                        public void onSuccess() {
                            myCallback1.onSuccess();
                        }

                        @Override
                        public void onError(String error) {
                            myCallback1.onSuccess();
                        }
                    });
                }
            }

            @Override
            public void onError(String error) {
                is_splash_ad_loaded = true;
                if (on_sucess) {
                    manager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
                        @Override
                        public void onSuccess() {
                            myCallback1.onSuccess();
                        }

                        @Override
                        public void onError(String error) {
                            myCallback1.onSuccess();
                        }
                    });
                }

            }
        });
    }

    private String getKeyHash(Activity activity) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = (Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return something.replace("+", "*");
            }
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
        return null;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshHandler.removeCallbacks(runnable);
    }

}