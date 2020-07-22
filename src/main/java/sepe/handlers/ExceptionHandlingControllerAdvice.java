package sepe.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sepe.config.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Annita on 5/16/2015.
 */
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleUnauthorizedException(IllegalStateException exception) throws UnsupportedEncodingException {
        String errorMessage = exception.getMessage();
        //String errorMessage = (exception.getCause().getCause()).getMessage();
        if (null != errorMessage && errorMessage.startsWith(Constants.SEPE_ERROR_PREFIX)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Constants.SEPE_ERROR_PREFIX, URLEncoder.encode(errorMessage, "UTF-8"));
            return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
        }
        else
            throw exception;
    }
}