package centripio.masteringreact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class MovieStoreApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieStoreApiApplication.class, args);
	}
}
