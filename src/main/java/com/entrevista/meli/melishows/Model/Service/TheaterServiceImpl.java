package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.TheaterDao;
import com.entrevista.meli.melishows.Model.Entity.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements ITheaterService{
    @Autowired
    TheaterDao theaterDao;

    @Override
    public List<Theater> findAll() {
        return theaterDao.findAll();
    }

    @Override
    public Theater findById(Long id) {
        return theaterDao.findById(id).orElse(null);
    }

    @Override
    public Theater save(Theater theater) {
        return theaterDao.save(theater);
    }

}
