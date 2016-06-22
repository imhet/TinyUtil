package im.het.tiny.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * Created by het on 16/4/23.
 */
public class AppUtil {

    public static boolean isServiceRunning(Context context, String serviceName) {
        boolean isServiceRunning = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo info : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (TextUtils.equals(serviceName, info.service.getClassName())) {
                isServiceRunning = true;
                break;
            }
        }
        return isServiceRunning;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        boolean isInstalled = false;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (null != info) {
                isInstalled = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }

}
