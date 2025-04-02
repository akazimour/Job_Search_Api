package com.akazimour.REVIEW_MS.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ModelExceptionHandler {
    @ExceptionHandler(value = {NoSuchModelException.class})
    public ResponseEntity<Object> handleNoSuchModelException(NoSuchModelException noSuchJobException){
        ModelErrorResponse nosuchreview = new ModelErrorResponse(noSuchJobException.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(nosuchreview, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {IdCanNotBeNullException.class})
    public ResponseEntity<Object> handleIdCanNotBeNullException(IdCanNotBeNullException idCanNotBeNullException){
       ModelErrorResponse idIsNull = new ModelErrorResponse(idCanNotBeNullException.getMessage(),HttpStatus.BAD_REQUEST);
       return new ResponseEntity<>(idIsNull,HttpStatus.BAD_REQUEST);
    }

}
