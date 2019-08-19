package com.hlhealth.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author changxy
 *
 */
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = { "com.hlhealth.neo4j.repository" })
@EnableTransactionManagement
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
