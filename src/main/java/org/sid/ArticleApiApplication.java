package org.sid;

import org.sid.domaine.Artist;
import org.sid.repository.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArticleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(ArtistRepository repository) {
	    return (args) -> {
	        repository.save(new Artist("samuel1234","Samuel", "M", 1));
	        repository.save(new Artist("gildas00","Gildas", "F", 1));
	        repository.save(new Artist("julle00","Jules", "M", 1));
	    };
	}

}
