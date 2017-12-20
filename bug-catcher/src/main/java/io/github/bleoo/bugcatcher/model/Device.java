package io.github.bleoo.bugcatcher.model;

/**
 * Created by bleoo on 2017/12/15.
 */

public class Device {

    public String manufacturer;     //厂商
    public String model;            //型号
    public int sdkVersion;
    public String appVersion;
    public int appBuild;

    public Device() {
        manufacturer = "#unknow";
        model = "#unknow";
        appVersion = "#unknow";
    }

    public Device(String manufacturer, String model, int sdkVersion, String appVersion, int appBuild) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.sdkVersion = sdkVersion;
        this.appVersion = appVersion;
        this.appBuild = appBuild;
    }
}
