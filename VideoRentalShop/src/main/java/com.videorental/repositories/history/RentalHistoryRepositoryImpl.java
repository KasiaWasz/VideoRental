package com.videorental.repositories.history;

import com.videorental.entities.rentals.history.RentalHistory;

import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class RentalHistoryRepositoryImpl extends AbstractRepository<RentalHistory> implements RentalHistoryRepository {


    @Autowired
    public RentalHistoryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, RentalHistory.class);
    }
}
