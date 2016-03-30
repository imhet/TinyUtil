package im.het.tiny.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by het on 16/3/31.
 */
public class L {

    public static final boolean OPEN = true;
    public static final String DEFAULT_TAG = "tinyutil";

    private enum Level {
        D, E, I, V, W
    }

    private L() {
    }

    public static void v(String msg) {
        v(DEFAULT_TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (OPEN) {
            doLog(tag, Level.V, msg, null);
        }
    }

    public static void d(String msg) {
        d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (OPEN) {
            doLog(tag, Level.D, msg, null);
        }
    }

    public static void i(String msg) {
        i(DEFAULT_TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (OPEN) {
            doLog(tag, Level.I, msg, null);
        }
    }

    public static void w(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (OPEN) {
            doLog(tag, Level.W, msg, null);
        }
    }

    public static void e(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (OPEN) {
            doLog(tag, Level.E, msg, null);
        }
    }

    public static void h(Object... args) {
        if (OPEN) {
            doLog(DEFAULT_TAG, Level.D, TextUtils.join(" , ", args), null);
        }
    }

    public static void printStackTrace() {
        if (OPEN) {
            i(Log.getStackTraceString(new Exception()));
        }
    }

    public static void printStackTrace(Throwable throwable) {
        if (OPEN) {
            e(throwable.getMessage());
        }
    }

    private static void doLog(String tag, Level level, String msg, Throwable t) {
        String fileSuffix = ".java";

        StackTraceElement stackTrace = (new Throwable()).getStackTrace()[2];
        String fileName = stackTrace.getFileName();
        String methodName = stackTrace.getMethodName();
        int linenumber = stackTrace.getLineNumber();
        if (fileName != null && fileName.contains(fileSuffix)) {
            fileName = fileName.replace(fileSuffix, "");
        }

        String output = String.format("[%s: %s: %d]%s", fileName, methodName, linenumber, msg);

        switch (level) {
            case D:
                if (t == null) {
                    Log.d(tag, output);
                } else {
                    Log.d(tag, output, t);
                }
                break;
            case E:
                if (t == null) {
                    Log.e(tag, output);
                } else {
                    Log.e(tag, output, t);
                }
                break;
            case I:
                if (t == null) {
                    Log.i(tag, output);
                } else {
                    Log.i(tag, output, t);
                }
                break;
            case V:
                if (t == null) {
                    Log.v(tag, output);
                } else {
                    Log.v(tag, output, t);
                }
                break;
            case W:
                if (t == null) {
                    Log.w(tag, output);
                } else {
                    Log.w(tag, output, t);
                }
                break;
            default:
                break;
        }
    }
}
