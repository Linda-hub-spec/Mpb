package com.etz.MPB.portal;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = "com.etz.MPB.portal")
@SpringBootApplication
@EnableSwagger2
public class MpbPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpbPortalApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
