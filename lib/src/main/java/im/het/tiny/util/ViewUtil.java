package im.het.tiny.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * View相关工具类.
 * <p/>
 * Created by het on 16/3/29.
 */
public final class ViewUtil {

    public static void setBackground(View v, Drawable d) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(d);
        } else {
            v.setBackgroundDrawable(d);
        }
    }

    private ViewUtil() {
    }
}
