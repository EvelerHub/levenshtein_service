package com.demo.levenshtein;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.SocketUtils;

@SpringBootApplication
public class LevenshteinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevenshteinServiceApplication.class, args);
	}
}
