package com.videorental.repositories.history.archivemovie;

import com.videorental.entities.history.archivemovie.ArchiveMovie;

public interface ArchiveMovieRepository {

    void saveOrUpdate(ArchiveMovie archiveMovie);

    void deleteById(Long id);
}
