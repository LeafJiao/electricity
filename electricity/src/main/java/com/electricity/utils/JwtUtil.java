package com.electricity.utils;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * @Title: JwtUtil
 * @Author JiaoWei
 * @Package com.myblog.utils
 * @Date 2024/3/14 21:56
 * @description: 生成token
 */
public class JwtUtil {

    // 有效期为
    public static final Long JWT_TTL = (long) (1000 * 60 * 60); //一天

    // 设置密钥明文 新版本必须大于256字节
    public static final String JWT_KEY = "";

    /**
     用于生成uuid，用来表示唯一
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");//token用UUID来代替
        return uuid;
    }

    /**
     * 生成加密后的密钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
        return key;
    }

    /**
     * 构造token
     * @param subject
     * @param ttlMillis
     * @param uuid
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<>();
        claims.put("subject", subject);
        claims.put("createTime", nowMillis);
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis; // 过期时间戳
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)               // 唯一的ID
                .setClaims(claims)         // 主题，当前为JSON数据
                .setSubject(subject)       // 主题 可以是JSON数据
                .setIssuer("jiaowei")      // 签发者
                .setIssuedAt(now)          // 签发时间
                .signWith(secretKey, signatureAlgorithm)  //  第一个参数为密钥,第二个使用HS256对称加密算法签名
                .setExpiration(expDate);                  // 过期时间
    }

    /**
        id : 唯一标识
        subject : 我们想要加密存储的数据
        ttl :  我们想要设置的过期时间
     */

    /**
     * 生成token jwt加密 subject token中要存放的数据（json格式）
     * @param subject
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID()); // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成token jwt加密
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID()); // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成token jwt加密
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); // 设置过期时间
        return builder.compact();
    }

    /*
     更新token
     */

    /**
     * 判断token是否过期
     * @param token
     * @return
     * @throws Exception
     */
    public static Boolean isTokenExpired(String token) {
        Claims claims = null;
        try {
            claims = parseJWT(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新token（如果过期时间不足一个小时）
     * @param token
     * @return
     * @throws Exception
     */
    public static String refreshToken(String token) {
        Claims claims = null;
        try {
            claims = parseJWT(token);
            Date expiration = claims.getExpiration();
            // 如果token过期时间不足 一个小时 刷新token
            if (expiration.getTime() - System.currentTimeMillis() < 1000 * 60 * 60 * 1) {
                // 默认延长 JWT_TTL
                return createJWT(claims.getSubject());
            }
        } catch (Exception e) {
            return token;
        }
        return token;
    }

    /**
     * jwt解密
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String token) {
        SecretKey secretKey = generalKey();
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token) // parseClaimsJws
                .getBody();
    }
}
