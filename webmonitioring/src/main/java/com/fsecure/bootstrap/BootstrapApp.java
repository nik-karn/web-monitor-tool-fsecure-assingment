package com.fsecure.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@PropertySource(value = "classpath:urldata.json")
@ConfigurationProperties
public class BootstrapApp implements CommandLineRunner {

	
	public BootstrapApp() {
		
	}
	
	
	ClassLoader classLoader = getClass().getClassLoader();	
	
	
	@Override
	public void run(String... args) throws Exception {		
		
	}

}
