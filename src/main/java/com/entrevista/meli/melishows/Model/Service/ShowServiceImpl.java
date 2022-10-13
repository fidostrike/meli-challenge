package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Constants.DateUtils;
import com.entrevista.meli.melishows.Model.Dao.ShowDao;
import com.entrevista.meli.melishows.Model.Entity.Function;
import com.entrevista.meli.melishows.Model.Entity.Show;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Service
public class ShowServiceImpl implements IShowService{
    @Autowired
    ShowDao showDao;

    @Autowired
    EntityManager em;

    /**
     * Busca todos los shows
     * @return
     */
    @Override
    public List<Show> findAll() {
        return showDao.findAll();
    }

    /**
     * Busca todos los shows con butacas libres
     * @return
     */
    @Override
    public List<Show> findByAvailable() {
        return showDao.findByAvailable();
    }

    /**
     * Ejecuta y devuelve el listado de shows filtrado por multiples parametros
     * @param showId
     * @param dateFrom
     * @param dateTo
     * @param priceFrom
     * @param priceTo
     * @return
     */
    @Cacheable("ShowsCache")
    @Override
    public List<Show> findByMultiple(Long showId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo) {
        Session session = em.unwrap(Session.class);

        String sql = getSqlByMultiple(showId, dateFrom, dateTo, priceFrom, priceTo);

        TypedQuery<Show> q = em.createQuery(sql, Show.class);

        return q.getResultList();
    }

    /**
     * Crea el string del script para la busqueda multiple
     * @param showId
     * @param dateFrom
     * @param dateTo
     * @param priceFrom
     * @param priceTo
     * @return
     */
    private static String getSqlByMultiple(Long showId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo) {
        String sql = "SELECT distinct s from Show s join s.lstFunction fc join fc.auditorium ad join ad.seatList st ";
        String sqlExtraWhere = "";

        if(showId !=null)
        {
            sqlExtraWhere += " AND s.id=" + showId.toString();
        }

        if(dateFrom !=null)
        {
            sqlExtraWhere+=" AND fc.dateFrom>='" + DateUtils.SDF_DB_SHORT.format(dateFrom) + "'";
        }

        if(dateTo !=null)
        {
            sqlExtraWhere+=" AND fc.dateFrom<='" + DateUtils.SDF_DB_SHORT.format(dateTo) + "'";
        }

        if(priceTo !=null || priceFrom !=null)
        {
            //add subtables
            sql+= " join st.groupSeat gs2 join gs2.lstPrice lp2 ";
        }

        sql+= " where ";

        //busco los habilitados
        sql += " not st.id in (SELECT rss.seat FROM ReservationTicket_Seat rss WHERE rss.function=fc.id)";

        if(priceTo !=null || priceFrom !=null) {
            sql += " and lp2.function=fc.id";
        }

        if(priceFrom !=null)
        {
            sql+=" and lp2.price>=" + priceFrom.toString() + "";
        }

        if(priceTo !=null)
        {
            sql+=" and lp2.price<=" + priceTo.toString() + "";
        }

        sql+= sqlExtraWhere;

        return sql;
    }

    /**
     * Busca un show con butacs libres filtrado por id
     * @param showId
     * @return
     */
    public Show findByShowAvailable(Long showId) {
        return showDao.findByShowAvailable(showId);
    }

    /**
     * Busca un show filtrado por id
     * @param id
     * @return
     */
    @Override
    public Show findById(Long id) {
        return showDao.findById(id).orElse(null);
    }

    /**
     * Persiste los datos en la tabla show
     * @param show
     * @return
     */
    @Override
    public Show save(Show show) {
        return showDao.save(show);
    }

}
