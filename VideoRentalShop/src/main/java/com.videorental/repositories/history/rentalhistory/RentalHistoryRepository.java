package com.videorental.repositories.history.rentalhistory;

import com.videorental.entities.history.rentalhistory.RentalHistory;

public interface RentalHistoryRepository {

    void saveOrUpdate(RentalHistory rentalHistory);

    void deleteById(Long id);
}
