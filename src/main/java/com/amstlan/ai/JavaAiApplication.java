package com.amstlan.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amstlan.ai.*.mapper")
public class JavaAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAiApplication.class, args);
    }

}
