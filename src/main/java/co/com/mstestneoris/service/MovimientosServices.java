package co.com.mstestneoris.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.mstestneoris.dto.ConsultaDTO;
import co.com.mstestneoris.dto.MovimientosDTO;
import co.com.mstestneoris.entity.Cuentas;
import co.com.mstestneoris.entity.Movimientos;
import co.com.mstestneoris.repository.IMovimientosRepository;

@Service
@Transactional
public class MovimientosServices implements IMovimientosServices {
	
	@Autowired
	private IMovimientosRepository repository;

	@Override
	public Movimientos save(MovimientosDTO dto, Cuentas cuenta) {
		return repository.save(new Movimientos(dto, cuenta));
	}

	@Override
	public Movimientos update(MovimientosDTO dto, Cuentas cuenta) {
		Movimientos movimiento = new Movimientos(dto, cuenta);
		return repository.save(movimiento);
	}
	
	@Transactional
	@Override
	public Movimientos getById(Integer id) {
		Optional<Movimientos> movimiento = repository.findById(id);
		return movimiento.get();
	}

	@Override
	public List<Movimientos> getAllMovimientos() {
		return repository.findAll();
	}

	@Override
	public List<Movimientos> getByNumeroCuenta(Long idCuenta) {
		return repository.findAllByNumeroCuenta(idCuenta);
	}
	
	@Override
	@Transactional
	public ConsultaDTO getByFechaAndCliente(LocalDate fechaMovimiento, String identificacion) {
		ConsultaDTO dto = null;
		String consulta = repository.findByFechaAndCliente(fechaMovimiento, identificacion);
		if (consulta != null) {
			String[] list = consulta.split(",");
			dto = new ConsultaDTO(LocalDate.parse(list[0]), list[1], Long.parseLong(list[2]) , list[3], Long.parseLong(list[4]), list[5], Long.parseLong(list[6]), Long.parseLong(list[7]));
		}
		return dto;
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);	
	}
	
	@Override
	public Long calcularSaldo(Long saldoInicial, Long valorMovimiento, String tipoMovimiento) {
		Long saldo = (long) 0;
		if (tipoMovimiento.equals("Retiro")) {
			saldo = saldoInicial - valorMovimiento;
		}
		if (tipoMovimiento.equals("Deposito")) {
			saldo = saldoInicial + valorMovimiento;
		}
		return saldo;
	}

}
