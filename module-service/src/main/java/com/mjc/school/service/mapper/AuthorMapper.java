package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper( AuthorMapper.class );
    AuthorDTO authorToAuthorDto(AuthorModel author);
    List<AuthorDTO> authorListToAuthorDtoList(List<AuthorModel> authorList);

}
