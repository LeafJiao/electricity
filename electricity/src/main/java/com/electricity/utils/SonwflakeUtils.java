package com.electricity.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

/**
 * 雪花算法，获取唯一ID
 *
 * @author jiaowei
 * @since 2024/11/11 11:04
 */
public class SonwflakeUtils {

    private Snowflake snowflake;

    /**
     * 成员类，SonwflakeUtils的实例对象保存域
     */
    private static class IdGenHolder {
        private static final SonwflakeUtils instance = new SonwflakeUtils();
    }

    /**
     * 私有化构造函数
     */
    private SonwflakeUtils() {
        String ipv4 = NetUtil.getLocalhostStr();
        long ipLong = Long.parseLong(ipv4.replaceAll("\\.", ""));
        long wokerId = Long.hashCode(ipLong) % 32;
        long datacenterId = 2L;
        snowflake = IdUtil.getSnowflake(wokerId, datacenterId);
    }

    /**
     * 外部调用获取实例对对象
     * @return
     */
    public static SonwflakeUtils getInstance() {
        return IdGenHolder.instance;
    }

    /**
     * 获取唯一ID
     * @return
     */
    public synchronized long id() {
        return snowflake.nextId();
    }

}