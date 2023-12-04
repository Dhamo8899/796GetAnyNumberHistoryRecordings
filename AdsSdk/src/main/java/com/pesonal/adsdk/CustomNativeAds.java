package com.pesonal.adsdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomNativeAds {
    private static final String TAG = "CustomNativeAds";
    static Activity activity;
    List<AdModel> modelList = new ArrayList<>();

    SharedPreferences sharedPreferences;
    private static CustomNativeAds mInstance;
    public static int nativeDataSwipeCount = 0;

    public CustomNativeAds(Activity activity) {
        CustomNativeAds.activity = activity;
        sharedPreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
    }

    public static CustomNativeAds getInstance(Activity activity) {
        CustomNativeAds.activity = activity;
        if (mInstance == null) {
            mInstance = new CustomNativeAds(activity);
        }
        return mInstance;
    }


    public void showCostomNativeAds(ViewGroup nativeAdContainer, boolean b, int k) {

//        View viewLayout = View.inflate(activity, R.layout.ads_native_custom, null);
        View viewLayout = null;
        if (k == 0) {
            viewLayout = View.inflate(activity, R.layout.ads_native_custom_skip, null);

        } else if (k == 1) {
            viewLayout = View.inflate(activity, R.layout.ads_native_custom_adapter, null);

        }

        modelList = getListFromLocal("NATIVE_KEY");
        AdModel adModel = null;
        // ----- SwipeNativeDataStart------------
        SharedPreferences.Editor customAdsEditor = sharedPreferences.edit();
        nativeDataSwipeCount = sharedPreferences.getInt("AdsNativeCustomSwipeCount", 0);

        if (modelList.size() != 0) {
            if (modelList.size() <= nativeDataSwipeCount) {
                nativeDataSwipeCount = 0;
            }
            adModel = modelList.get(nativeDataSwipeCount);
            nativeDataSwipeCount++;
            customAdsEditor.putInt("AdsNativeCustomSwipeCount", nativeDataSwipeCount);
            customAdsEditor.apply();

        }
// ----- SwipeNativeData End------------

        ImageView ad_media = viewLayout.findViewById(R.id.ad_media);
        ImageView ad_app_icon = viewLayout.findViewById(R.id.ad_app_icon);
        TextView ad_headline = viewLayout.findViewById(R.id.ad_headline);
        TextView ad_body = viewLayout.findViewById(R.id.ad_body);
        TextView ad_call_to_action = viewLayout.findViewById(R.id.ad_call_to_action);
        Log.d(TAG, "showCostomNativeAds: " + modelList);

        GlideUrl ad_media_url = new GlideUrl(adModel.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        GlideUrl ad_app_icon_url1 = new GlideUrl(adModel.getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

        Glide.with(activity).asBitmap().load(ad_media_url).placeholder(R.mipmap.ic_launcher).into(ad_media);
        Glide.with(activity).asBitmap().load(ad_app_icon_url1).placeholder(R.mipmap.ic_launcher).into(ad_app_icon);
        ad_headline.setText(adModel.getTitle());
        ad_body.setText(adModel.getSubTitle());
        AdModel finalAdModel = adModel;
        ad_call_to_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                            .setShowTitle(false)
                            .enableUrlBarHiding()
                            .setToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                            .setSecondaryToolbarColor(activity.getResources().getColor(R.color.ad_text_primary))
                            .build();
                    customTabsIntent.intent.setPackage("com.android.chrome");
                    customTabsIntent.launchUrl(activity, Uri.parse(finalAdModel.getRedirectUrl()));
                } catch (Exception ignored1) {
                    Log.e(TAG, "CustomNativeAds onClick Error : " + ignored1.getLocalizedMessage());
                }
            }
        });

        nativeAdContainer.removeAllViews();
        nativeAdContainer.setVisibility(View.VISIBLE);

        if (b) {
            final float scale = activity.getResources().getDisplayMetrics().density;
            int pixels = (int) (260 * scale + 0.5f);
            nativeAdContainer.getLayoutParams().height = pixels;
            nativeAdContainer.requestLayout();
            nativeAdContainer.addView(viewLayout);
        }else {
            nativeAdContainer.addView(viewLayout);
        }//        return viewLayout;
    }

    public List<AdModel> getListFromLocal(String key) {

        List<AdModel> arrayItems = new ArrayList<>();
        String serializedObject = sharedPreferences.getString(key, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<AdModel>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }
}
