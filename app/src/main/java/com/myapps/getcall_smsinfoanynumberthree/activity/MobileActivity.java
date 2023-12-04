package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.adapter.CustomPagerAdapter;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityMobileBinding;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.pesonal.adsdk.AppManage;

public class MobileActivity extends AppCompatActivity {

    ActivityMobileBinding binding;
    Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile);

        binding.ccpicker.detectLocaleCountry(true);

        AppManage.getInstance(this).showBanner((ViewGroup) findViewById(R.id.adView));
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_banner_adplaceholder), false, 1);

        Glide.with(this).load(R.drawable.btn_animation).into(binding.btnAnimationBg);
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidPhone(binding.etMono.getText().toString()) && binding.etMono.getText().toString().length() < 9) {
                    Toast.makeText(MobileActivity.this, "Please Enter Valid Mobile Number.", Toast.LENGTH_SHORT).show();
                } else if (!binding.etMono.getText().toString().equals(binding.etCmono.getText().toString())) {
                    Toast.makeText(MobileActivity.this, "Mobile Number Not Match.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Const.isNetworkConnected(MobileActivity.this)){
                        AppManage.getInstance(MobileActivity.this).showInterstitialAd(MobileActivity.this, new AppManage.MyInterStitialCallback() {
                            public void callbackCall() {
                                Intent intent = new Intent(MobileActivity.this, PurchaseActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            }
                        }, AppManage.app_mainClickCntSwAd);

                    }else {
                        Toast.makeText(MobileActivity.this, "No Internet Connection.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        ResponseDataList responseDataList=Const.getDataList(this,"ResponseDataList");
        binding.viewpager.setAdapter(new CustomPagerAdapter(this, responseDataList.getSlideList()));
        binding.viewpager.setClipToPadding(false);
        binding.viewpager.setPadding(70, 0, 70, 0);
        binding.viewpager.setPageMargin(25);

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public static boolean isValidPhone(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches());
    }



    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1, true);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

}