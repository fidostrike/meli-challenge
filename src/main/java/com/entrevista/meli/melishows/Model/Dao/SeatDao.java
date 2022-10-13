package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Seat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDao extends JpaRepository<Seat,Long>{

    final String SQL_SEAT_BY_FUNCTION_AVailable = "SELECT distinct s from Seat s join s.groupSeat gs join gs.lstPrice pr join pr.function fc on (s.auditorium=fc.auditorium) where fc.id=:functionId and not s.id in (SELECT rss.seat.id FROM ReservationTicket_Seat rss WHERE rss.function.id=fc.id)";

    final String SQL_SEAT_BY_SEATS_AND_FUNCTION_AVailable = "SELECT distinct s from Seat s join s.groupSeat gs join gs.lstPrice pr join pr.function fc on (s.auditorium=fc.auditorium) where fc.id=:functionId and s.id in (:seatIds) and not s.id in (SELECT rss.seat.id FROM ReservationTicket_Seat rss WHERE rss.function.id=fc.id)";

    @Query(value= SQL_SEAT_BY_FUNCTION_AVailable)
    List<Seat> findByFunctionAvailable(@Param("functionId") Long functionId);

    @Query(value= SQL_SEAT_BY_SEATS_AND_FUNCTION_AVailable)
    List<Seat> findBySeatIdsAndFunctionAvailable(@Param("seatIds") Long[] seatIds, @Param("functionId") Long functionId);
    //List<Seat> findBySeatIdsAndFunctionAvailable(@Param("functionId") Long functionId);

}
