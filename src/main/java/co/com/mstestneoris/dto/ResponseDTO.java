package co.com.mstestneoris.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseDTO<T> {

	private HttpStatus responseCode;
	private String message;
	private T data;
	private String transactionId;
	
	public ResponseDTO(HttpStatus responseCode, String message, T data, String transactionId) {
		this.responseCode = responseCode;
		this.message = message;
		this.data = data;
		this.transactionId = transactionId;
	}

	public ResponseDTO() {
		
	}
	
}
