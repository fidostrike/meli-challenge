package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.PriceDao;
import com.entrevista.meli.melishows.Model.Entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements IPriceService{
    @Autowired
    PriceDao priceDao;

    @Override
    public List<Price> findAll() {
        return priceDao.findAll();
    }

    @Override
    public Price findById(Long id) {
        return priceDao.findById(id).orElse(null);
    }

    @Override
    public Price save(Price price) {
        return priceDao.save(price);
    }

}
