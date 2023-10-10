package com.holestorysb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
//public class HolestorysbApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(HolestorysbApplication.class, args);
//	}
//}

//TODO we need to use the above ^ and figure out how to se authorization and authentication for when they log in, but for now we are bypassing it

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HolestorysbApplication {
	public static void main(String[] args) {
		SpringApplication.run(HolestorysbApplication.class, args);
	}
}
