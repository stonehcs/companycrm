package com.lichi.increaselimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * 项目入口
 * @author majie
 *
 */
@SpringBootApplication
@ServletComponentScan  
@ImportResource(locations = { "classpath:druid-bean.xml" })		
public class IncreaseLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncreaseLimitApplication.class, args);
	}
}
