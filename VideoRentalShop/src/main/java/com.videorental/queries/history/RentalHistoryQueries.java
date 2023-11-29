package com.videorental.queries.history;

import com.videorental.dtos.history.RentalHistoryDto;
import com.videorental.entities.history.rentalhistory.RentalHistory;

import java.util.List;
import java.util.Optional;

public interface RentalHistoryQueries {

    RentalHistory getById(Long id);

    List<RentalHistory> getAll();

    Optional<RentalHistory> findById(Long id);

    List<RentalHistoryDto> getAllRentalHistoryDto();
}
