package com.myapps.getcall_smsinfoanynumberthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.myapps.getcall_smsinfoanynumberthree.adapter.IcludedListAdapter;
import com.myapps.getcall_smsinfoanynumberthree.adapter.PurchaseCatAdapter;
import com.myapps.getcall_smsinfoanynumberthree.databinding.ActivityFeaturedBinding;
import com.myapps.getcall_smsinfoanynumberthree.model.ResponseDataList;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;

public class FeaturedActivity extends AppCompatActivity {

    ResponseDataList responseDataList = new ResponseDataList();
    ActivityFeaturedBinding binding;
    boolean isExpand1 = false;
    boolean isExpand2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_featured);

        responseDataList = Const.getDataList(this, "ResponseDataList");

        setupTab();
        packageSetup();

    }

    private void setupTab() {
        TabLayout.Tab tab1 = binding.tbLayout.newTab();
        TabLayout.Tab tab2 = binding.tbLayout.newTab();
        TabLayout.Tab tab3 = binding.tbLayout.newTab();

        tab1.setText("Weekly");
        tab2.setText("Monthly");
        tab3.setText("Yearly");
        binding.tbLayout.addTab(tab1);
        binding.tbLayout.addTab(tab2);
        binding.tbLayout.addTab(tab3);

        planSetup(0);
        binding.tbLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (Const.isOnline(FeaturedActivity.this)) {
                    planSetup(tab.getPosition());
                } else {
                    Toast.makeText(FeaturedActivity.this, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void planSetup(int pos) {
        switch (pos) {
            case 0:
                binding.tvTittle.setText(responseDataList.getWeekPlanList().get(0).getPlanTittle());
                binding.tvTittle2.setText(responseDataList.getWeekPlanList().get(1).getPlanTittle());
                binding.tvPrice.setText(responseDataList.getWeekPlanList().get(0).getPrice());
                binding.tvPrice2.setText(responseDataList.getWeekPlanList().get(1).getPrice());
                binding.tvPriceOff.setText(responseDataList.getWeekPlanList().get(0).getOfferTag());
                binding.tvPriceOff2.setText(responseDataList.getWeekPlanList().get(1).getOfferTag());

                IcludedListAdapter icludedListAdapter = new IcludedListAdapter(responseDataList.getWeekPlanList().get(0).getIncludedList(), this);
                binding.rvIncludeList.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList.setAdapter(icludedListAdapter);

                IcludedListAdapter icludedListAdapter2 = new IcludedListAdapter(responseDataList.getWeekPlanList().get(1).getIncludedList(), this);
                binding.rvIncludeList2.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList2.setAdapter(icludedListAdapter2);

                break;
            case 1:
                binding.tvTittle.setText(responseDataList.getMonthPlanList().get(0).getPlanTittle());
                binding.tvTittle2.setText(responseDataList.getMonthPlanList().get(1).getPlanTittle());
                binding.tvPrice.setText(responseDataList.getMonthPlanList().get(0).getPrice());
                binding.tvPrice2.setText(responseDataList.getMonthPlanList().get(1).getPrice());
                binding.tvPriceOff.setText(responseDataList.getMonthPlanList().get(0).getOfferTag());
                binding.tvPriceOff2.setText(responseDataList.getMonthPlanList().get(1).getOfferTag());

                IcludedListAdapter icludedMonthListAdapter = new IcludedListAdapter(responseDataList.getMonthPlanList().get(0).getIncludedList(), this);
                binding.rvIncludeList.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList.setAdapter(icludedMonthListAdapter);

                IcludedListAdapter icludedMonthListAdapter2 = new IcludedListAdapter(responseDataList.getMonthPlanList().get(1).getIncludedList(), this);
                binding.rvIncludeList2.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList2.setAdapter(icludedMonthListAdapter2);
                break;
            case 2:
                binding.tvTittle.setText(responseDataList.getYearPlanList().get(0).getPlanTittle());
                binding.tvTittle2.setText(responseDataList.getYearPlanList().get(1).getPlanTittle());
                binding.tvPrice.setText(responseDataList.getYearPlanList().get(0).getPrice());
                binding.tvPrice2.setText(responseDataList.getYearPlanList().get(1).getPrice());
                binding.tvPriceOff.setText(responseDataList.getYearPlanList().get(0).getOfferTag());
                binding.tvPriceOff2.setText(responseDataList.getYearPlanList().get(1).getOfferTag());

                IcludedListAdapter icludedYearListAdapter = new IcludedListAdapter(responseDataList.getYearPlanList().get(0).getIncludedList(), this);
                binding.rvIncludeList.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList.setAdapter(icludedYearListAdapter);

                IcludedListAdapter icludedYearListAdapter2 = new IcludedListAdapter(responseDataList.getYearPlanList().get(1).getIncludedList(), this);
                binding.rvIncludeList2.setLayoutManager(new LinearLayoutManager(this));
                binding.rvIncludeList2.setAdapter(icludedYearListAdapter2);
                break;

        }

        isExpand1 = false;
        isExpand2 = false;
        binding.rvIncludeList.setVisibility(View.GONE);
        binding.rvIncludeList2.setVisibility(View.GONE);

    }

    private void packageSetup() {
        binding.tvExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand1) {
                    isExpand1 = false;
                    binding.rvIncludeList.setVisibility(View.GONE);
                } else {
                    isExpand1 = true;
                    binding.rvIncludeList.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.tvExpand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand2) {
                    isExpand2 = false;
                    binding.rvIncludeList2.setVisibility(View.GONE);
                } else {
                    isExpand2 = true;
                    binding.rvIncludeList2.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}