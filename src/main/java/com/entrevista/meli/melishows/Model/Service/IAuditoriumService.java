package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Auditorium;

import java.util.List;

public interface IAuditoriumService {
    public List<Auditorium> findAll();

    public Auditorium findById(Long id);

    public Auditorium save(Auditorium auditorium);
}