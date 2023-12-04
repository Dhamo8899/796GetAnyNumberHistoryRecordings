package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityProcessBinding;
import com.pesonal.adsdk.AppManage;

public class ProcessActivity extends AppCompatActivity {
    ActivityProcessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_process);

        AppManage.getInstance(ProcessActivity.this).showBanner((ViewGroup) findViewById(R.id.adView));
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_banner_adplaceholder), false, 1);

        Glide.with(this).load(R.drawable.btn_animation).into(binding.btnAnimationBg);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(ProcessActivity.this).showInterstitialAd(ProcessActivity.this, new AppManage.MyInterStitialCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(ProcessActivity.this, ThankYouActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, AppManage.app_mainClickCntSwAd);

            }
        });

    }
}