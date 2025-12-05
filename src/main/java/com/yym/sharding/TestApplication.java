package com.yym.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2025-12-02 14:30
 */
@MapperScan("com.yym.sharding.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class, args);
    }
}
