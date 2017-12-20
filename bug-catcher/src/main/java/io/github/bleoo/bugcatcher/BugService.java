package io.github.bleoo.bugcatcher;


import io.github.bleoo.bugcatcher.model.Bug;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BugService {

    @POST("api/")
    Call<ResponseBody> upload(@Body Bug bug);
}