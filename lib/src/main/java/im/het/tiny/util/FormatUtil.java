package im.het.tiny.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by het on 16/4/23.
 */
public class FormatUtil {

    /**
     * 保留小数点后若干位
     *
     * @param d 要格式化的数
     * @param scale 小数点后的位数
     * @return
     */
    public static double getDecimal(double d, int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取百分比
     *
     * @param x     分子
     * @param total 分母
     *
     * @return
     */
    public static String getPercent(long x, long total) {
        double percent = x * 1.0 / total;
        NumberFormat nf = NumberFormat.getPercentInstance();
        return nf.format(percent);
    }

}
