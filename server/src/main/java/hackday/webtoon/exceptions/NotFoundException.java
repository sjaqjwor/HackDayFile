package hackday.webtoon.exceptions;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}