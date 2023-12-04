package com.myapps.getcall_smsinfoanynumberthree.model;

import com.google.gson.annotations.SerializedName;

public class PurchaseListItem{

	@SerializedName("subTittle")
	private String subTittle;

	@SerializedName("price")
	private String price;

	@SerializedName("key")
	private String key;

	@SerializedName("tittle")
	private String tittle;

	public String getSubTittle(){
		return subTittle;
	}

	public String getPrice(){
		return price;
	}

	public String getKey(){
		return key;
	}

	public String getTittle(){
		return tittle;
	}

	@Override
 	public String toString(){
		return 
			"PurchaseListItem{" + 
			"subTittle = '" + subTittle + '\'' + 
			",price = '" + price + '\'' + 
			",key = '" + key + '\'' + 
			",tittle = '" + tittle + '\'' + 
			"}";
		}
}