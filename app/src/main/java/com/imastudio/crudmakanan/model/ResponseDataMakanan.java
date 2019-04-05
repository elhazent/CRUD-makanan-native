package com.imastudio.crudmakanan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDataMakanan{

	@SerializedName("DataMakanan")
	private List<DataMakananItem> dataMakanan;

	public void setDataMakanan(List<DataMakananItem> dataMakanan){
		this.dataMakanan = dataMakanan;
	}

	public List<DataMakananItem> getDataMakanan(){
		return dataMakanan;
	}
}