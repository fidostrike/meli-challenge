package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Function;
import com.entrevista.meli.melishows.Model.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDao extends JpaRepository<Show,Long>{
    final String SQL_SHOW_ALL_AVailable = "SELECT distinct s from Show s join s.lstFunction fc join fc.auditorium ad join ad.seatList st where not st.id in (SELECT rss.seat FROM ReservationTicket_Seat rss WHERE rss.function=fc.id)";
    final String SQL_SHOW_BY_ID_AVailable = "SELECT distinct s from Show s join s.lstFunction fc join fc.auditorium ad join ad.seatList st where s.id=:showId and not st.id in (SELECT rss.seat FROM ReservationTicket_Seat rss WHERE rss.function=fc.id)";

    @Query(SQL_SHOW_ALL_AVailable)
    List<Show> findByAvailable();

    @Query(value=SQL_SHOW_BY_ID_AVailable)
    Show findByShowAvailable(@Param("showId") Long showId);



}
