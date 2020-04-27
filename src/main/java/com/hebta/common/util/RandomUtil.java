package com.hebta.common.util;

import java.util.Random;

public class RandomUtil {
    private static final String RANDOM_STR = "9876543210ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private static final String RANDOM_NUM_STR = "0123456789";

    /**
     * 获取指定位数随机数
     * <br/>
     * 随机数范围：9876543210ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * @param num
     * @return
     */
    public static final String getRandom(int num) {
        StringBuilder sb = new StringBuilder(num);
        Random randdom = new Random();
        for (int i = 0, index = 0; i < num; i++) {
            index = randdom.nextInt(RANDOM_STR.length());
            sb.append(RANDOM_STR.charAt(index));
        }
        return sb.toString();
    }
    
    /**
     * 获取指定位数随机数
     * <br/>
     * 随机数范围：0123456789
     * @param num
     * @return
     */
    public static final String getNumRandom(int num) {
        StringBuilder sb = new StringBuilder(num);
        Random randdom = new Random();
        for (int i = 0, index = 0; i < num; i++) {
            index = randdom.nextInt(RANDOM_NUM_STR.length());
            sb.append(RANDOM_NUM_STR.charAt(index));
        }
        return sb.toString();
    }
    
}
