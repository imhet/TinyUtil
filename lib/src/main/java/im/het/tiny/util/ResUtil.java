package im.het.tiny.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 资源获取工具.
 * <p/>
 * Created by het on 16/3/29.
 */
public final class ResUtil {

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

    private static DisplayMetrics getMetrics() {
        return AppContext.get().getResources().getDisplayMetrics();
    }

    private ResUtil() {
    }
}
