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

public class CustomBannerAds {
    private static final String TAG = "CustomBannerAds";
    static Activity activity;
    List<AdModel> modelList = new ArrayList<>();

    SharedPreferences sharedPreferences;
    private static CustomBannerAds mInstance;
    public static int bannerDataSwipeCount = 0;

    public CustomBannerAds(Activity activity) {
        CustomBannerAds.activity = activity;
        sharedPreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
    }

    public static CustomBannerAds getInstance(Activity activity) {
        CustomBannerAds.activity = activity;
        if (mInstance == null) {
            mInstance = new CustomBannerAds(activity);
        }
        return mInstance;
    }

    public void showCostomBannerAds(ViewGroup bannerAdContainer) {

        View viewLayout = View.inflate(activity, R.layout.ads_banner_custom, null);

        modelList = getListFromLocal("BANNER_KEY");
        AdModel adModel = null;
        // ----- SwipeNativeDataStart------------
        SharedPreferences.Editor customAdsEditor = sharedPreferences.edit();
        bannerDataSwipeCount = sharedPreferences.getInt("AdsBannerCustomSwipeCount", 0);

        if (modelList.size() != 0) {
            if (modelList.size() <= bannerDataSwipeCount) {
                bannerDataSwipeCount = 0;
            }
            adModel = modelList.get(bannerDataSwipeCount);
            bannerDataSwipeCount++;
            customAdsEditor.putInt("AdsBannerCustomSwipeCount", bannerDataSwipeCount);
            customAdsEditor.apply();

        }
// ----- SwipeNativeData End------------

        ImageView ad_app_icon = viewLayout.findViewById(R.id.ad_app_icon);
        TextView ad_headline = viewLayout.findViewById(R.id.ad_headline);
        TextView ad_body = viewLayout.findViewById(R.id.ad_body);
        TextView ad_call_to_action = viewLayout.findViewById(R.id.ad_call_to_action);

        GlideUrl ad_app_icon_url1 = new GlideUrl(adModel.getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

        Glide.with(activity).asBitmap().load(ad_app_icon_url1).placeholder(R.mipmap.ic_launcher).into(ad_app_icon);
        ad_headline.setText(adModel.getTitle());
        ad_body.setText(adModel.getSubTitle());
        ad_body.setSelected(true);
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
                    Log.e(TAG, "CustomNativeAds onClick Error: " + ignored1.getLocalizedMessage());
                }
            }
        });

        bannerAdContainer.addView(viewLayout);
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
