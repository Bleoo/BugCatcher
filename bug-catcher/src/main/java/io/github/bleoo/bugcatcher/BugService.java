package io.github.bleoo.bugcatcher;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BugService {

    @POST("api/")
    Call<ResponseBody> upload(@Body Bug bug);
}