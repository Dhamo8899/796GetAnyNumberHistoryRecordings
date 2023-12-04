package com.myapps.getcall_smsinfoanynumberthree.activity;

import static com.pesonal.adsdk.AppOpenAdsManager.isAD;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.myapps.getcall_smsinfoanynumberthree.utils.RetrofitAPI;
import com.pesonal.adsdk.ADS_SplashActivity;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.getDataListner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SplashActivity extends ADS_SplashActivity {

    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        isAD = true;
        ADSinit(SplashActivity.this, getCurrentVersionCode(), new getDataListner() {
            @Override
            public void onSuccess() {

               fetchDataList();
            }

            @Override
            public void onUpdate(String url) {
                Log.e("my_log", "onUpdate: " + url);
                showUpdateDialog(url);
            }

            @Override
            public void onRedirect(String url) {
                Log.e("my_log", "onRedirect: " + url);
                showRedirectDialog(url);
            }

            @Override
            public void onReload() {
                startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                finish();
            }

            @Override
            public void onGetExtradata(String extraData) {
                Log.e("my_log", "ongetExtradata: " + extraData.toString());
            }
        });
    }


    public void fetchDataList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.MAIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ResponseDataList> call = retrofitAPI.getDataList();
        call.enqueue(new Callback<ResponseDataList>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDataList> call, Response<ResponseDataList> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse ResponseDataList : " + response.body());
                    Const.saveDataList(SplashActivity.this,"ResponseDataList",response.body());
                    isAD = false;
                    AppManage.getInstance(SplashActivity.this).showInterstitialAd_splash(SplashActivity.this, new AppManage.MyInterStitialCallback() {
                        @Override
                        public void callbackCall() {
                            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, AppManage.app_innerClickCntSwAd);
                } else {
                    Toast.makeText(SplashActivity.this, "Something want wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDataList> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(SplashActivity.this, "Fail to get the data..\nPlease Restart App", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void showRedirectDialog(final String url) {

        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(com.pesonal.adsdk.R.layout.installnewappdialog, null);
        dialog.setContentView(view);
        TextView update = view.findViewById(com.pesonal.adsdk.R.id.update);
        TextView txt_title = view.findViewById(com.pesonal.adsdk.R.id.txt_title);
        TextView txt_decription = view.findViewById(com.pesonal.adsdk.R.id.txt_decription);

        update.setText("Install Now");
        txt_title.setText("Install our new app now and enjoy");
        txt_decription.setText("We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app.");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri marketUri = Uri.parse(url);
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);
                } catch (ActivityNotFoundException ignored1) {
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }


    public void showUpdateDialog(final String url) {

        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(com.pesonal.adsdk.R.layout.installnewappdialog, null);
        dialog.setContentView(view);
        TextView update = view.findViewById(com.pesonal.adsdk.R.id.update);
        TextView txt_title = view.findViewById(com.pesonal.adsdk.R.id.txt_title);
        TextView txt_decription = view.findViewById(com.pesonal.adsdk.R.id.txt_decription);

        update.setText("Update Now");
        txt_title.setText("Update our new app now and enjoy");
        txt_decription.setText("");
        txt_decription.setVisibility(View.GONE);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri marketUri = Uri.parse(url);
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);
                } catch (ActivityNotFoundException ignored1) {
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public int getCurrentVersionCode() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

}