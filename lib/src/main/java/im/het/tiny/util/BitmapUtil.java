package im.het.tiny.util;

import android.graphics.Bitmap;

/**
 * Created by het on 16/4/23.
 */
public class BitmapUtil {

    public static boolean isRoundCornerBitmap(Bitmap bitmap) {
        int color = bitmap.getPixel(0, 0);
        return ((color >> 24) & 0xFF) == 0;
    }


}
