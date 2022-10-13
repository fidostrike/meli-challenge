package com.entrevista.meli.melishows.Model.Dao;

import com.entrevista.meli.melishows.Model.Entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDao extends JpaRepository<Price,Long>{

}
