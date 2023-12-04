package com.pesonal.adsdk;


public class CustomAppOpenFun implements CustomAppOpenAds.AppOpenCallBack{

    public final CustomAppOpenAds customAppOpenAds;
    public final getDataListner ads_splashActivity;


    public CustomAppOpenFun(getDataListner ads_splashActivity, CustomAppOpenAds customAppOpenAds) {
        this.customAppOpenAds = customAppOpenAds;
        this.ads_splashActivity = ads_splashActivity;
    }

    public void mo5555a() {
        AppOpenManager.isShowingAd=false;
        this.customAppOpenAds.dismiss();
        if (ads_splashActivity!=null){
            this.ads_splashActivity.onSuccess();
        }

    }
}
