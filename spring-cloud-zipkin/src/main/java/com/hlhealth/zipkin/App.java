package com.hlhealth.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin2.server.internal.EnableZipkinServer;

/**
 * 	只作为测试使用 
 * 	建议 java -jar zipkin-server-2.12.9-exec.jar 启动zipkin
 * 
 * @author changxy
 *
 */
@SpringBootConfiguration
@EnableZipkinServer
@EnableAutoConfiguration
@EnableEurekaClient
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
