package com.imastudio.crudmakanan.network;

import com.imastudio.crudmakanan.model.ResponseDataMakanan;
import com.imastudio.crudmakanan.model.ResponseKategoriMakanan;
import com.imastudio.crudmakanan.model.ResponseLogin;
import com.imastudio.crudmakanan.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Blackswan on 9/12/2017.
 */

public interface RestApi {


    @FormUrlEncoded
    @POST("registeruser.php/")
    Call<ResponseRegister> registerUser(
            @Field("vsnama") String strnama,
            @Field("vsalamat") String stralamat,
            @Field("vsnotelp") String strnotelp,
            @Field("vsjenkel") String strjenkel,
            @Field("vsusername") String strusername,
            @Field("vspassword") String strpassword,
            @Field("vslevel") String strlevel,
            @Field("vsusia") String strusia
    );

    @FormUrlEncoded
    @POST("loginuser.php/")
    Call<ResponseLogin> loginUser(
            @Field("edtusername") String strusername,
            @Field("edtpassword") String strpassword,
            @Field("vslevel") String strlevel
    );
////
    @FormUrlEncoded
    @POST("getdatamakanan.php/")
    Call<ResponseDataMakanan>   getdatamakanan(
            @Field("vsiduser") String striduser,
            @Field("vsidkastrkategorimakanan") String strkartmakaan
    );
//
    @FormUrlEncoded
    @POST("deletedatamakanan.php/")
    Call<ResponseRegister> deletedata(
            @Field("vsidmakanan") String stridmakanan
    );
//
////
    @GET("kategorimakanan.php/")
    public Call<ResponseKategoriMakanan> getkategorimakanan();

//    @Multipart
//    @POST("uploadmakanan1.php")
//    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file, @Part("image") RequestBody name);
//

}
