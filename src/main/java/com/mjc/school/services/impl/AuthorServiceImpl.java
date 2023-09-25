package com.mjc.school.services.impl;

import com.mjc.school.model.AuthorModel;
import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.services.AuthorService;
import com.mjc.school.services.dto.AuthorRequestDTO;
import com.mjc.school.services.dto.AuthorResponseDTO;
import com.mjc.school.services.exception.ErrorCodes;
import com.mjc.school.services.exception.NotFoundException;
import com.mjc.school.services.validator.Validator;
import com.mjc.school.services.mapper.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;
    private final Validator validator;
    private final AuthorModelMapper mapper = Mappers.getMapper(AuthorModelMapper.class);

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo, Validator validator) {
        this.authorRepo = authorRepo;
        this.validator = validator;
    }

    @Override
    public List<AuthorResponseDTO> readAll(int page, int limit, String sortBy) {
        return mapper.modelListToDtoList(authorRepo.readAll(page, limit, sortBy));
    }

    @Override
    public AuthorResponseDTO readById(Long id) {
        AuthorModel dto = authorRepo.readById(id).orElseThrow(() -> new NotFoundException(String.format(ErrorCodes.AUTHOR_NOT_EXIST.getMessage(), id)));
        return mapper.modelToDTO(dto);
    }

    @Override
    public AuthorResponseDTO create(AuthorRequestDTO createRequest) {
        validator.checkAuthorDto(createRequest);
        AuthorModel model = mapper.dtoToModel(createRequest);
        AuthorModel newModel = authorRepo.create(model);
        return mapper.modelToDTO(newModel);
    }

    @Override
    public AuthorResponseDTO update(AuthorRequestDTO updateRequest) {
        validator.checkAuthorDto(updateRequest);
        AuthorModel model = mapper.dtoToModel(updateRequest);
        readById(model.getId());
        AuthorModel updatedModel = authorRepo.update(model);
        return mapper.modelToDTO(updatedModel);
    }

    @Override
    public boolean deleteById(Long id) {
        if (authorRepo.existById(id)){
            return authorRepo.deleteById(id);
        } else throw new NotFoundException(String.format(ErrorCodes.AUTHOR_NOT_EXIST.getMessage(), id));
    }

    public AuthorResponseDTO getAuthorByNewsId(Long newsId){
        validator.validateNewsExist(newsId);
        AuthorModel author = authorRepo.getAuthorByNewsId(newsId);
        return mapper.modelToDTO(author);
    }

}
