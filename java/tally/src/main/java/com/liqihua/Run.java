package com.liqihua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages={"com.*"})
@SpringBootApplication
public class Run {

	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
}
