package com.adkun.seckill.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author adkun
 */
public class ToolBox implements ErrorCode {

    public static final String salt = "zxcvf";

    /**
     * 生成md5
     * 加上salt
     * @param str 需要生成md5的字符串
     * @return md5
     */
    public static String md5(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new BusinessException(PARAMETER_ERROR, "参数不合法！");
        }
        return DigestUtils.md5DigestAsHex((str + salt).getBytes());
    }

    /**
     * 日期格式封装
     * @param date Date对象
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 生成验证码
     * @return
     */
    public static String getOTP() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
