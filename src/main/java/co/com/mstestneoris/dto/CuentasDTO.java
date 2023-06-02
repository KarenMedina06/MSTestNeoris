package co.com.mstestneoris.dto;

import lombok.Data;

@Data
public class CuentasDTO {

	private Integer id;
	private Long numeroCuenta;
	private String tipoCuenta;
	private Long saldoInicial;
	private String cliente;
	private boolean estado;
	
}
