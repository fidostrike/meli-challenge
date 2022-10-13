package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Theater;

import java.util.List;

public interface ITheaterService {
    public List<Theater> findAll();

    public Theater findById(Long id);

    public Theater save(Theater theater);
}