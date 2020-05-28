package com.xjtuse.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.xjtuse.mall.mapper")
@EnableAspectJAutoProxy
public class SmallApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmallApplication.class, args);
	}
}
