package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterDao extends JpaRepository<Theater,Long>{

}
