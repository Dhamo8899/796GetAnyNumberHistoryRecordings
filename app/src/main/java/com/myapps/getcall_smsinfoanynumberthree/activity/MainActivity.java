package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityMainBinding;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.pesonal.adsdk.AppManage;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        AppManage.getInstance(this).showBanner((ViewGroup) findViewById(R.id.adView));
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_banner_adplaceholder), false, 1);

        Glide.with(this).load(R.drawable.btn_animation).into(binding.btnAnimationBg);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.isOnline(MainActivity.this)){
                    if (!isValidEmail(binding.etEmail.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                    }else if(!binding.etEmail.getText().toString().equals(binding.etCemail.getText().toString())){
                        Toast.makeText(MainActivity.this, "Email Address Not Match.", Toast.LENGTH_SHORT).show();
                    } else {
                        AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, new AppManage.MyInterStitialCallback() {
                            public void callbackCall() {
                                Intent intent = new Intent(MainActivity.this, ThankYouActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }
                        }, AppManage.app_mainClickCntSwAd);

                    }
                }else {
                    Toast.makeText(MainActivity.this,"Please check your internet connection!!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}