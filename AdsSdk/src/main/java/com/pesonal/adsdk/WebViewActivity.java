package com.pesonal.adsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {
    TextView btnSkip;
    WebView browser;
    String adType;

    public long F;
    public long N;
    public boolean V = false;
    public boolean firstVisble = true;
    SharedPreferences.Editor editor1;
    SharedPreferences preferences1;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        preferences1 = getSharedPreferences("TimePrefsW", Context.MODE_PRIVATE);
        editor1 = preferences1.edit();
        editor1.clear();
        editor1.apply();

        adType = getIntent().getStringExtra("adType");
        browser = findViewById(R.id.webView1);
        btnSkip = findViewById(R.id.btnSkip);


        LoadPage(AppManage.WEBVIEW_APPID);


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if (adType.equals("Reward")) {
                    if (!V) {
                        finish();
                        AppManage.getInstance(WebViewActivity.this).rewardCallBack();
                    }

                } else {
                    finish();
                    AppManage.getInstance(WebViewActivity.this).interstitialCallBack();
                }*/
                if (adType.equals("Reward")) {
                    if (!V) {
                        finish();
                        AppManage.getInstance(WebViewActivity.this).interstitialCallBack();
                    }

                }

            }
        });
    }

    public final void E() {
        this.N = System.currentTimeMillis() + this.F;

        countDownTimer = new a(this.F, 1000).start();
        this.V = true;
    }

    public void timerOn() {

        F = 15000;
        SharedPreferences.Editor edit = getSharedPreferences("TimePrefsW", 0).edit();
        edit.putBoolean("isTimeRunning1", true);
        edit.putLong("millisLeft1", F);
        edit.putLong("endTime1", System.currentTimeMillis() + F);
        edit.apply();
        E();

    }

    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {

            ((TextView) findViewById(R.id.btnSkip)).setText(getString(R.string.Skip));
            F = 0;
            V = false;
        }

        public void onTick(long j) {
            F = j;

            ((TextView) findViewById(R.id.btnSkip)).setText("" + C(WebViewActivity.this, j) + " remaining");
        }
    }

    public static String C(WebViewActivity webViewActivity, long j2) {
        Objects.requireNonNull(webViewActivity);
        long floor = (long) Math.floor((double) (j2 / 86400000));
        long floor2 = (long) Math.floor((double) ((j2 % 86400000) / 3600000));
        long floor3 = (long) Math.floor((double) ((j2 % 3600000) / 60000));
        long floor4 = (long) Math.floor((double) ((j2 % 60000) / 1000));
        String str = floor + "d " + floor2 + "h " + floor3 + "m " + floor4 + "s ";
        if (floor == 0) {
            str = floor2 + "h " + floor3 + "m " + floor4 + "s ";
        }
        if (floor2 == 0) {
            str = floor3 + "m " + floor4 + "s ";
        }
        if (floor3 != 0) {
            return str;
        }
        return floor4 + "s ";
    }

    public void LoadPage(String Url) {
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setBuiltInZoomControls(false);


        browser.getSettings().setUseWideViewPort(true);
        browser.setWebViewClient(new MyBrowser());
        browser.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    if (firstVisble) {
                        if (adType.equals("Reward")) {

                            timerOn();
                        }
                        btnSkip.setVisibility(View.VISIBLE);
                        firstVisble = false;
                    }

                }
            }
        });
        browser.getSettings().setDomStorageEnabled(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl(Url);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {

        }

      /*  @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
           *//* if (adType.equals("Reward")) {

                timerOn();
            }*//*

        }

*/
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (browser.canGoBack()) {
            browser.goBack();
        } else {
//            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firstVisble = true;

        editor1.clear();
        editor1.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adType.equals("Reward")) {

            SharedPreferences sharedPreferences = getSharedPreferences("TimePrefsW", 0);
            this.V = sharedPreferences.getBoolean("isTimeRunning1", false);
            this.F = sharedPreferences.getLong("millisLeft1", this.F);
            if (this.V) {
                long j2 = sharedPreferences.getLong("endTime1", this.N);
                this.N = j2;
                countDownTimer = new a(this.F, 1000).start();

            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adType.equals("Reward")) {

            SharedPreferences.Editor edit = getSharedPreferences("TimePrefsW", 0).edit();
            edit.putBoolean("isTimeRunning1", this.V);
            edit.putLong("millisLeft1", this.F);
            edit.putLong("endTime1", this.N);
            edit.apply();
            countDownTimer.cancel();
        }
    }

}