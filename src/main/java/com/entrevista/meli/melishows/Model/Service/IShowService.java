package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.Show;

import java.util.Date;
import java.util.List;

public interface IShowService {
    public List<Show> findAll();

    public List<Show> findByAvailable();

    public List<Show> findByMultiple(Long showId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo);

    public Show findByShowAvailable(Long showId);

    public Show findById(Long id);

    public Show save(Show show);
}