package com.mjc.school.services.impl;

import com.mjc.school.model.NewsModel;
import com.mjc.school.repository.*;
import com.mjc.school.services.NewsService;
import com.mjc.school.services.dto.NewsRequestDTO;
import com.mjc.school.services.dto.NewsResponseDTO;
import com.mjc.school.services.exception.ErrorCodes;
import com.mjc.school.services.exception.NotFoundException;
import com.mjc.school.services.mapper.NewsModelMapper;
import com.mjc.school.services.validator.Validator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;
    private final NewsModelMapper newsMapper = Mappers.getMapper(NewsModelMapper.class);
    private final Validator validator;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, TagRepository tagRepository, Validator validator){
        this.newsRepository = newsRepository;
        this.tagRepository = tagRepository;
        this.validator = validator;
    }

    @Override
    public List<NewsResponseDTO> readAll(int page, int limit, String sortBy) {
        return newsMapper.modelListToDtoList(newsRepository.readAll( page, limit, sortBy));
    }

    @Override
    public NewsResponseDTO readById(Long id) {
        checkNewsExist(id);
        NewsModel model = newsRepository.readById(id).get();
        return newsMapper.modelToDTO(model);
    }

    @Override
    public NewsResponseDTO create(NewsRequestDTO createRequest) {
        validator.checkNewsDto(createRequest);
        Set<Long> tagIds = createRequest.tagIds();
        NewsModel model = newsMapper.dtoToModel(createRequest);
        for (Long id : tagIds){
            if (tagRepository.existById(id)){
                model.addTag(tagRepository.readById(id).get());
            }
        }
        NewsModel newModel = newsRepository.create(model);
        return newsMapper.modelToDTO(newModel);
    }

    @Override
    public NewsResponseDTO update(NewsRequestDTO updateRequest) {
        validator.checkNewsDto(updateRequest);
        Set<Long> tagIds = updateRequest.tagIds();
        NewsModel model = newsMapper.dtoToModel(updateRequest);
        checkNewsExist(model.getId());
        for (Long id : tagIds){
            if (tagRepository.existById(id)){
                model.addTag(tagRepository.readById(id).get());
            }
        }
        NewsModel updatedModel = newsRepository.update(model);

        return newsMapper.modelToDTO(updatedModel);
    }

    @Override
    public boolean deleteById(Long id) {
        checkNewsExist(id);
        return newsRepository.deleteById(id);
    }

    public List<NewsResponseDTO> readByParams(Long tagId, String tagName, String authorName, String title, String content){
        return newsMapper.modelListToDtoList(newsRepository.readByParams(tagId, tagName, authorName, title, content));
    }


    private void checkNewsExist(Long id){
        if (!newsRepository.existById(id)){
            throw new NotFoundException(String.format(ErrorCodes.NEWS_NOT_EXIST.getMessage(),id));
        }
    }
}
