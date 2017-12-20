package io.github.bleoo.bugcatcher.common;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bleoo on 2017/12/20.
 */

public class ThreadUtil {

    private static Handler sHandler;
//    private static ExecutorService sExecutor;

    private ThreadUtil() {
    }

    public static void init() {
        sHandler = new Handler(Looper.getMainLooper());
//        sExecutor = Executors.newFixedThreadPool(3);
    }

    public static void run(Runnable runnable) {
//        sExecutor.execute(runnable);
        new Thread(runnable).start();
    }

    public static void runOnMain(Runnable runnable) {
        sHandler.post(runnable);
    }
}
