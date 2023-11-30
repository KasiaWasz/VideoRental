package com.videorental.controllers.history;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.services.history.archivemovie.ArchiveMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/archive-movie-list")
@Controller
class ArchiveMovieListController {

    private static final String M_ARCHIVE_MOVIE_LIST = "archiveMovies";
    private static final String V_ARCHIVE_MOVIE_LIST = "archive-movie-list-view";

    private final ArchiveMovieService archiveMovieService;


    @Autowired
    private ArchiveMovieListController(ArchiveMovieService archiveMovieService) {

        Assert.notNull(archiveMovieService, "archiveMovieService must not be null");

        this.archiveMovieService = archiveMovieService;
    }


    @ModelAttribute(M_ARCHIVE_MOVIE_LIST)
    private List<MovieDto> getAllArchiveMoviesDto() {

        return archiveMovieService.getAllArchiveMoviesDto();
    }

    @GetMapping
    private String showArchiveMovieList() {

        return V_ARCHIVE_MOVIE_LIST;
    }
}
