package im.het.tiny.util;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 资源获取工具.
 * <p/>
 * Created by het on 16/3/29.
 */
public final class ResUtil {

    private static Resources res = AppContext.get().getResources();

    public static Drawable getDrawable(int resID) {
        return res.getDrawable(resID);
    }

    public static int getColor(int resID) {
        return res.getColor(resID);
    }

    public static String[] getStrArray(int resID) {
        return res.getStringArray(resID);
    }

    public static String getStr(int resID) {
        return res.getString(resID);
    }

    public static int getDimen(int resID) {
        return res.getDimensionPixelSize(resID);
    }

    public static ColorStateList getColorStateList(int resID) {
        return (ColorStateList) res.getColorStateList(resID);
    }

    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getMetrics());
    }

    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getMetrics());
    }

    public static float px2dp(float px) {
        final float scale = getMetrics().density;
        return (px / scale);
    }

    public static float px2sp(float px) {
        return (px / getMetrics().scaledDensity);
    }

    /*
     * 反射获取资源
     */
    private static int getIdentifier(String name, String defType) {
        String s = AppContext.get().getPackageName();
        int identifier = AppContext.get().getResources().getIdentifier(name, defType, s);
        if (identifier == 0) {
            L.e("resource " + name + ", type " + defType + ", undefined.");
        }
        return identifier;
    }

    private static DisplayMetrics getMetrics() {
        return AppContext.get().getResources().getDisplayMetrics();
    }

    private ResUtil() {
    }
}
