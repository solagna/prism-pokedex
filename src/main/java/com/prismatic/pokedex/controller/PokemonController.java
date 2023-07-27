package com.prismatic.pokedex.controller;

import com.prismatic.pokedex.pokemon.Pokemon;
import com.prismatic.pokedex.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/pokemons")
@Slf4j
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    //ALL USERS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pokemon>> getPokemons(@RequestParam("level") Optional<Integer> level){
        if(level.isPresent())
            return new ResponseEntity<>(pokemonService.getPokemonsByLevel(level), HttpStatus.OK);

        return new ResponseEntity<>(pokemonService.getPokemons(), HttpStatus.OK);
    }

    //ALL USERS
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> getPokemon(@PathVariable Long id){
        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonService.getPokemonById(id));

        if (pokemon.isPresent())
            return new ResponseEntity<>(pokemon.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //ADMIN USERS
    @PostMapping(value = "/pokemon", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon){
        pokemonService.createPokemon(pokemon);
       return ResponseEntity.created(URI.create(String.format("/pokemon/%s", pokemon.getName()))).body(pokemon);
    }

    //ADMIN USERS
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> replacePokemon(@RequestBody Pokemon pokemon, @PathVariable Long id){
        pokemonService.replacePokemon(pokemon, id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    //ADMIN USERS
    @DeleteMapping
    public void deletePokemon(){}

    //ADMIN USERS
    @PatchMapping
    public void patchPokemon(){}

}
