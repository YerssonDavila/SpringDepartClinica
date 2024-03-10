package edu.unc.departamentos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import edu.unc.departamentos.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(EntityNotFoundException.class)
	    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
	        ApiResponse<Object> message = new ApiResponse<>(false,ex.getMessage(), null);

	        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	    }
	 
	 @ExceptionHandler(IllegalOperationException.class)
	    public ResponseEntity<ApiResponse<Object>> illegalOperationException(IllegalOperationException ex, WebRequest request) {
	    	ApiResponse<Object> message = new ApiResponse<>(false,ex.getMessage(), null);
	        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	    }
	 
	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request){
	        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),request.getDescription(false));
	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
}
