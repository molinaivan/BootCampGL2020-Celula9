package ar.com.gl.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import ar.com.gl.zuul.filter.PostFilter;
import ar.com.gl.zuul.filter.PreFilter;

/**
 * @author Maribel
 *
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulGatewayApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

}
