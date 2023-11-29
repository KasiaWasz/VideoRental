package com.videorental.repositories.rentals.history;

import com.videorental.entities.history.rentalhistory.RentalHistory;

import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class RentalHistoryRepositoryImpl extends AbstractRepository<RentalHistory> implements RentalHistoryRepository {


    @Autowired
    RentalHistoryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, RentalHistory.class);
    }
}
