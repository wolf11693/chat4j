package org.ra.exceprion.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	public class ResponseMessage {
		private String message;
		private Long timestamp;
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public Long getTimestamp() {
			return timestamp;
		}
		
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler()
	public ResponseEntity<ResponseMessage> exceptionHandler(Exception ex){
		LOG.info("exceptionHandler - START");

		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setMessage(ex.getMessage());
		responseMessage.setTimestamp(new Date().getTime());
		
		LOG.info("exceptionHandler - END");
		return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}