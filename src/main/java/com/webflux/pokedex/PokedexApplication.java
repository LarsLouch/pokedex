package com.webflux.pokedex;

import com.webflux.pokedex.model.Pokemon;
import com.webflux.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {

		SpringApplication.run(PokedexApplication.class, args);}

		@Bean
		CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository){
		return args -> {
			//fluxo de dados
			Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon(null, "Squirtle", "Água", "Jato de Água", 9.0 ),
					new Pokemon(null, "Bulbasaur", "Grama", "Chicote de Vinha", 10.0),
					new Pokemon(null, "Charmander", "Fogo", "Lança Chamas", 9.0),
					new Pokemon(null, "Pikachu", "Elétrico", "Choque de Trovão", 10.0),
					new Pokemon(null, "Butterfree", "Voador", "Voar", 5.0))
					.flatMap(repository::save);

			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};



		}
	}


