package com.entrevista.meli.melishows.Model.Service;

import com.entrevista.meli.melishows.Model.Entity.GroupSeat;

import java.util.List;

public interface IGroupSeatService {
    public List<GroupSeat> findAll();

    public GroupSeat findById(Long id);

    public GroupSeat save(GroupSeat groupSeat);
}