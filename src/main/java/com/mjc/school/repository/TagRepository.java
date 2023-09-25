package com.mjc.school.repository;

import com.mjc.school.model.TagModel;

import java.util.List;

public interface TagRepository extends BaseRepository<TagModel, Long> {
    List<TagModel> getTagsByNewsId(Long newsId);
}
