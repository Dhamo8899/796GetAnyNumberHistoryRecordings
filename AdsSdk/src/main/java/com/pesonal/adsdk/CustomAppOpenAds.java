package com.pesonal.adsdk;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomAppOpenAds extends Dialog {
    private static final String TAG = "CustomAppOpenAds";
    Context context;
    LinearLayout ll_continue_app;
    TextView txt_appname, txt_context, btn_call_to_action;
    ImageView media_view;

    List<AdModel> modelList = new ArrayList<>();
    AdModel adModel;
    SharedPreferences sharedPreferences;
    public static int appopenDataSwipeCount = 0;
    public AppOpenCallBack appOpenCallBack;

    public CustomAppOpenAds(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
//        CustomAppOpenAds.appOpenCallBack = appOpenCallBack;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_appopen_qureka);

        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        AppOpenManager.isShowingAd=true;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Context context = getContext();
        Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        attributes.width = point.x;
        Context context2 = getContext();
        Point point2 = new Point();
        ((WindowManager) context2.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point2);
        attributes.height = point2.y;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ll_continue_app = findViewById(R.id.ll_continue_app);
        txt_appname = findViewById(R.id.txt_appname);
        txt_context = findViewById(R.id.txt_context);
        btn_call_to_action = findViewById(R.id.btn_call_to_action);
        media_view = findViewById(R.id.media_view);

        modelList = getListFromLocal("APPOPEN_KEY");
// ----- SwipeDataStart------------
        SharedPreferences.Editor customAdsEditor = sharedPreferences.edit();
        appopenDataSwipeCount = sharedPreferences.getInt("AdsAppOpenCustomSwipeCount", 0);

        if (modelList.size() != 0) {
            if (modelList.size() <= appopenDataSwipeCount) {
                appopenDataSwipeCount = 0;
            }
            adModel = modelList.get(appopenDataSwipeCount);
            appopenDataSwipeCount++;
            customAdsEditor.putInt("AdsAppOpenCustomSwipeCount", appopenDataSwipeCount);
            customAdsEditor.apply();

        }
// ----- SwipeData End------------

        GlideUrl url1 = new GlideUrl(adModel.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(context).asBitmap().load(url1).placeholder(R.drawable.no_internet).into(media_view);
        txt_appname.setText(adModel.getTitle());
        txt_context.setText(adModel.getSubTitle());

        btn_call_to_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                            .setShowTitle(false)
                            .enableUrlBarHiding()
                            .setToolbarColor(context.getResources().getColor(R.color.ad_text_primary))
                            .setSecondaryToolbarColor(context.getResources().getColor(R.color.ad_text_primary))
                            .build();
                    customTabsIntent.intent.setPackage("com.android.chrome");
                    customTabsIntent.launchUrl(context, Uri.parse(adModel.getRedirectUrl()));
                } catch (Exception ignored1) {
                    Log.e(TAG, "CustomAppOpenAds onClick Error : " + ignored1.getLocalizedMessage());
                }
            }
        });

        ll_continue_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppOpenCallBack dVar = appOpenCallBack;
                if (dVar != null) {
                    ((CustomAppOpenFun) dVar).mo5555a();
                }
            }
        });

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

                AppOpenCallBack dVar = appOpenCallBack;
                if (dVar != null) {
                    ((CustomAppOpenFun) dVar).mo5555a();
                }
                return false;
            }
        });
    }

    public interface AppOpenCallBack{
    }


    public void onBackPressed() {
        super.onBackPressed();
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
