package br.com.acoaapi.utils;

public class NumberUtils {

    public static Double roundWithTwoDecimals(Double value) {
        if (null == value) return 0.0;
        return Math.round(value * 100.0) / 100.0;
    }
}
