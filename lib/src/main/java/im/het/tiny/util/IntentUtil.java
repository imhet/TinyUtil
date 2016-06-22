package im.het.tiny.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by het on 16/4/23.
 */
public class IntentUtil {

    /**
     * 开启Market app，如果没有，则进入google play网页版
     */
    public static void showAppMarket(Context context, String packageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        } catch (Exception e) {
            // 没有安装任何market
            String url = "https://play.google.com/store/apps/details?id=" + packageName;
            // FIXME
        }
    }

}
