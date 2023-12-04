package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityStartBinding;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.myapps.getcall_smsinfoanynumberthree.utils.MyPrefs;
import com.pesonal.adsdk.AppManage;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.onboarding;
        binding.videoView.setVideoPath(path);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams parms = binding.videoView.getLayoutParams();
        parms.width = metrics.widthPixels;
        parms.height = metrics.heightPixels;
        binding.videoView.setLayoutParams(parms);

        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                binding.videoView.requestFocus();
                binding.videoView.start();
                mp.setLooping(true);
            }
        });

        binding.tvTittle.setText(AppManage.QUREKA_B1);
        binding.SubTittle.setText(AppManage.QUREKA_B2);
        binding.tvBtn.setText(AppManage.QUREKA_B3);


        Glide.with(this).load(R.drawable.btn_animation).into(binding.btnAnimationBg);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.isOnline(StartActivity.this)){
                    AppManage.getInstance(StartActivity.this).showInterstitialAd(StartActivity.this, new AppManage.MyInterStitialCallback() {
                        public void callbackCall() {
                            if (MyPrefs.readBoolean(StartActivity.this,MyPrefs.IS_PREMIUM,false)){
                                Intent intent = new Intent(StartActivity.this, ProcessActivity.class);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }else {
                                Intent intent = new Intent(StartActivity.this, SelectHistoryActivity.class);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }
                        }
                    }, AppManage.app_mainClickCntSwAd);
                }else {
                    Toast.makeText(StartActivity.this,"Please check your internet connection!!",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.videoView.resume();
    }
}