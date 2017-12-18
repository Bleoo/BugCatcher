package io.github.bleoo.bugcatcher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by bleoo on 2017/12/18.
 */

public class Utils {

    private static Context sAppContext;

    public static void init(Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static Device getDeviceInfo() {
        Device device = new Device();
        device.manufacturer = Build.MANUFACTURER;
        device.model = Build.MODEL;
        device.sdkVersion = Build.VERSION.SDK_INT;
        PackageManager pm = sAppContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(sAppContext.getPackageName(), 0);
            device.appVersion = info.versionName;
            device.appBuild = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }
}
