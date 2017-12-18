package io.github.bleoo.bugcatcher;

import android.support.annotation.NonNull;

/**
 * Created by bleoo on 2017/12/12.
 */

public class BugTrigger {

    interface onTriggerListener {
        String onActivated();
    }

    private Bug bug;
    private onTriggerListener listener;

    public BugTrigger(String bugId, @NonNull onTriggerListener listener) {
        bug = new Bug();
        bug.id = bugId;
        bug.device = Utils.getDeviceInfo();
        this.listener = listener;
    }

    public String getBugId() {
        return bug.id;
    }

    public onTriggerListener getListener() {
        return listener;
    }

    public void setListener(@NonNull onTriggerListener listener) {
        this.listener = listener;
    }

    public Bug setUpBug(String info) {
        bug.info = info;
        return bug;
    }
}
