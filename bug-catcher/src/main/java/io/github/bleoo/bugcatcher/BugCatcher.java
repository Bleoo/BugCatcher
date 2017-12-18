package io.github.bleoo.bugcatcher;

import android.content.Context;
import android.text.TextUtils;

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

    private static Retrofit sRetrofit;
    private static BugService sService;
    private static Config sConfig;

    public static void init(Config config) {
        sConfig = config;
        Utils.init(sConfig.context);
        Utils.getDeviceInfo();
    }

    private static void getService() {
        if (TextUtils.isEmpty(sConfig.getBaseUrl())) {
            Loger.e("BaseUrl of Config is null!");
            return;
        }

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(sConfig.basUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (sService == null) {
            sService = sRetrofit.create(BugService.class);
        }
    }

    public static class Config {

        private Context context;
        private String basUrl;
        private boolean debug;

        public Config context(Context context) {
            this.context = context;
            return this;
        }

        public Config baseUrl(String url) {
            basUrl = url;
            return this;
        }

        public Config debug(boolean enable) {
            debug = enable;
            return this;
        }

        public Context getContext() {
            return context;
        }

        public String getBaseUrl() {
            return basUrl;
        }

        public boolean isDebug() {
            return debug;
        }
    }

    /**
     * BugCatcher.activate(new BugTrigger("#1817"));
     *
     * @param bugTrigger
     */
    public static void activate(BugTrigger bugTrigger) {
        if (bugTrigger == null) {
            return;
        }
        if (sConfig == null) {
            Loger.e("Config is null , are you init ?");
        }
        if (!sConfig.isDebug()) {
            return;
        }

        getService();

        String info = bugTrigger.getListener().onActivated();
        sService.upload(bugTrigger.setUpBug(info)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
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
