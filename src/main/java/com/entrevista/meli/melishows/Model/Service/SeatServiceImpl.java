package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.SeatDao;
import com.entrevista.meli.melishows.Model.Entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements ISeatService{
    @Autowired
    SeatDao seatDao;

    @Override
    public List<Seat> findAll() {
        return seatDao.findAll();
    }

    @Override
    public Seat findById(Long id) {
        return seatDao.findById(id).orElse(null);
    }

    /**
     * Busca las butacas libre filtrado por funcion
     * @param functionId
     * @return
     */
    @Override
    public List<Seat> findByFunction(Long functionId) {
        List<Seat> result = seatDao.findByFunctionAvailable(functionId);

        filterListByFunction(functionId, result);

        return result;
    }

    /**
     * Filtra las butacas libres filtrado por un array de butacas y la funcion
     * @param seatIds
     * @param functionId
     * @return
     */
    @Override
    public List<Seat> findBySeatIdsFunction(Long[] seatIds, Long functionId) {
        List<Seat> result = seatDao.findBySeatIdsAndFunctionAvailable(seatIds, functionId);

        //if not the same number, a seat was take
        if(result.size()==seatIds.length) {
            //filter price
            filterListByFunction(functionId, result);
        }
        else
        {
            result = null;
        }

        return result;
    }

    /**
     * Filtra los precios del array para dejar solo los relacionados a la funcion
     * @param functionId
     * @param result
     */
    private static void filterListByFunction(Long functionId, List<Seat> result) {
        result.forEach(seat -> seat.getGroupSeat().setLstPrice(seat.getGroupSeat().getLstPrice().stream().filter(price -> price.getFunction().getId() == functionId).collect(Collectors.toSet())));
    }

    /**
     * Persiste los datos de la butaca
     * @param seat
     * @return
     */
    @Override
    public Seat save(Seat seat) {
        return seatDao.save(seat);
    }

}
