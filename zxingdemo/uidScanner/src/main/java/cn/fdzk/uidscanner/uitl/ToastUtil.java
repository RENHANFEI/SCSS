package cn.fdzk.uidscanner.uitl;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by konie on 2017/4/8.
 */

public class ToastUtil {

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showFetchError(Context context, String tag, Throwable t) {
        toast(context, "获取失败!");
        LogUtil.logError(tag, t);
    }

    public static void showOperationError(Context context, String tag, Throwable t) {
        toast(context, "操作失败!");
        LogUtil.logError(tag, t);
    }

}
