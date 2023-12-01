package com.videorental.services.movie;

import com.videorental.services.history.archivemovie.ArchiveMovieService;
import com.videorental.services.history.rentalhistory.RentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MovieDeletionService {

    private final MovieService movieService;
    private final ArchiveMovieService archiveMovieService;
    private final RentalHistoryService rentalHistoryService;


    @Autowired
    private MovieDeletionService(
        MovieService movieService,
        ArchiveMovieService archiveMovieService,
        RentalHistoryService rentalHistoryService) {

        Assert.notNull(movieService, "movieService must not be null");
        Assert.notNull(archiveMovieService, "archiveMovieService must not be null");
        Assert.notNull(rentalHistoryService, "rentalHistoryService must not be null");

        this.movieService = movieService;
        this.archiveMovieService = archiveMovieService;
        this.rentalHistoryService = rentalHistoryService;
    }


    public void deleteMovieById(Long id) {
        rentalHistoryService.deleteByMovieId(id);
        archiveMovieService.deleteByMovieId(id);
        movieService.deleteById(id);
    }
}
