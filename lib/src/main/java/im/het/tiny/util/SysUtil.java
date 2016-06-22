package im.het.tiny.util;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
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

    public static String getBaseStation() {
        String cellInfo = null;

        try {
            TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            CellLocation cellLocation = telephonyManager.getCellLocation();

            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                cellInfo = Integer.toString(gsmCellLocation.getCid());
            } else if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                cellInfo = Integer.toString(cdmaCellLocation.getBaseStationId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellInfo;
    }

    public static String getWifiMacAddress() {
        String wifiInfo = null;
        try {
            WifiManager wifiMgr = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
            wifiInfo = wifiMgr.getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiInfo;
    }

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

    public static boolean isSdCardOk() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static String getSimOperator() {
        TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tel.getSimOperator();
    }

    /**
     * 获取手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     */
    public static String getSystemManufacturer() {
        return Build.MANUFACTURER;
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

    public static String getNetworkCountry(Context aContext) {
        try {
            TelephonyManager tm = (TelephonyManager) aContext.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getNetworkCountryIso();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLocalCountry() {
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
