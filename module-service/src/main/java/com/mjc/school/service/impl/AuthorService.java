package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalLong;

@Service
@AllArgsConstructor
public class AuthorService implements BaseService<AuthorDTO, Long> {
    private BaseValidation<AuthorDTO> validation;
    private BaseRepository<AuthorModel, Long> repository;

    @Override
    public List<AuthorDTO> readAll() {
        return AuthorMapper.INSTANCE.authorListToAuthorDtoList(repository.readAll());
    }

    @Override
    public AuthorDTO readById(Long id) throws ValidationException {
        return AuthorMapper.INSTANCE.authorToAuthorDto(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData())));
    }

    @Override
    public AuthorDTO create(AuthorDTO createRequest) throws ValidationException {
        validation.validate(createRequest);

        OptionalLong maxId = repository.readAll()
                .stream()
                .mapToLong(AuthorModel::getId)
                .max();

        long nextId = maxId.orElse(0) + 1;
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(nextId);

        authorModel.setCreateDate(LocalDateTime.now());
        authorModel.setLastUpdateDate(LocalDateTime.now());

        authorModel.setName(createRequest.getName());

        return AuthorMapper.INSTANCE.authorToAuthorDto(repository.create(authorModel));
    }

    @Override
    public AuthorDTO update(AuthorDTO updateRequest, Long id) throws ValidationException {
        validation.validate(updateRequest);

        AuthorModel authorModel = new AuthorModel();
        authorModel.setLastUpdateDate(LocalDateTime.now());
        authorModel.setName(updateRequest.getName());

        if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData());
        return AuthorMapper.INSTANCE.authorToAuthorDto(repository.update(authorModel, id));
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData());
        return repository.deleteById(id);
    }
}
