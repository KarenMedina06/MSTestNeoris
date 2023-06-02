package co.com.mstestneoris.dto;

import lombok.Data;

@Data
public class MovimientosDTO {

	private Integer id;
	private String fecha;
	private String tipoMovimiento;
	private Long valor;
	private Long saldo;
	private Long cuenta;
}
