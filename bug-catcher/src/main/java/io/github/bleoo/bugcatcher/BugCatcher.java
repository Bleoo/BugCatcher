package io.github.bleoo.bugcatcher;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import io.github.bleoo.bugcatcher.common.Loger;
import io.github.bleoo.bugcatcher.common.ThreadUtil;
import io.github.bleoo.bugcatcher.common.Utils;
import io.github.bleoo.bugcatcher.db.DBHelper;
import io.github.bleoo.bugcatcher.model.Bug;
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
        if (!checkConfig()) return;
        ThreadUtil.init();
        Utils.init(sConfig.getContext());
        DBHelper.init(sConfig.getContext());

        uploadLastData();
    }

    private static void getService() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(sConfig.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (sService == null) {
            sService = sRetrofit.create(BugService.class);
        }
    }

    public static class Config {

        private Context context;
        private String baseUrl;
        private boolean debug;

        public Config context(Context context) {
            this.context = context;
            return this;
        }

        public Config baseUrl(String url) {
            baseUrl = url;
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
            return baseUrl;
        }

        public boolean isDebug() {
            return debug;
        }
    }

    private static boolean checkConfig() {
        if (sConfig == null) {
            Loger.e("Config is null , are you init ?");
            return false;
        }
        if (!sConfig.isDebug()) {
            return false;
        }
        if (TextUtils.isEmpty(sConfig.getBaseUrl())) {
            Loger.e("BaseUrl of Config is null!");
            return false;
        }
        if (sConfig.getContext() == null) {
            Loger.e("Context of Config is null!");
            return false;
        }
        return true;
    }

    private static void uploadLastData() {
        ThreadUtil.run(new Runnable() {
            @Override
            public void run() {
                final List<Bug> bugList = DBHelper.getBugs();
                if (bugList == null || bugList.isEmpty()) {
                    return;
                }

                ThreadUtil.runOnMain(new Runnable() {
                    @Override
                    public void run() {
                        for (final Bug bug : bugList) {
                            bug.device = Utils.getDeviceInfo();

                            upload(bug, new OnUploadCallback() {
                                @Override
                                public void onSuccess(final Bug bug) {
                                    ThreadUtil.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            DBHelper.deleteBug(bug);
                                        }
                                    });
                                }

                                @Override
                                public void onFail(final Bug bug) {

                                }
                            });

                        }
                    }
                });

            }
        });
    }

    /**
     * BugCatcher.activate(new BugTrigger("#1817"));
     *
     * @param bugTrigger
     */
    public static void activate(BugTrigger bugTrigger) {
        if (!checkConfig()) return;
        if (bugTrigger == null) {
            return;
        }

        String info = bugTrigger.getListener().onActivated();
        Bug bug = bugTrigger.setUpBug(info);
        bug.device = Utils.getDeviceInfo();
        upload(bug, new OnUploadCallback() {
            @Override
            public void onSuccess(Bug bug) {

            }

            @Override
            public void onFail(final Bug bug) {
                ThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        DBHelper.insertBug(bug);
                    }
                });
            }
        });
    }

    private static void upload(final Bug bug, final OnUploadCallback callback) {
        getService();
        sService.upload(bug).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Loger.e("成功");
                    if (callback != null) {
                        callback.onSuccess(bug);
                    }
                    return;
                }
                if (callback != null) {
                    callback.onFail(bug);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Loger.e("失败");
                Loger.e(t.getMessage());
                if (callback != null) {
                    callback.onFail(bug);
                }
            }
        });
    }

    private interface OnUploadCallback {
        void onSuccess(Bug bug);

        void onFail(Bug bug);
    }
}
