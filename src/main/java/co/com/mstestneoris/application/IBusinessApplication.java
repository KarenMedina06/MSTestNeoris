package co.com.mstestneoris.application;

import co.com.mstestneoris.dto.ClientesDTO;
import co.com.mstestneoris.dto.CuentasDTO;
import co.com.mstestneoris.dto.MovimientosDTO;
import co.com.mstestneoris.dto.ResponseDTO;

public interface IBusinessApplication {

	public ResponseDTO<?> registrarCliente(ClientesDTO dto);

	public ResponseDTO<?> modificarCliente(ClientesDTO dto);

	public ResponseDTO<?> getAllClientes();
	
	public ResponseDTO<?> getClienteByParam(String param);
	
	public ResponseDTO<?> eliminarCliente(Integer id);

	public ResponseDTO<?> registrarCuentas(CuentasDTO dto);

	public ResponseDTO<?> modificarCuentas(CuentasDTO dto);

	public ResponseDTO<?> getAllCuentas();
	
	public ResponseDTO<?> getCuentaByParam(String param);

	public ResponseDTO<?> eliminarCuentas(Integer id);
	
	public ResponseDTO<?> registrarMovimientos(MovimientosDTO dto);

	public ResponseDTO<?> modificarMovimientos(MovimientosDTO dto);

	public ResponseDTO<?> getAllMovimientos();
	
	public ResponseDTO<?> getMovimientoByParam(String param);

	public ResponseDTO<?> eliminarMovimientos(Integer id);
	
	public ResponseDTO<?> getMovimientosByClienteFecha(String fecha, String identificacion);
}
