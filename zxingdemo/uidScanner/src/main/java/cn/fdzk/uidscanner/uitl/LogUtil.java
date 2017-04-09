package cn.fdzk.uidscanner.uitl;

import android.util.Log;

/**
 * Created by konie on 2017/4/8.
 */

public class LogUtil {

    public static void log(String tag, String message) {
        Log.i(tag, message);
    }

    public static void logError(String tag, Throwable t) {
        Log.e(tag, t.getMessage(), t);
    }

}
