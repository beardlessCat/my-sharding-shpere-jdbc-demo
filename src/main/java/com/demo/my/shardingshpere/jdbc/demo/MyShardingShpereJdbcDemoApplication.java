package com.demo.my.shardingshpere.jdbc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.my.shardingshpere.jdbc.demo.mapper")
public class MyShardingShpereJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShardingShpereJdbcDemoApplication.class, args);
    }

}
