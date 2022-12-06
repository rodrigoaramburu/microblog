package br.dev.botecodigital.microblog.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.dev.botecodigital.microblog.users.exceptions.ConflictUserException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConflictUserException.class)
	public ResponseEntity<Object> handleConflictUserException(ConflictUserException ex, WebRequest request){
		
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("message", "Conflict Error");
		error.put("errors", ex.getConflicts());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> details = new HashMap<String, String>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			FieldError fieldError = (FieldError) error;
			details.put(fieldError.getField(), error.getDefaultMessage());
		}
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("message", "Validation Error");
		error.put("code", status.toString());
		error.put("errors", details);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
