package com.gd.data;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.gd.data.mapper")
@EnableScheduling
public class DataApplication{

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(DataApplication.class );
        application.setApplicationContextClass( AnnotationConfigApplicationContext.class );
        application.run( args );

//        String path = System.getProperty("classpath:logback-spring.xml");
//        PropertyConfigurator.configure(path);

        CountDownLatch latch = new CountDownLatch( 1 );
        latch.await();
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//
//        return builder.sources(DataApplication.class);
//
//    }

}
