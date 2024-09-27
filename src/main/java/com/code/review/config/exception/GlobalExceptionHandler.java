package com.code.review.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.NoSuchFileException;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

    /**
     * GlobalExceptionHandler 에 정의되지 않은 exception 발생할때 예외처리
     * 알 수 없는 오류가 발생했습니다 : {errorMessage}
     * {발생한 Exception 의 .getClass() 값} // Exception 공부 및 핸들러 기입하기 위해서
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = "알 수 없는 오류가 발생했습니다 : " + ex.getMessage() + "\n" + ex.getClass();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }

    /**
     * 값을 찾을 수 없을때
     * 500 INTERNAL_SERVER_ERROR : {errorMessage}
     * NullPointerException 을 처리하는 핸들러가 404 Not Found 상태 코드를 반환하는 것은 일반적인 관례에 맞지 않습니다.
     * NullPointerException 은 서버 내부에서 발생한 문제를 나타내기 때문에, 일반적으로는 500 Internal Server Error 를 반환하는 것이 맞습니다.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException(NullPointerException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpStatus.INTERNAL_SERVER_ERROR + " : " + ex.getMessage());
    }

    /**
     * HttpMethod 잘못 입력시
     * 405 METHOD_NOT_ALLOWED : {errorMessage}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(HttpStatus.METHOD_NOT_ALLOWED + " : "
                        + "Request method '" + ex.getMethod() + "' 는(은) 지원하지 않습니다.\nRequest method를 다시 확인해주세요.");
    }

    /**
     * API 잘못 입력시
     * 404 NOT_FOUND : {errorMessage}
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> noResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(HttpStatus.NOT_FOUND + " : " + ex.getMessage());
    }

    /**
     * HttpMethod 또는 API 잘못 입력시
     * 위의 두 ExceptionHandler 가 따로따로 잡아주기에 사용하지 않는다.
     * 익셉션은 부모Exception 보다 자식Exception 사용하는게 좋은 방법이기 때문이다.
     *
     * 부모 자식 관계는 아닌것 같지만 따로따로 사용할 수 있고,
     * handleGenericException() 으로 잡아낸 Exception 이여서 위의 두개 사용
     *
     * 부모Exception 을 사용할것이면 그냥 Exception.class 를 사용하면 된다!!!
     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<String> noHandlerFoundException(NoHandlerFoundException ex) {
//        ex.getRequestURL();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                HttpStatus.NOT_FOUND + " :  요청하신 API를 찾을 수 없습니다. 입력 값을 다시 확인 해주세요.\n"
//                        + "현재 입력 값 : " + ex.getHttpMethod() + " " + ex.getRequestURL());
//    }

    /**
     * RequestParam 필요값을 입력안할시
     * 400 BAD_REQUEST : {errorMessage}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpStatus.BAD_REQUEST + " : " + ex.getMessage());
    }
}
