package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.AuditoriumDao;
import com.entrevista.meli.melishows.Model.Entity.Auditorium;
import com.entrevista.meli.melishows.Model.Entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumServiceImpl implements IAuditoriumService{
    @Autowired
    AuditoriumDao auditoriumDao;

    @Override
    public List<Auditorium> findAll() {
        return auditoriumDao.findAll();
    }

    @Override
    public Auditorium findById(Long id) {
        return auditoriumDao.findById(id).orElse(null);
    }

    @Override
    public Auditorium save(Auditorium auditorium) {
        return auditoriumDao.save(auditorium);
    }

}
