package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Price;

import java.util.List;

public interface IPriceService {
    public List<Price> findAll();

    public Price findById(Long id);

    public Price save(Price price);
}