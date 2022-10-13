package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.FunctionDao;
import com.entrevista.meli.melishows.Model.Entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements IFunctionService{
    @Autowired
    FunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    @Override
    public Function findById(Long id) {
        return functionDao.findById(id).orElse(null);
    }

    @Override
    public List<Function> findByShow(Long showId)
    {
        return functionDao.findByShow(showId);
    }

    @Override
    public Function findByIdWithSeats(Long functionId)
    {
        return functionDao.findById(functionId).orElse(null);
    }

    @Override
    public Function save(Function function) {
        return functionDao.save(function);
    }

}
