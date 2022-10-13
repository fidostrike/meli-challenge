package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Function;

import java.util.List;

public interface IFunctionService {
    public List<Function> findAll();

    public Function findById(Long id);

    public List<Function> findByShow(Long showId);

    public Function findByIdWithSeats(Long functionId);

    public Function save(Function function);
}