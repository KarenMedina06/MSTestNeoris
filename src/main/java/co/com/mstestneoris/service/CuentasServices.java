package co.com.mstestneoris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.mstestneoris.dto.CuentasDTO;
import co.com.mstestneoris.entity.Clientes;
import co.com.mstestneoris.entity.Cuentas;
import co.com.mstestneoris.repository.ICuentasRepository;

@Service
@Transactional
public class CuentasServices implements ICuentasServices {
	
	@Autowired
	private ICuentasRepository repository;

	@Override
	public Cuentas save(CuentasDTO dto, Clientes clientes) {
		dto.setEstado(true);
		return repository.save(new Cuentas(dto, clientes));
	}

	@Override
	public Cuentas update(CuentasDTO dto) {
		Cuentas cuentas = new Cuentas(dto);
		return repository.save(cuentas);
	}

	@Override
	public List<Cuentas> getAllCuentas() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Cuentas getByNumeroCuenta(Long numeroCuenta) {
		return repository.findByNumeroCuenta(numeroCuenta);
	}

	@Override
	public void eliminarCuenta(Integer id) {
		repository.deleteById(id);
	}

}
