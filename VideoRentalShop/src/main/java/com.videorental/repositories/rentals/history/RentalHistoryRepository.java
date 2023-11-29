package com.videorental.repositories.rentals.history;

import com.videorental.entities.history.rentalhistory.RentalHistory;

public interface RentalHistoryRepository {

    void saveOrUpdate(RentalHistory rentalHistory);

    void deleteById(Long id);
}
