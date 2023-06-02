package co.com.mstestneoris.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultaDTO {

	private LocalDate fecha;
	private String cliente;
	private Long numeroCuenta;
	private String tipoCuenta;
	private Long saldoInicial;
	private String tipoMovimiento;
	private Long valor;
	private Long saldo;
	
	public ConsultaDTO(LocalDate fecha, String cliente, Long numeroCuenta, String tipoCuenta, Long saldoInicial,
			String tipoMovimiento, Long valor, Long saldo) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}
	
	
}
