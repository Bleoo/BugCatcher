package io.github.bleoo.bugcatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BugTrigger bugTrigger = new BugTrigger("103", new BugTrigger.onTriggerListener() {
            @Override
            public String onActivated() {
                return "android 测试1";
            }
        });

        BugCatcher.activate(bugTrigger);
    }
}
