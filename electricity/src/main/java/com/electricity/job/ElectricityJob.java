package com.electricity.job;

import com.electricity.service.ElectricityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jiaowei
 * @since 2024/9/30 21:52
 *
 * 电价预测定时任务
 */
@Component
@Slf4j
public class ElectricityJob {

    /**
     * redis客户端 用于锁住当前的定时任务
     */
    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ElectricityService electricityService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void predict() {
        RLock lock = redissonClient.getLock("electricity:lock");
        try {
            boolean tryLock = lock.tryLock(0, -1, TimeUnit.MILLISECONDS);
            log.warn("tryLock:{}", tryLock);
            if (tryLock) {
//                System.out.println(electricityService.getNow());
                log.warn("Thread.currentThread().getId(): {}", Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.warn("Thread.currentThread().getId():{}", Thread.currentThread().getId());
            }
        }
    }
}
