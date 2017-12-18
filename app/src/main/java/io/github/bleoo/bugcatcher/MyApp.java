package io.github.bleoo.bugcatcher;

import android.app.Application;

/**
 * Created by bleoo on 2017/12/15.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BugCatcher.init(new BugCatcher.Config()
                .context(this)
                .baseUrl("http://10.0.1.89:3000")
                .debug(true));
    }
}
