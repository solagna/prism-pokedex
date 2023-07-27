package com.prismatic.pokedex.service;

import com.prismatic.pokedex.pokemon.Pokemon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    List<Pokemon> pokemons = new ArrayList<>();

    public PokemonService() {
        Pokemon pikachu = new Pokemon(1L, 12, "Pikachu", 60.0);
        Pokemon suicune = new Pokemon(2L, 198, "Suicune", 195.0);
        Pokemon ekans = new Pokemon(3L, 1, "Ekans", 52.0);
        Pokemon totodile = new Pokemon(4L, 32, "Totodile", 98.0);
        Pokemon bulbassaur = new Pokemon(5L, 80, "Bulbassaur", 67.0);

        pokemons.add(pikachu);
        pokemons.add(suicune);
        pokemons.add(ekans);
        pokemons.add(totodile);
        pokemons.add(bulbassaur);
    }

    //ALL USERS
    public List<Pokemon> getPokemons() {
        return this.pokemons;
    }

    //ALL USERS
    public Pokemon getPokemonById(Long id) {
        for (Pokemon p : this.pokemons) {
            if (p.getId().equals(id))
                return p;
        }
         return null;
    }

    //ALL USERS
    public List<Pokemon> getPokemonsByLevel(Optional<Integer> level) {
        return  getPokemons().stream()
                .filter(o -> o.getLevel()  >= level.get())
                .collect(Collectors.toList());
    }

    //ALL USERS
    public List<Pokemon> getPokemonsHasHundredLevel() {
        return getPokemons().stream()
                .filter(Pokemon::hasOverHundredLevel)
                .collect(Collectors.toList());
    }

    //ADMIN
    public void createPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

    //ADMIN
    public void replacePokemon(Pokemon newPokemon, Long id){
        Optional<Pokemon> oldPokemon = Optional.ofNullable((getPokemonById(id)));

        if(oldPokemon.isPresent()){
            Pokemon pokemon = oldPokemon.get();
            pokemon.setName(newPokemon.getName());
            pokemon.setLevel(newPokemon.getLevel());
            pokemon.setHeight(newPokemon.getHeight());
            this.createPokemon(newPokemon);
        }
    }

}
