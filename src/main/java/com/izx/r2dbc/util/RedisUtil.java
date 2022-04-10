package com.izx.r2dbc.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RedisUtil {

    public static final String KEY_PREFIX = "im:discord";

    public static String getKey(String... parts) {
        String keySuffix =
                Arrays.stream(parts).map(String::toLowerCase).collect(Collectors.joining(":"));
        return String.format("%s:%s", KEY_PREFIX, keySuffix);
    }
}
