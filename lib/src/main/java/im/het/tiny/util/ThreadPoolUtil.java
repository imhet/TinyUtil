package im.het.tiny.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by het on 16/3/29.
 */
public final class ThreadPoolUtil {

    private static ExecutorService mExecutor = null;

    public static void run(Runnable runnable) {
        if (mExecutor == null || mExecutor.isShutdown()) {
            mExecutor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        }

        if (mExecutor != null) {
            mExecutor.execute(runnable);
        }
    }


    private ThreadPoolUtil(){
    }
}
