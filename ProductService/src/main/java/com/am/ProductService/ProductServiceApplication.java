package com.am.ProductService;

import com.am.ProductService.command_api.exception.ProductServiceEventsHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configure){
		configure.registerListenerInvocationErrorHandler("product", c -> new ProductServiceEventsHandler());
	}

}
