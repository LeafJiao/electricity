package com.electricity;

import com.electricity.model.entity.User;
import com.electricity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Title: MybatisTest
 * @Author JiaoWei
 * @Package com.myblog
 * @Date 2024/3/11 16:36
 * @description: Mybatis测试
 */

@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void test() {
        User user = new User();
        user.setId(1);
        user.setUsername("jw");
        user.setAvatar("http://...");
    }
}
