package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuditoriumWithSeatDto {
    private Long id;

    private String name;

}
