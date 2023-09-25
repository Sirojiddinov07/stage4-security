package com.mjc.school.repository;

import com.mjc.school.model.AuthorModel;

public interface AuthorRepository extends BaseRepository<AuthorModel, Long> {
    AuthorModel getAuthorByNewsId(Long id);
}

