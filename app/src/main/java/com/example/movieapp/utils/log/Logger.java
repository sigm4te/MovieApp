package com.example.movieapp.utils.log;

import android.util.Log;

import com.example.movieapp.BuildConfig;

public class Logger {

    private static final String TAG = "MyApplication";

    public static void log(String message) {
        log(LogLevel.VERBOSE, message);
    }

    public static void logV(String message) {
        log(LogLevel.VERBOSE, message);
    }

    public static void logD(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void logI(String message) {
        log(LogLevel.INFO, message);
    }

    public static void logW(String message) {
        log(LogLevel.WARN, message);
    }

    public static void logE(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void logF(String message) {
        log(LogLevel.FATAL, message);
    }

    private static void log(LogLevel type, String message) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = getSimpleClassName(stackTrace[2].getClassName());
        String methodName = stackTrace[2].getMethodName();
        String logMessage = String.format("[%s.%s] %s", className, methodName, message == null ? "" : message);
        switch (type) {
            case VERBOSE:
                if (BuildConfig.LOG_VERBOSE) Log.v(TAG, logMessage);
                break;
            case DEBUG:
                if (BuildConfig.LOG_DEBUG) Log.d(TAG, logMessage);
                break;
            case INFO:
                if (BuildConfig.LOG_INFO) Log.i(TAG, logMessage);
                break;
            case WARN:
                if (BuildConfig.LOG_WARN) Log.w(TAG, logMessage);
                break;
            case ERROR:
                if (BuildConfig.LOG_ERROR) Log.e(TAG, logMessage);
                break;
            case FATAL:
                if (BuildConfig.LOG_FATAL) Log.wtf(TAG, logMessage);
                break;
        }
    }

    private static String getSimpleClassName(String fullClassName) {
        int lastDot = fullClassName.lastIndexOf('.');
        return lastDot == -1 ? fullClassName : fullClassName.substring(lastDot + 1);
    }
}
