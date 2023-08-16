package com.videorental.repositories.shift;

import com.videorental.entities.shift.Shift;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class ShiftRepositoryImpl extends AbstractRepository<Shift> implements ShiftRepository {


    @Autowired
    ShiftRepositoryImpl(SessionFactory sessionFactory) {

        super(sessionFactory, Shift.class);
    }
}
