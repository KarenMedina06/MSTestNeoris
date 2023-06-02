package co.com.mstestneoris.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.mstestneoris.dto.ConsultaDTO;
import co.com.mstestneoris.dto.MovimientosDTO;
import co.com.mstestneoris.entity.Cuentas;
import co.com.mstestneoris.entity.Movimientos;

@Component
public interface IMovimientosServices {

	public Movimientos save(MovimientosDTO dto, Cuentas cuenta);
	
	public Movimientos update(MovimientosDTO dto, Cuentas cuenta);
	
	public Movimientos getById(Integer id);

	public List<Movimientos> getAllMovimientos();
	
	public List<Movimientos> getByNumeroCuenta(Long idCuenta);
	
	public ConsultaDTO getByFechaAndCliente(LocalDate fechaMovimiento, String identificacion);
	
	public void delete(Integer id);
	
	public Long calcularSaldo(Long saldoInicial, Long valorMovimiento, String tipoMovimiento);
}
