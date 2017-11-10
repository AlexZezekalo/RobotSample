package com.nixsolutions.robotsdk.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nixsolutions.robotsdk.BuildConfig;


public class Logger {

    private static final String GLOBAL_TAG = "RobotSample";

    private static boolean GLOBAL_FLAG = !BuildConfig.DEBUG;

    private String tag;

    private int level = Log.VERBOSE;

    private boolean debug = BuildConfig.DEBUG;

    private Logger(@NonNull Class<?> cls) {
        this.tag = GLOBAL_TAG + "->" + cls.getSimpleName();
    }

    @NonNull
    public static Logger createLogger(Class<?> cls) {
        return new Logger(cls);
    }

    public void verbose(String msg) {
        if (level <= Log.VERBOSE || debug) {
            Log.v(tag, msg);
        }
    }

    public void debug(String msg) {
        if (level <= Log.DEBUG || debug) {
            Log.d(tag, msg);
        }
    }

    public void info(String msg) {
        if (level <= Log.INFO || debug) {
            Log.i(tag, msg);
        }
    }

    public void warn(String msg) {
        if (level <= Log.WARN || debug) {
            Log.w(tag, msg);
        }
    }

    public void error(String msg) {
        if (level <= Log.ERROR || debug) {
            Log.e(tag, msg);
        }
    }

    public void error(@NonNull Throwable exception) {
        if (level <= Log.ERROR || debug) {
            exception.printStackTrace();
        }
    }

    public void error(String message, @NonNull Throwable exception) {
        if (level <= Log.ERROR || debug) {
            Log.e(tag, message);
            exception.printStackTrace();
        }
    }


}
