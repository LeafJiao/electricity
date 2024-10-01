package com.electricity.interceptors;

import com.electricity.exception.GlobalException;
import com.electricity.utils.JwtUtil;
import com.electricity.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Title: LoginInterceptor
 * @Author JiaoWei
 * @Package com.myblog.interceptors
 * @Date 2024/3/14 21:51
 * @description: 登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // 令牌验证
        String token = request.getHeader("Authorization");
        // 验证token
        try {
            //从redis中获取相同的token
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            String redisToken = opsForValue.get(token);
            if (redisToken == null) {
                //token已经失效了, 捕捉的异常被覆盖
                throw new RuntimeException();
            }
            Claims claims = JwtUtil.parseJWT(token);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims.getSubject());
            //放行
            return true;
        } catch (Exception e) {
            log.warn("路径:{}", request.getRequestURI());
            throw new GlobalException("用户未登录");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
