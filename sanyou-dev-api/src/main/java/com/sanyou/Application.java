package com.sanyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: asus
 * Date: 2021/5/23
 * Time: 23:50
 * Version:V1.0
 */
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.sanyou","org.n3r.idworker"})
@MapperScan(basePackages = {"com.sanyou.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
