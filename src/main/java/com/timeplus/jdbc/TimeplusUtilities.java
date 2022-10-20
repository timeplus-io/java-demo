package com.timeplus.jdbc;

public class TimeplusUtilities {
    public static String getMethodName() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[1]
                .getMethodName();
        return nameofCurrMethod;
    }
}
