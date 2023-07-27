package com.prismatic.pokedex.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {
    private Long id;
    private Integer level;
    private String name;
    private Double height;

    public boolean hasOverHundredLevel(){
        return this.level >= 100;
    }
}
