package com.hebta.common.util;

import java.util.Random;

/**
 * @Description: 随机算法工具类
 * @author: edward
 * @date: 2013-7-4 下午4:16:23
 * @version: V1.0
 */
public class RandUtil {

    private static RandUtil me;
    private Random rand;

    private RandUtil() {
        rand = new Random();
    }

    private synchronized static RandUtil getInstace() {
        return null == me ? me = new RandUtil() : me;
    }

    public static int nextInt(int n_num) {
        return getInstace().rand.nextInt(n_num);
    }

    public static int nextInt(int n_minNum, int n_maxNum) {
        return n_minNum + getInstace().rand.nextInt(n_maxNum - n_minNum + 1);
    }

    public static double nextDouble() {
        return getInstace().rand.nextDouble();
    }
}
