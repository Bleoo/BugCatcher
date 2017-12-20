package io.github.bleoo.bugcatcher.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import io.github.bleoo.bugcatcher.model.Device;

/**
 * Created by bleoo on 2017/12/18.
 */

public class Utils {

    private static Context sAppContext;
    private static Device sDevice;

    public static void init(Context context) {
        sAppContext = context.getApplicationContext();
        getDeviceInfo();
    }

    public static Device getDeviceInfo() {
        if (sDevice != null) {
            return sDevice;
        }
        sDevice = new Device();
        sDevice.manufacturer = Build.MANUFACTURER;
        sDevice.model = Build.MODEL;
        sDevice.sdkVersion = Build.VERSION.SDK_INT;
        PackageManager pm = sAppContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(sAppContext.getPackageName(), 0);
            sDevice.appVersion = info.versionName;
            sDevice.appBuild = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sDevice;
    }
}
