package com.videorental.services.history;

import com.videorental.queries.rentals.history.RentalHistoryQueries;
import com.videorental.repositories.rentals.history.RentalHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalHistoryService {

    private final RentalHistoryRepository rentalHistoryRepository;
    private final RentalHistoryQueries rentalHistoryQueries;
}
