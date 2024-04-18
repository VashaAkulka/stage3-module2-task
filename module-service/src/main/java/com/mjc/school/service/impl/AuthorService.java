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
public class AuthorService implements BaseService<AuthorModel, AuthorDTO, Long> {
    private BaseValidation<AuthorModel> validation;
    private BaseRepository<AuthorModel, Long> repository;

    @Override
    public List<AuthorDTO> readAll() {
        return AuthorMapper.INSTANCE.authorListToAuthorDtoList(repository.readAll());
    }

    @Override
    public AuthorDTO readById(Long id) {
        try {
            return AuthorMapper.INSTANCE.authorToAuthorDto(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData())));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO create(AuthorModel createRequest) {
        try {
            validation.validate(createRequest);

            OptionalLong maxId = repository.readAll()
                    .stream()
                    .mapToLong(AuthorModel::getId)
                    .max();

            long nextId = maxId.orElse(0) + 1;
            createRequest.setId(nextId);

            createRequest.setCreateDate(LocalDateTime.now());
            createRequest.setLastUpdateDate(LocalDateTime.now());

            return AuthorMapper.INSTANCE.authorToAuthorDto(repository.create(createRequest));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO update(AuthorModel updateRequest) {
        try {
            validation.validate(updateRequest);

            updateRequest.setLastUpdateDate(LocalDateTime.now());
            AuthorModel author = repository.update(updateRequest);
            if (author == null) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData());
            return AuthorMapper.INSTANCE.authorToAuthorDto(author);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            if (!repository.deleteById(id)) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData());
            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
