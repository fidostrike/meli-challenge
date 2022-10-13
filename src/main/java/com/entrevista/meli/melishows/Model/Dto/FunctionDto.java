package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FunctionDto {
    private Long id;

    AuditoriumDto auditorium;

    //ShowDto show;

    private Date dateFrom;

    private Date dateTo;
}
