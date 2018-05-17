package hackday.webtoon.exceptions;

import hackday.webtoon.models.dtos.response.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ErrorInfo handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        return new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
    }

    @ExceptionHandler(HashException.class)
    public ResponseEntity<?> noSuchAlgorithmException(HashException nsae){
        DefaultResponse dr = DefaultResponse.builder()
                .statusCode(nsae.getMessage())
                .result("FAIL")
                .msg("Hash fail").build();
        return new ResponseEntity<>(dr,HttpStatus.OK);
    }
    @ExceptionHandler(FileException.class)
    public ResponseEntity<?> fileException(FileException ikse){
        DefaultResponse dr = DefaultResponse.builder()
                .statusCode(ikse.getMessage())
                .result("FAIL")
                .msg("File write fail").build();
        return new ResponseEntity<>(dr,HttpStatus.OK);
    }


}
