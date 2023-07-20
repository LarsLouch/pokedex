package com.webflux.pokedex.repository;

import com.webflux.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository <Pokemon, String>{





}
