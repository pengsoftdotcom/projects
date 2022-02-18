package com.pengsoft.project.scs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.jeecg")
@SpringBootApplication
public class ScsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScsApplication.class, args);
    }

}
