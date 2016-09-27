package com.hualongdata.util;

/**
 * Created by yangbajing on 16-9-9.
 */
public class TimeUtils {
    public static long nowSeconds() {
        return nowMillis() / 1000;
    }

    public static long nowMillis() {
        return System.currentTimeMillis();
    }
}
