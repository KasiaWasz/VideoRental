package com.videorental.jobs;

import com.videorental.services.movie.MovieService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MovieJob implements Job {

    private MovieService movieService;

    @Autowired
    public MovieJob(MovieService movieService) {

        Assert.notNull(movieService, "movieService must not be null");

        this.movieService = movieService;
    }

    public MovieJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        movieService.setMoviePrice();
    }
}
