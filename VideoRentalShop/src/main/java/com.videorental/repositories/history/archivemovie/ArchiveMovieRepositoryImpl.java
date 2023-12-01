package com.videorental.repositories.history.archivemovie;

import com.videorental.entities.history.archivemovie.ArchiveMovie;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class ArchiveMovieRepositoryImpl extends AbstractRepository<ArchiveMovie> implements ArchiveMovieRepository {


    @Autowired
    ArchiveMovieRepositoryImpl(SessionFactory sessionFactory) {

        super(sessionFactory, ArchiveMovie.class);
    }
}