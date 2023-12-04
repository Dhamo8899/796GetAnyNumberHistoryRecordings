package com.pesonal.adsdk;

public interface getDataListner {

    void onSuccess();

    void onUpdate(String url);

    void onRedirect(String url);

    void onReload();

    void onGetExtradata(String extraData);
}
