package com.videorental.services.movie;

import com.videorental.controllers.movie.MovieForm;
import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.movie.MovieQueries;
import com.videorental.repositories.movie.MovieRepository;
import com.videorental.services.history.archivemovie.ArchiveMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MovieService {

    private final MovieQueries movieQueries;
    private final MovieRepository movieRepository;
    private final ArchiveMovieService archiveMovieService;


    @Autowired
    private MovieService(MovieQueries movieQueries,
        MovieRepository movieRepository,
        ArchiveMovieService archiveMovieService) {

        Assert.notNull(movieQueries, "movieQueries must not be null");
        Assert.notNull(movieRepository, "movieRepository must not be null");
        Assert.notNull(archiveMovieService, "archiveMovieService must not be null");

        this.movieQueries = movieQueries;
        this.movieRepository = movieRepository;
        this.archiveMovieService = archiveMovieService;
    }


    public List<MovieDto> getAllMoviesDto() {

        return movieQueries.getAllMoviesDto();
    }

    public List<MovieDto> getAllActiveMoviesDto() {

        return movieQueries.getAllActiveMoviesDto();
    }

    public Movie getById(Long id) {

        Assert.notNull(id, "id must not be null");

        return movieQueries.getById(id);
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        movieRepository.deleteById(id);
    }

    public void saveOrUpdateMovie(MovieForm movieForm) {

        Assert.notNull(movieForm, "movieForm must not be null");

        Movie movie = movieQueries.findById(movieForm.getId())
                .orElseGet(Movie::new);

        movie.setName(movieForm.getName());
        movie.setPrice(movieForm.getPrice());
        movie.setLastUpdateDate(LocalDate.now());
        movie.setMovieActive(true);

        movieRepository.saveOrUpdate(movie);
    }

    public void setMoviePrice() {

        Long movieId = movieQueries.getRandomMovieId();
        Movie movie = movieQueries.getById(movieId);
        Movie movieOnSale = movieQueries.getMovieOnSale();
        BigDecimal initialPrice = BigDecimal.valueOf(10.99);

        LocalDate currentTime = LocalDate.now();
        long weeksSinceLastPromotion = ChronoUnit.WEEKS.between(movieOnSale.getLastUpdateDate(), currentTime);

        if (weeksSinceLastPromotion >= 1) {

            movieOnSale.setPrice(initialPrice);
            movieOnSale.setOnSale(false);
            movieOnSale.setLastUpdateDate(currentTime);
            movieRepository.saveOrUpdate(movieOnSale);
        }
        movie.setPrice(BigDecimal.valueOf(5.99));
        movie.setOnSale(true);
        movie.setLastUpdateDate(currentTime);
        movieRepository.saveOrUpdate(movie);
    }

    public void setMovieActive(Long id) {

        Assert.notNull(id, "id must not be null");

        Movie movie = getById(id);

        movie.setMovieActive(false);

        movieRepository.saveOrUpdate(movie);

        archiveMovieService.saveArchiveMovie(movie);
    }
}
