package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionDao extends JpaRepository<Function,Long>{
    @Query(value="SELECT f from Function f join f.show as s WHERE s.id=:showId")
    List<Function> findByShow(@Param("showId") Long showId);

    @Query(value="SELECT f from Function f join f.auditorium ad join ad.seatList st join st.groupSeat gs join gs.lstPrice pr WHERE f.id=:functionId")
    Function findByIdWithSeatsAvailable(@Param("functionId") Long functionId);
}
