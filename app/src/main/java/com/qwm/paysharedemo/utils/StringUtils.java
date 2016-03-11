package com.qwm.paysharedemo.utils;

import com.qwm.paysharedemo.constants.Constants;

import java.util.Random;

/**
 * @author qiwenming
 * @date 2016/3/11 0011 下午 4:30
 * @ClassName: StringUtils
 * @PackageName: com.qwm.paysharedemo.utils
 * @Description: 一些字符串相关的工具类
 */
public class StringUtils {


    /**
     *     生成随机字符串
     */
    public static String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }



}
