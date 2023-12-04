package com.myapps.getcall_smsinfoanynumberthree.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MonthPlanListItem{

	@SerializedName("planTittle")
	private String planTittle;

	@SerializedName("includedList")
	private List<IncludedListItem> includedList;

	@SerializedName("price")
	private String price;

	@SerializedName("offerTag")
	private String offerTag;

	@SerializedName("key")
	private String key;

	public String getPlanTittle(){
		return planTittle;
	}

	public List<IncludedListItem> getIncludedList(){
		return includedList;
	}

	public String getPrice(){
		return price;
	}

	public String getOfferTag(){
		return offerTag;
	}

	public String getKey(){
		return key;
	}
}