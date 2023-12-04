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
    CustomLoadingDialog customLoadingDialog=new CustomLoadingDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_select_history);

        AppManage.getInstance(SelectHistoryActivity.this).showBanner((ViewGroup) findViewById(R.id.adView));
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_banner_adplaceholder), false, 1);

        if (Const.isOnline(this)){
            fetchDataList();
        }else {
            Toast.makeText(this,"Please check your internet connection!!",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void fetchDataList() {
        customLoadingDialog.showLoadingDialog();
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
                    Const.saveDataList(SelectHistoryActivity.this,"ResponseDataList",response.body());
                    setupRecyclerView(response.body());
                } else {
                    Toast.makeText(SelectHistoryActivity.this, "Something want wrong...", Toast.LENGTH_SHORT).show();
                }
                customLoadingDialog.dismissLoadingDialog();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDataList> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                customLoadingDialog.dismissLoadingDialog();
                Toast.makeText(SelectHistoryActivity.this, "Fail to get the data..\nPlease Restart App", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setupRecyclerView(ResponseDataList responseDataList){
        CatAdapter catAdapter=new CatAdapter(responseDataList.getCatList(),this);
        binding.rvCat.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCat.setAdapter(catAdapter);

    }

}