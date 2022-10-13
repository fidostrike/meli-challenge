package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Dao.GroupSeatDao;
import com.entrevista.meli.melishows.Model.Entity.GroupSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupSeatServiceImpl implements IGroupSeatService{
    @Autowired
    GroupSeatDao groupSeatDao;

    @Override
    public List<GroupSeat> findAll() {
        return groupSeatDao.findAll();
    }

    @Override
    public GroupSeat findById(Long id) {
        return groupSeatDao.findById(id).orElse(null);
    }

    @Override
    public GroupSeat save(GroupSeat groupSeat) {
        return groupSeatDao.save(groupSeat);
    }

}
