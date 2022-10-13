package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.ReservationTicketDao;
import com.entrevista.meli.melishows.Model.Entity.Price;
import com.entrevista.meli.melishows.Model.Entity.ReservationTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationTicketServiceImpl implements IReservationTicketService{
    @Autowired
    ReservationTicketDao reservationTicketDao;

    @Override
    public List<ReservationTicket> findAll() {
        return reservationTicketDao.findAll();
    }

    /**
     * Busca el ticket y filtra los precios para solo dejar los que estan relacionados a la funcion
     * @param id
     * @return
     */
    @Override
    public ReservationTicket findById(Long id) {
        ReservationTicket reservationTicket = reservationTicketDao.findById(id).orElse(null);

        filterPriceByFunction(reservationTicket);

        return reservationTicket;
    }

    /**
     * filtra el array por los precios relacionados a la funcion
     * @param reservationTicket
     */
    private static void filterPriceByFunction(ReservationTicket reservationTicket) {
        reservationTicket.getLstReservationTicket_Seat().forEach(
                reservationTicket_seat -> reservationTicket_seat.getSeat().getGroupSeat().setLstPrice(
                        reservationTicket_seat.getSeat().getGroupSeat().getLstPrice().stream().filter(
                                price -> price.getFunction().getId()==reservationTicket_seat.getFunction().getId()
                        ).collect(Collectors.toSet())
                )
        );
    }

    /**
     * Persiste los datos en la tabla de reserva de tickets
     * @param reservationTicket
     * @return
     */
    @Override
    public ReservationTicket save(ReservationTicket reservationTicket) {
        return reservationTicketDao.save(reservationTicket);
    }

}
