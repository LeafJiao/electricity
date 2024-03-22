package com.electricity;

import com.electricity.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: JwtTest
 * @Author JiaoWei
 * @Package com.myblog
 * @Date 2024/3/14 22:26
 * @description: token测试
 */
@SpringBootTest
public class JwtTest {

    @Test
    public void test() {
        String token = JwtUtil.createJWT("aaaa");
        System.out.println("Token = " + token);

        try {
            Claims claims = JwtUtil.parseJWT(token);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date((Long) claims.get("createTime"));
//            System.out.println(format.format(date));
            System.out.println(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
