package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.adapter.CatAdapter;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivitySelectHistoryBinding;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.myapps.getcall_smsinfoanynumberthree.utils.CustomLoadingDialog;
import com.myapps.getcall_smsinfoanynumberthree.utils.RetrofitAPI;
import com.pesonal.adsdk.AppManage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectHistoryActivity extends AppCompatActivity {
    private static final String TAG = "SelectHistoryActivity";
    ActivitySelectHistoryBinding binding;

    ResponseDataList responseDataList = new ResponseDataList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_select_history);

        AppManage.getInstance(SelectHistoryActivity.this).showBanner((ViewGroup) findViewById(R.id.adView));
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_banner_adplaceholder), false, 1);

        responseDataList = Const.getDataList(this, "ResponseDataList");
        setupRecyclerView(responseDataList);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    private void setupRecyclerView(ResponseDataList responseDataList){
        CatAdapter catAdapter=new CatAdapter(responseDataList.getCatList(),this);
        binding.rvCat.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCat.setAdapter(catAdapter);

    }

}