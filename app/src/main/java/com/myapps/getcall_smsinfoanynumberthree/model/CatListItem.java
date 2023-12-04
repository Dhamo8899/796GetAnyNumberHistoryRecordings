package com.myapps.getcall_smsinfoanynumberthree.model;

import com.google.gson.annotations.SerializedName;

public class CatListItem{

	@SerializedName("icon_url")
	private String iconUrl;

	@SerializedName("subTittle")
	private String subTittle;

	@SerializedName("tag")
	private String tag;

	@SerializedName("tittle")
	private String tittle;

	public String getIconUrl(){
		return iconUrl;
	}

	public String getSubTittle(){
		return subTittle;
	}

	public String getTag(){
		return tag;
	}

	public String getTittle(){
		return tittle;
	}
}