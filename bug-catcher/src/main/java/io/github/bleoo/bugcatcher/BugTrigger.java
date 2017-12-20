package io.github.bleoo.bugcatcher;

import io.github.bleoo.bugcatcher.model.Bug;

/**
 * Created by bleoo on 2017/12/12.
 */

public class BugTrigger {

    public interface onTriggerListener {
        String onActivated();
    }

    private Bug bug;
    private onTriggerListener listener;

    public BugTrigger(String bugId, onTriggerListener listener) {
        bug = new Bug();
        bug.id = bugId;
        this.listener = listener;
    }

    public String getBugId() {
        return bug.id;
    }

    public onTriggerListener getListener() {
        return listener;
    }

    public void setListener(onTriggerListener listener) {
        this.listener = listener;
    }

    public Bug setUpBug(String info) {
        bug.info = info;
        return bug;
    }
}
