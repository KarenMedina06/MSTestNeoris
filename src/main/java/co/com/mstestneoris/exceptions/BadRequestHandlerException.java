package co.com.mstestneoris.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import brave.Tracer;
import co.com.mstestneoris.dto.ResponseDTO;


@ControllerAdvice
public class BadRequestHandlerException {

	@Autowired
	private Tracer tracer;
	
	@ExceptionHandler
	public ResponseEntity<ResponseDTO<?>> exceptionHandler(BadRequestException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDTO<>(HttpStatus.BAD_REQUEST, ex.getMessage(), null, tracer.currentSpan().context().traceIdString())
        );
	}
}
