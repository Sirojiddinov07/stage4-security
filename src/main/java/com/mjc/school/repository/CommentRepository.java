package com.mjc.school.repository;

import com.mjc.school.model.CommentModel;

import java.util.List;

public interface CommentRepository extends BaseRepository<CommentModel, Long> {
    List<CommentModel> getCommentsByNewsId (Long newsId);
}
