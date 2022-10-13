package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Price;
import com.entrevista.meli.melishows.Model.Entity.ReservationTicket;

import java.util.List;

public interface IReservationTicketService {
    public List<ReservationTicket> findAll();

    public ReservationTicket findById(Long id);

    public ReservationTicket save(ReservationTicket reservationTicket);
}