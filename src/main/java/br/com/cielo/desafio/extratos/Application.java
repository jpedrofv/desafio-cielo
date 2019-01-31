package br.com.cielo.desafio.extratos;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages= {"br.com.cielo.desafio.extratos","com.mangofactory.swagger"})
public class Application extends SpringBootServletInitializer {

		@Override
		protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
			return builder.sources(Application.class);
		}

		private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
			return builder.sources(Application.class);
		}

		public static void main(String[] args) {
			configureApplication(new SpringApplicationBuilder()).run(args);
		}	

}

