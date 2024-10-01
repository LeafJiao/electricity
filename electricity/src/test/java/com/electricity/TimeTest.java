package com.electricity;
import java.util.Date;

import com.electricity.model.entity.Electricity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jiaowei
 * @since 2024/10/1 21:36
 */
@SpringBootTest
@Slf4j
public class TimeTest {

    @Test
    public void test() {
        Electricity electricity = new Electricity();
        electricity.setId(0L);
        electricity.setLowest(0.0D);
        electricity.setMiddle(0.0D);
        electricity.setHighest(0.0D);
        electricity.setCreateTime(new Date());
        electricity.setUpdateTime(new Date());

        log.warn("tiem:{}", electricity);
    }
}
