package com.pesonal.adsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CustomAdActivity extends AppCompatActivity {
    public static int f22134v;
    public static int interDataSwipeCount=0;
    LinearLayout llPersonalAd, llPersonalAdCenter, adPersonalCloseBtn;
    ImageView native_ad_media, native_ad_icon, ImgClose;
    TextView native_ad_title, native_ad_social_context, native_ad_call_to_action;
    RelativeLayout int_bg;
  public static  List<AdModel> modelList=new ArrayList<>();
    AdModel adModel;
    SharedPreferences sharedPreferences;
    public static   List<AdModel> arrayItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = f22134v;
        setContentView(i == 2 ? R.layout.cust_int2 : i == 1 ? R.layout.cust_int1 : R.layout.cust_int);
        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        llPersonalAdCenter = findViewById(R.id.llPersonalAdCenter);
        llPersonalAd = findViewById(R.id.llPersonalAd);
        int_bg = findViewById(R.id.int_bg);
        native_ad_icon = findViewById(R.id.native_ad_icon);
        native_ad_media = findViewById(R.id.native_ad_media);
        ImgClose = findViewById(R.id.ImgClose);
        adPersonalCloseBtn = findViewById(R.id.adPersonalCloseBtn);
        native_ad_title = findViewById(R.id.native_ad_title);
        native_ad_social_context = findViewById(R.id.native_ad_social_context);
        native_ad_call_to_action = findViewById(R.id.native_ad_call_to_action);

//        modelList=Constant.adModels;

        modelList=getListFromLocal("INTERSTITIAL_KEY");

        Log.d("TAG3", "onCreate: modelList" +modelList);
// ----- SwipeDataStart------------
        SharedPreferences.Editor customAdsEditor = sharedPreferences.edit();
        interDataSwipeCount=sharedPreferences.getInt("AdsIntrCustomSwipeCount", 0);

        if (modelList.size()!=0){
            if (modelList.size()<=interDataSwipeCount){
                interDataSwipeCount=0;
            }
            adModel=modelList.get(interDataSwipeCount);
            interDataSwipeCount++;
            customAdsEditor.putInt("AdsIntrCustomSwipeCount", interDataSwipeCount);
            customAdsEditor.apply();

        }
// ----- SwipeData End------------


        if (f22134v == 2) {
            f22134v = 0;
//             adModel=modelList.get(0);

            mo15746X(findViewById(R.id.llcus3), 1000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    FadeIn(findViewById(R.id.llPersonalAd));
                    FadeIn(findViewById(R.id.main));
                    FadeIn(findViewById(R.id.aa));
                    FadeIn(findViewById(R.id.adPersonalLlCloseInstallBtns));
                }
            }, 1000);
        } else if (f22134v == 1) {
//            adModel=modelList.get(1);

            f22134v = f22134v + 1;
            mo15745W(native_ad_icon, 1000);
            mo15746X(findViewById(R.id.cvTopAd), 1000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FadeIn(findViewById(R.id.aa));
                    FadeIn(findViewById(R.id.adPersonalLlCloseInstallBtnsCenter));
                }
            }, 2200);
        } else {
//            adModel=modelList.get(2);

            f22134v = f22134v + 1;
            mo15747Y(native_ad_icon, 1000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int i;
                    View view;

                    mo15744V(native_ad_title, 600);
                    mo15744V(findViewById(R.id.banner), 1000);
                    mo15744V(native_ad_social_context, 1200);
                    mo15744V(findViewById(R.id.adPersonalLlCloseInstallBtns), 1600);
                }
            }, 800);
        }

        GlideUrl url = new GlideUrl(adModel.getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        GlideUrl url1 = new GlideUrl(adModel.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(this).asBitmap().load(url).placeholder(R.mipmap.ic_launcher).into(native_ad_icon);
        Glide.with(this).asBitmap().load(url1).placeholder(R.drawable.no_internet).into(native_ad_media);
        native_ad_title.setText(adModel.getTitle());
        native_ad_social_context.setText(adModel.getSubTitle());

        native_ad_call_to_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                            .setShowTitle(false)
                            .enableUrlBarHiding()
                            .setToolbarColor(getResources().getColor(R.color.ad_text_primary))
                            .setSecondaryToolbarColor(getResources().getColor(R.color.ad_text_primary))
                            .build();
                    customTabsIntent.intent.setPackage("com.android.chrome");
                    customTabsIntent.launchUrl(CustomAdActivity.this, Uri.parse(adModel.getRedirectUrl()));
                } catch (Exception ignored1) {
                }
            }
        });

        ImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AppManage.getInstance(CustomAdActivity.this).loadInterstitialAd(CustomAdActivity.this);
                AppManage.getInstance(CustomAdActivity.this).interstitialCallBack();
            }
        });

        adPersonalCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AppManage.getInstance(CustomAdActivity.this).loadInterstitialAd(CustomAdActivity.this);
                AppManage.getInstance(CustomAdActivity.this).interstitialCallBack();
            }
        });
    }

    public List<AdModel> getListFromLocal(String key)

   {
        Log.d("TAG2", "null:key " +key);
        arrayItems = new ArrayList<>();
       Log.d("TAG2", "null:arrayItems " +arrayItems);
        String serializedObject = sharedPreferences.getString(key, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<AdModel>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }
    public void FadeIn(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new C3231b(view));
    }

    /* renamed from: V */
    public void mo15744V(View view, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 5.0f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new C3238i(view));
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    /* renamed from: W */
    public void mo15745W(View view, int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        loadAnimation.setFillAfter(true);
        view.startAnimation(loadAnimation);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.5f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new C3240k(view));
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    /* renamed from: X */
    public void mo15746X(View view, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.5f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new C3239j(view));
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    /* renamed from: Y */
    public void mo15747Y(View view, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -2.0f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new C3230a(view));
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    class C3231b implements Animation.AnimationListener {

        /* renamed from: a */
        final /* synthetic */ View f22152a;

        C3231b(View view) {
            this.f22152a = view;
        }

        public void onAnimationEnd(Animation animation) {
            this.f22152a.setVisibility(View.VISIBLE);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C3238i implements Animation.AnimationListener {

        /* renamed from: a */
        final View f22160a;

        C3238i(View view) {
            this.f22160a = view;
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            this.f22160a.setVisibility(View.VISIBLE);
        }
    }

    class C3240k implements Animation.AnimationListener {

        /* renamed from: a */
        final /* synthetic */ View f22164a;

        C3240k(View view) {
            this.f22164a = view;
        }

        public void onAnimationEnd(Animation animation) {
            Animation loadAnimation = AnimationUtils.loadAnimation(CustomAdActivity.this, R.anim.zoom_out);
            loadAnimation.setFillAfter(true);
            this.f22164a.startAnimation(loadAnimation);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            this.f22164a.setVisibility(View.VISIBLE);
        }
    }

    class C3239j implements Animation.AnimationListener {

        /* renamed from: a */
        final /* synthetic */ View f22162a;

        C3239j(View view) {
            this.f22162a = view;
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            this.f22162a.setVisibility(View.VISIBLE);
        }
    }

    class C3230a implements Animation.AnimationListener {

        /* renamed from: a */
        final /* synthetic */ View f22150a;

        C3230a(View view) {
            this.f22150a = view;
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            this.f22150a.setVisibility(View.VISIBLE);
        }
    }

    public void onBackPressed() {
    }

}