package im.het.tiny.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * Created by het on 16/4/23.
 */
public class TextUtil {

    private static String RANDOM_STRING_SEED = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer(RANDOM_STRING_SEED);
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * capitalize first letter
     * <p/>
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     *
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arrayOfChar = str.toCharArray();
        arrayOfChar[0] = Character.toUpperCase(arrayOfChar[0]);
        return new String(arrayOfChar);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String replaceBlank(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 检查并纠正非法字符输入
     */
    public static String checkReplaceIllegalInput(String str) {
        String result = str;
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("/") || str.contains("\\") || str.contains("?")
                    || str.contains("*") || str.contains(":") || str.contains("<")
                    || str.contains(">") || str.contains("|")) {
                result = str.replaceAll("/", "").replace("\\", "").replace("?", "")
                        .replace("*", "").replace("<", "").replace(">", "").replace("|", "")
                        .replace(":", "");
            }
        }
        return result;
    }

}
