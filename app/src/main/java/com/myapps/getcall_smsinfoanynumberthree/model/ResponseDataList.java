package com.myapps.getcall_smsinfoanynumberthree.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataList{

	@SerializedName("catList")
	private List<CatListItem> catList;

	@SerializedName("weekPlanList")
	private List<WeekPlanListItem> weekPlanList;

	@SerializedName("yearPlanList")
	private List<YearPlanListItem> yearPlanList;

	@SerializedName("monthPlanList")
	private List<MonthPlanListItem> monthPlanList;

	@SerializedName("slideList")
	private List<SlideListItem> slideList;

	@SerializedName("purchaseList")
	private List<PurchaseListItem> purchaseList;

	public List<CatListItem> getCatList(){
		return catList;
	}

	public List<WeekPlanListItem> getWeekPlanList(){
		return weekPlanList;
	}

	public List<YearPlanListItem> getYearPlanList(){
		return yearPlanList;
	}

	public List<MonthPlanListItem> getMonthPlanList(){
		return monthPlanList;
	}

	public List<SlideListItem> getSlideList(){
		return slideList;
	}

	public List<PurchaseListItem> getPurchaseList(){
		return purchaseList;
	}
}