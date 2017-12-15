package io.github.bleoo.bugcatcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bleoo on 2017/12/12.
 */

public class BugCatcher {

    private static Retrofit retrofit;
    private static BugService service;

    public static void init(Context context) {

    }

    private static void getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.1.89:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (service == null) {
            service = retrofit.create(BugService.class);
        }
    }

    /**
     * BugCatcher.activate(new BugTrigger("#1817"));
     *
     * @param bugTrigger
     */
    public static void activate(@NonNull BugTrigger bugTrigger) {
        getService();

        String info = bugTrigger.getListener().onActivated();
        service.upload(bugTrigger.setUpBug(info)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){
                    Loger.e("成功");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Loger.e("失败");
                Loger.e(t.getMessage());
            }
        });
    }
}
