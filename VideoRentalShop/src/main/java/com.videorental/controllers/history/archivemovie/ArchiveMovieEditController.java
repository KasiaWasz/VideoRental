package com.videorental.controllers.history.archivemovie;

import com.videorental.services.movie.MovieDeletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/archive-movie-edit")
@Controller
class ArchiveMovieEditController {

    private static final String P_ARCHIVE_MOVIE_ID = "id";
    private static final String ARCHIVE_MOVIE_LIST_URL ="/archive-movie-list";

    private final MovieDeletionService movieDeletionService;


    @Autowired
    private ArchiveMovieEditController(MovieDeletionService movieDeletionService) {

        Assert.notNull(movieDeletionService, "movieDeletionService must not be null");

        this.movieDeletionService = movieDeletionService;
    }


    @GetMapping("/delete")
    private String deleteArchiveMovie(@RequestParam(P_ARCHIVE_MOVIE_ID) Long id) {

        movieDeletionService.deleteMovieById(id);

        return "redirect:" + ARCHIVE_MOVIE_LIST_URL;
    }
}
