package com.company.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.company.mapper")
@ComponentScan("com.company.service")
@Configuration
public class TransactionConfig {

}
