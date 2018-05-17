package hackday.webtoon.exceptions;

import hackday.webtoon.models.dtos.response.StatusCode;

public class FileException extends RuntimeException{
    public FileException(StatusCode statusCode) {
        super(statusCode.toString());
    }
}
