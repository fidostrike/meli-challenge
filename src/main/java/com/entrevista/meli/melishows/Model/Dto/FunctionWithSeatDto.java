package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FunctionWithSeatDto{
    private Long id;

    private AuditoriumWithSeatDto auditorium;

    private Set<Seat> lstSeat = new HashSet<>();

    private Date dateFrom;

    private Date dateTo;
}
