package im.het.tiny.util;

import java.util.Map;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;

/**
 * Preference 工具类
 * <p/>
 * Created by het on 16/3/29.
 */
public final class PrefUtil {

    private static SharedPreferences mPreferences;

    private PrefUtil() {
    }

    private static Context getContext() {
        return AppContext.get();
    }

    private static SharedPreferences getSharedPreferences() {
        if (mPreferences == null) {
            mPreferences = getContext().getSharedPreferences(getContext().getPackageName() + "_pref", Context
                    .MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public static void putBundleString(Map<String, String> map) {
        Editor editor = getEditor();
        for (String key : map.keySet()) {
            editor.putString(key, map.get(key));
        }
        apply(editor);
    }

    public static void putString(String key, String value) {
        apply(getEditor().putString(key, value));
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        apply(getEditor().putInt(key, value));
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        apply(getEditor().putLong(key, value));
    }

    public static long getLong(String key) {
        return getLong(key, -1);
    }

    public static long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        apply(getEditor().putFloat(key, value));
    }

    public static float getFloat(String key) {
        return getFloat(key, -1);
    }

    public static float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        apply(getEditor().putBoolean(key, value));
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    public static void remove(String key) {
        apply(getEditor().remove(key));
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static void apply(Editor editor) {
        if (Build.VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

}
