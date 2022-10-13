package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.GroupSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSeatDao extends JpaRepository<GroupSeat,Long>{

}
