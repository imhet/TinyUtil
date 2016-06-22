package im.het.tiny.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/**
 * 网络相关工具类.
 * <p/>
 * Created by het on 16/3/29.
 */
public final class NetUtil {

    public static String getAppSignature(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
            Iterator<PackageInfo> iterator = apps.iterator();
            PackageInfo info;

            while (iterator.hasNext()) {
                info = iterator.next();
                if (TextUtils.equals(info.packageName, context.getPackageName())) {
                    if (info.signatures.length > 0) {
                        return info.signatures[0].toCharsString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getIP() {
        String ipInfo = null;
        try {
            Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
            LOOP:
            while (faces.hasMoreElements()) {
                Enumeration<InetAddress> addresses = faces.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipInfo = inetAddress.getHostAddress().toString();
                        break LOOP;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipInfo;
    }

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
