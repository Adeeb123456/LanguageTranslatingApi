package com.example.designerclicksol.languagetranslatingapi.ws;


import java.net.URL;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface WebService {



    /*
    *  String urlStr = "https://www.googleapis.com/language/translate/v2?key=" + key + "&q=" + encodedText + "&target=" + to + "&source=" + from;
    * */


    @POST("?key= {key}&q={encodedText}&target={to}&source={from}")
    Call<ResponseBody> getTranslatedString(@Path("key") String key,@Path("encodedText") String encodedText,@Path("to") String to,@Path("from") String from);

@POST
Call<ResponseBody> getTranslatedStringThroughCompleteUrl(@Url String url);






 /*   @GET("/v1/account/rule")
    Call<ResponseBody> getAccount(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
@POST("?r=api/userapi/login")
Call<Login> loginUser(@Body JsonObject jsonbject);

@Headers({"Content-Type: application/json;charset=UTF-8"})

@POST("?r=api/userapi/signup")
Call<Signup> singnUpUser(@Body JsonObject jsonObject);

@FormUrlEncoded
@POST("?r=api/userapi/signup")
Call<Signup> signUpFormUrlEncoded(@Field("api_key") String key,
                                  @Field("langauge") String lan,
                                  @Field("user_id") String uid,
                                  @Field("f_name") String fname,
                                  @Field("password") String pass,
                                  @Field("l_name") String lnam,
                                  @Field("email") String email,
                                  @Field("phone_no") String phone,
                                  @Field("image_url") String imr_url
);



 @Headers({"Content-Type: application/json;charset=UTF-8"})
 @POST("?r=api/userapi/listfinancialtracking")
 Call<GetAccount> GET_ACCOUNT_CALL(@Body JsonObject jsonObject);
*/



/*{
"api_key": "SI1UJ2MA4IZ0YA",
"langauge" :"ar/en",
"user_id":"0",
"f_name": "james",
"password":"123",
 "l_name": "Bond",
 "email": "ijaz@geniteam.com",
 "phone_no":"+923444404160",
 "image_url":"base64image",
}
*/



  /*  @GET("Login")
    Call<LoginModel> loginUser(@QueryMap Map<String, String> options);
    @GET("Login")
    Call<LoginModel> loginUser2(@QueryMap Map<String, String> options);
*/
/**
    @POST(AppConst.Update_Profile)
    Call<LoginModel>postUpdateprofile(@Field(AppConst.U_FullName) String name,
                                      @Field(AppConst.U_Email) String email,
                                      @Field(AppConst.U_Password) String password,
                                      @Field(AppConst.U_DeviceToken) String token,
                                      @Field(AppConst.U_DeviceType) String devictype,
                                      @Field(AppConst.U_Image) String image);

    @FormUrlEncoded
    @POST(AppConst.KeySignup)
    Call<SignUpModel>postSignupUser(@Field(AppConst.U_FullName) String name,
                                    @Field(AppConst.U_Email) String email,
                                    @Field(AppConst.U_Password) String password,
                                    @Field(AppConst.U_DeviceToken) String token,
                                    @Field(AppConst.U_DeviceType) String devictype,


                                    @Field(AppConst.U_Image) String image);

                                    */
}
