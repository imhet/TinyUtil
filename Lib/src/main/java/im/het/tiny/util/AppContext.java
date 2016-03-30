package im.het.tiny.util;

import android.app.Application;
import android.content.Context;

/**
 * 在这里设置Application Context , 方便工具类某些方法不需要传递Context
 * <p/>
 * Created by het on 16/3/30.
 */
public class AppContext {

    private static Context mApplicationContext;

    public static void set(Context context) {
        if (context == null) {
            throw new RuntimeException("context cannot set to null");
        }

        if (context instanceof Application) {
            mApplicationContext = context;
        } else {
            mApplicationContext = context.getApplicationContext();
        }
    }

    public static Context get() {
        return mApplicationContext;
    }
}
