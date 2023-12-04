package com.myapps.getcall_smsinfoanynumberthree.model;

import com.google.gson.annotations.SerializedName;

public class SlideListItem{

	@SerializedName("icon_url")
	private String iconUrl;

	@SerializedName("number")
	private String number;

	@SerializedName("time")
	private String time;

	@SerializedName("tittle")
	private String tittle;

	public String getIconUrl(){
		return iconUrl;
	}

	public String getNumber(){
		return number;
	}

	public String getTime(){
		return time;
	}

	public String getTittle(){
		return tittle;
	}
}