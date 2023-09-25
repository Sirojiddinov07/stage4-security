package com.mjc.school.repository.impl;

import com.mjc.school.model.NewsModel;
import com.mjc.school.model.TagModel;
import com.mjc.school.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryImpl extends AbstractRepository<TagModel, Long> implements TagRepository {

    @Override
    protected void setFields(TagModel oldModel, TagModel newModel) {
        oldModel.setName(newModel.getName());
    }

    @Override
    public List<TagModel> getTagsByNewsId(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        return newsModel.getTags().stream().toList();
    }
}
