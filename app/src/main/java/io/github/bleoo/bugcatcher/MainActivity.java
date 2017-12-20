package io.github.bleoo.bugcatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_info = findViewById(R.id.et_info);

        final BugTrigger bugTrigger = new BugTrigger("103", new BugTrigger.onTriggerListener() {
            @Override
            public String onActivated() {
                return et_info.getText().toString();
            }
        });

        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BugCatcher.activate(bugTrigger);
            }
        });
    }
}
