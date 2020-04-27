/**
 *
 *   powered by Sanlion.Do
 */
package com.hebta.common.util;

import java.text.DecimalFormat;

/**
 *
 * @author sanlion
 */
public class NumberUtil {

    public static double prettyDouble(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return Double.valueOf(decimalFormat.format(value));
    }

    /**
     * 正整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) { return false; }
        }
        return true;
    }
}
