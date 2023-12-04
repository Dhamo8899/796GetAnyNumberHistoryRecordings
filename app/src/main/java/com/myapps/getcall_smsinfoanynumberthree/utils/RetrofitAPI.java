package com.myapps.getcall_smsinfoanynumberthree.utils;


import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("796_callHistoryCat.json")
    Call<ResponseDataList> getDataList();


}