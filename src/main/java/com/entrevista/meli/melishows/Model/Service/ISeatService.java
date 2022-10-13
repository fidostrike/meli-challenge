package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Seat;

import java.util.List;

public interface ISeatService {
    public List<Seat> findAll();

    public Seat findById(Long id);

    public List<Seat> findByFunction(Long functionId);

    public List<Seat> findBySeatIdsFunction(Long[] seatIds, Long functionId);

    public Seat save(Seat seat);
}