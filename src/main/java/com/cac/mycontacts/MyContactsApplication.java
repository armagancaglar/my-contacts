package com.cac.mycontacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EntityScan(basePackages = {"com.cac.mycontacts.entity"})
@PropertySource("file:mycontacts.properties")
public class MyContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyContactsApplication.class, args);
	}

}
