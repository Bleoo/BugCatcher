package io.github.bleoo.bugcatcher;

/**
 * Created by bleoo on 2017/12/15.
 */

public class Bug {
    public String id;
    public Device device;
    public String info;

    public Bug() {
    }

    public Bug(String id, Device device, String info) {
        this.id = id;
        this.device = device;
        this.info = info;
    }
}
