package com.mjc.school.service.validate;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationNews implements BaseValidation<NewsModel> {

    BaseRepository<AuthorModel, Long> repository;

    public void validate(NewsModel news) throws ValidationException {
        String title = news.getTitle();
        String content = news.getContent();
        Long id = news.getAuthorId();

        if (title.isEmpty() || content.isEmpty()) throw new ValidationException(ErrorCode.EMPTY_FIELD.getErrorData());
        if (title.length() < 5 || title.length() > 30) throw new ValidationException(ErrorCode.FIELD_TITLE_INVALID_LENGTH.getErrorData());
        if (content.length() < 5 || content.length() > 255) throw new ValidationException(ErrorCode.FIELD_CONTENT_INVALID_LENGTH.getErrorData());
        if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorData());
    }
}
