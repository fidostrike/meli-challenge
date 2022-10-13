package com.entrevista.meli.melishows.Model.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationTicketInputDto {
    String dni;
    String fullName;
    Long functionId;
    Long[] lstSeatIds;
}
