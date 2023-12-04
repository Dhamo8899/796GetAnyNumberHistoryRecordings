package com.myapps.getcall_smsinfoanynumberthree.model;

import com.google.gson.annotations.SerializedName;

public class IncludedListItem{

	@SerializedName("name")
	private String name;

	@SerializedName("status")
	private String status;

	public String getName(){
		return name;
	}

	public String getStatus(){
		return status;
	}
}