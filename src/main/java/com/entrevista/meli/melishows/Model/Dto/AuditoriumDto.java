package com.entrevista.meli.melishows.Model.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditoriumDto {
    private Long id;

    private String name;

    TheaterDto theater;

}
