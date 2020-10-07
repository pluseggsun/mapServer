package com.bootdo.common.utils;

/**
 * ExceptionUtils
 *
 * @author system
 * @date 2018.09.26
 */
public class ExceptionUtils {
    public static String getExceptionAllinformation(Exception ex) {
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
}
