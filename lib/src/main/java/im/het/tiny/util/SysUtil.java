package im.het.tiny.util;

import java.util.Locale;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 获取系统相关信息工具类
 * <p/>
 * Created by het on 16/3/29.
 */
public final class SysUtil {

    private static Context getContext() {
        return AppContext.get();
    }

    public static String getSimOperator() {
        TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tel.getSimOperator();
    }

    public static int getOsVerion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId();
    }

    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getAndroidID() {
        return Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getMeta(String key) {
        try {
            ApplicationInfo info = getContext().getPackageManager()
                    .getApplicationInfo(getContext().getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getVersionName() {
        PackageManager pm = getContext().getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(getContext().getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            L.printStackTrace(e);
        }
        return "";
    }

    public static String getVersionCode() {
        PackageManager pm = getContext().getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(getContext().getPackageName(), 0);
            return String.valueOf(pi.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            L.printStackTrace(e);
        }
        return "";
    }

    public static String getLocalLanguage() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "";
        }
        return locale.getLanguage();
    }

    private static String getLocalCountry() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "";
        }
        return locale.getCountry();
    }

    public static String getSimCountry() {
        TelephonyManager telManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String countryIso = telManager.getSimCountryIso();
        if (TextUtils.isEmpty(countryIso)) {
            countryIso = telManager.getNetworkCountryIso();
        }
        if (countryIso == null) {
            countryIso = "";
        }

        return countryIso;
    }

    private SysUtil() {
    }

}
