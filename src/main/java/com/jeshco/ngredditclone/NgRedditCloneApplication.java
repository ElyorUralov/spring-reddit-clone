package com.jeshco.ngredditclone;

import com.jeshco.ngredditclone.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class NgRedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgRedditCloneApplication.class, args);
	}

}
