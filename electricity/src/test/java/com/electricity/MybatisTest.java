package com.electricity;

import com.electricity.model.entity.BlogUser;
import com.electricity.mapper.BlogUserMapper;
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
    private BlogUserMapper blogUserMapper;
    @Test
    public void test() {
        BlogUser blogUser = new BlogUser();
        blogUser.setId(1);
        blogUser.setUsername("jw");
        blogUser.setAvatar("http://...");
    }
}
