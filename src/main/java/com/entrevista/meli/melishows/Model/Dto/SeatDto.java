package com.entrevista.meli.melishows.Model.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SeatDto {
    private Long id;

    private String name;

    GroupSeatDto groupSeat;

}
