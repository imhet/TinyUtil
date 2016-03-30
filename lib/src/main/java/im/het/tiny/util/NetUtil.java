package im.het.tiny.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络相关工具类.
 * <p/>
 * Created by het on 16/3/29.
 */
public final class NetUtil {

    public static void accessNetworkSetting(Activity activity) {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT > 10) {
                intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
            } else {
                intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
            }
            if (activity != null) {
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkOk() {
        ConnectivityManager cm = (ConnectivityManager) AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isWifi() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static String getNetworkType() {
        String networkName = "no-net";
        ConnectivityManager connectivityManager = (ConnectivityManager) AppContext.get().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            if (activeNetInfo.isConnected()) {
                if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    networkName = activeNetInfo.getSubtypeName();
                } else {
                    networkName = activeNetInfo.getTypeName();
                }
            }
        }
        return networkName;
    }

    private NetUtil() {
    }
}
