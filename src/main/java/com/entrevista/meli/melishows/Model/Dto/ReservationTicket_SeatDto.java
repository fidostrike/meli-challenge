package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Function;
import com.entrevista.meli.melishows.Model.Entity.ReservationTicket;
import com.entrevista.meli.melishows.Model.Entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
public class ReservationTicket_SeatDto {
    private Long id;

    private SeatDto seat;

    private FunctionDto function;
}
