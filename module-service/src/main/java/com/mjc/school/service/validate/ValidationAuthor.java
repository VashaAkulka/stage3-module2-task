package com.mjc.school.service.validate;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationAuthor implements BaseValidation<AuthorDTO> {
    @Override
    public void validate(AuthorDTO author) throws ValidationException {
        if (author.getName().isEmpty()) throw new ValidationException(ErrorCode.EMPTY_FIELD.getErrorData());
        if (author.getName().length() < 3 || author.getName().length() > 15) throw new ValidationException(ErrorCode.FIELD_NAME_INVALID_LENGTH.getErrorData());
    }
}
