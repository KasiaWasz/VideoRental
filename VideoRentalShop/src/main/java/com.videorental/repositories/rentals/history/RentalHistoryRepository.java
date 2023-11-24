package com.videorental.repositories.rentals.history;

import com.videorental.entities.rentals.history.RentalHistory;

public interface RentalHistoryRepository {

    void saveOrUpdate(RentalHistory rentalHistory);

    void deleteById(Long id);
}
