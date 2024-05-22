package com.one.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDto(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoMorte
) {
}
