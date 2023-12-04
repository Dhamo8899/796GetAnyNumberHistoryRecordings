package com.myapps.getcall_smsinfoanynumberthree.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetailsParams;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.adapter.PurchaseCatAdapter;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityPurchaseBinding;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.myapps.getcall_smsinfoanynumberthree.utils.CustomLoadingDialog;
import com.myapps.getcall_smsinfoanynumberthree.utils.MyPrefs;
import com.pesonal.adsdk.AppManage;

import java.util.Collections;
import java.util.List;

public class PurchaseActivity extends AppCompatActivity {
    private static final String TAG = "PurchaseActivity";
    ActivityPurchaseBinding binding;
    String PRODUCT_ID;
    boolean isBillingInitialized = false;
    ResponseDataList responseDataList=new ResponseDataList();
    BillingClient billingClient;
    CustomLoadingDialog customLoadingDialog = new CustomLoadingDialog(this);
    private FirebaseAnalytics mFirebaseAnalytics;

    String selectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Glide.with(this).load(R.drawable.btn_animation).into(binding.btnAnimationBg);

        responseDataList=Const.getDataList(this,"ResponseDataList");

        setUpView();

        PRODUCT_ID = responseDataList.getPurchaseList().get(0).getKey();
        selectedPrice = responseDataList.getPurchaseList().get(0).getPrice();

        binding.llPlan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRODUCT_ID = responseDataList.getPurchaseList().get(0).getKey();
                selectedPrice = responseDataList.getPurchaseList().get(0).getPrice();
                binding.llPlan1.setBackgroundResource(R.drawable.bg_filled_round);
                binding.llPlan2.setBackgroundResource(R.drawable.bg_outline_round);
                binding.llPlan3.setBackgroundResource(R.drawable.bg_outline_round);
            }
        });

        binding.llPlan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRODUCT_ID = responseDataList.getPurchaseList().get(1).getKey();
                selectedPrice = responseDataList.getPurchaseList().get(1).getPrice();
                binding.llPlan1.setBackgroundResource(R.drawable.bg_outline_round);
                binding.llPlan2.setBackgroundResource(R.drawable.bg_filled_round);
                binding.llPlan3.setBackgroundResource(R.drawable.bg_outline_round);
            }
        });
        binding.llPlan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRODUCT_ID = responseDataList.getPurchaseList().get(2).getKey();
                selectedPrice = responseDataList.getPurchaseList().get(2).getPrice();
                binding.llPlan1.setBackgroundResource(R.drawable.bg_outline_round);
                binding.llPlan2.setBackgroundResource(R.drawable.bg_outline_round);
                binding.llPlan3.setBackgroundResource(R.drawable.bg_filled_round);
            }
        });

        initClientBilling();


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Const.isOnline(PurchaseActivity.this)){
                    if (isBillingInitialized){
                        launchSubscription(PRODUCT_ID);
                    }else{
                        Toast.makeText(PurchaseActivity.this, "Server down\nTry Again later.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(PurchaseActivity.this,"Please check your internet connection!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initClientBilling() {
        billingClient = BillingClient.newBuilder(this)
                .setListener(purchaseUpdatedListener)
                .enablePendingPurchases()
                .build();

        startConnection();
    }

    private final PurchasesUpdatedListener purchaseUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                // Handle successful purchases here
                for (Purchase purchase : purchases) {
                    verifySubPurchase(purchase);
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                // Handle user cancellation
                Toast.makeText(PurchaseActivity.this, "Subscription not complete", Toast.LENGTH_SHORT).show();
            } /*else {
            Toast.makeText(PurchaseActivity.this, "Something went wrong, try again later.", Toast.LENGTH_SHORT).show();
            // Handle other errors
        }*/
        }
    };

    private void verifySubPurchase(Purchase purchases) {
        AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchases.getPurchaseToken())
                .build();
            billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                // user prefs to set premium
                Toast.makeText(PurchaseActivity.this, "Subscription activated, Enjoy!", Toast.LENGTH_SHORT).show();
                MyPrefs.writeBoolean(PurchaseActivity.this, MyPrefs.IS_PREMIUM, true);

                double valuePrice = Double.parseDouble(selectedPrice.replace("$",""));

                Bundle purchaseParams = new Bundle();
                purchaseParams.putString(FirebaseAnalytics.Param.ITEM_ID, PRODUCT_ID);
                purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, valuePrice);
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams);

                Intent intent = new Intent(PurchaseActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
            }
        });
    }


    private void startConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                // Handle billing service disconnect
                isBillingInitialized = false;
            }

            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Billing client is ready, you can query purchases or make purchases here
                    isBillingInitialized = true;
                }
            }
        });
    }

    private void launchSubscription(String sku) {
        customLoadingDialog.showLoadingDialog();
        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder()
                .setSkusList(Collections.singletonList(sku))
                .setType(BillingClient.SkuType.SUBS)
                .build();

        billingClient.querySkuDetailsAsync(skuDetailsParams, (billingResult, skuDetailsList) -> {
            Log.d(TAG, "launchSubscription: " + binding.nextBtn.isClickable());
            customLoadingDialog.dismissLoadingDialog();

            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                if (skuDetailsList != null && !skuDetailsList.isEmpty()) {
                    BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                            .setSkuDetails(skuDetailsList.get(0))
                            .build();

                    billingClient.launchBillingFlow(PurchaseActivity.this, billingFlowParams);
                }
            } else {
                // Handle the query SKU details error
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        billingClient.endConnection();
    }
    private void setUpView(){
        binding.tvTittle1.setText(responseDataList.getPurchaseList().get(0).getTittle());
        binding.tvTittle2.setText(responseDataList.getPurchaseList().get(1).getTittle());
        binding.tvTittle3.setText(responseDataList.getPurchaseList().get(2).getTittle());

        binding.tvSub1.setText(responseDataList.getPurchaseList().get(0).getSubTittle());
        binding.tvSub2.setText(responseDataList.getPurchaseList().get(1).getSubTittle());
        binding.tvSub3.setText(responseDataList.getPurchaseList().get(2).getSubTittle());

        binding.tvPlan1.setText(responseDataList.getPurchaseList().get(0).getPrice());
        binding.tvPlan2.setText(responseDataList.getPurchaseList().get(1).getPrice());
        binding.tvPlan3.setText(responseDataList.getPurchaseList().get(2).getPrice());

        binding.tvPolicy.setText(AppManage.QUREKA_APPID);

        PurchaseCatAdapter purchaseCatAdapter=new PurchaseCatAdapter(responseDataList.getCatList(),this);
        binding.rvProTittle.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProTittle.setAdapter(purchaseCatAdapter);
    }



}