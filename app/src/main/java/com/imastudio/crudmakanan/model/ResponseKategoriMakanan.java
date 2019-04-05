package com.imastudio.crudmakanan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKategoriMakanan{

	@SerializedName("DataKategori")
	private List<DataKategoriItem> dataKategori;

	public void setDataKategori(List<DataKategoriItem> dataKategori){
		this.dataKategori = dataKategori;
	}

	public List<DataKategoriItem> getDataKategori(){
		return dataKategori;
	}
}