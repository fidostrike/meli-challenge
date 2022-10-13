package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumDao extends JpaRepository<Auditorium,Long>{

}
