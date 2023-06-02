package co.com.mstestneoris.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.mstestneoris.dto.CuentasDTO;
import co.com.mstestneoris.entity.Clientes;
import co.com.mstestneoris.entity.Cuentas;

@Component
public interface ICuentasServices {

	public Cuentas save(CuentasDTO dto, Clientes clientes);
	
	public Cuentas update(CuentasDTO dto);
	
	public List<Cuentas> getAllCuentas();
	
	public Cuentas getByNumeroCuenta(Long numeroCuenta);
	
	public void eliminarCuenta(Integer id);
}
