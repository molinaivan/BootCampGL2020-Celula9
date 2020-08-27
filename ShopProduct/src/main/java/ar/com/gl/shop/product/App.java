package ar.com.gl.shop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App {	
	public static void main(final String[] args) {
		SpringApplication.run(App.class);
	}
	
}
