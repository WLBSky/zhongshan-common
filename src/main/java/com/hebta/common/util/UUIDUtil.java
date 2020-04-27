package com.hebta.common.util;

import java.util.UUID;

public class UUIDUtil {

    private static UUIDUtil me;

    private UUIDUtil() {
    }

    private synchronized static UUIDUtil getInstance() {
        return null == me ? me = new UUIDUtil() : me;
    }

    public static String getUUId() {
        return getInstance().buildUUId();
    }

    private String buildUUId() {
        StringBuilder buff = new StringBuilder().append(RandUtil.nextDouble())
                .append(System.nanoTime()).append(RandUtil.nextInt(Integer.MAX_VALUE));
        return UUID.nameUUIDFromBytes(buff.toString().getBytes()).toString().replace("-", "");
    }
    
}
