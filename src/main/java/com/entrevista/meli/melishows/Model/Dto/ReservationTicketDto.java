package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.ReservationTicket_Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ReservationTicketDto {
    private Long id;

    private String dni;

    private String fullName;

    Float finalPrice;

    private Set<ReservationTicket_SeatDto> lstReservationTicket_Seat = new HashSet<>();
}
