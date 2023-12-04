package com.myapps.getcall_smsinfoanynumberthree.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataList{

	@SerializedName("catList")
	private List<CatListItem> catList;

	@SerializedName("slideList")
	private List<SlideListItem> slideList;

	@SerializedName("purchaseList")
	private List<PurchaseListItem> purchaseList;

	public List<CatListItem> getCatList(){
		return catList;
	}

	public List<SlideListItem> getSlideList(){
		return slideList;
	}

	public List<PurchaseListItem> getPurchaseList(){
		return purchaseList;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataList{" + 
			"catList = '" + catList + '\'' + 
			",slideList = '" + slideList + '\'' + 
			",purchaseList = '" + purchaseList + '\'' + 
			"}";
		}
}