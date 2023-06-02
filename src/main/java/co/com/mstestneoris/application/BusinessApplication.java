package co.com.mstestneoris.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import brave.Tracer;
import co.com.mstestneoris.dto.ClientesDTO;
import co.com.mstestneoris.dto.ConsultaDTO;
import co.com.mstestneoris.dto.CuentasDTO;
import co.com.mstestneoris.dto.MovimientosDTO;
import co.com.mstestneoris.dto.ResponseDTO;
import co.com.mstestneoris.entity.Clientes;
import co.com.mstestneoris.entity.Cuentas;
import co.com.mstestneoris.entity.Movimientos;
import co.com.mstestneoris.entity.Personas;
import co.com.mstestneoris.exceptions.BadRequestException;
import co.com.mstestneoris.service.IClientesServices;
import co.com.mstestneoris.service.ICuentasServices;
import co.com.mstestneoris.service.IMovimientosServices;
import co.com.mstestneoris.service.IPersonasService;

/**
 * Clase central de fachada que implementa la interfaz IBusinessApplication y 
 * tiene metodos definidos para la gestion de Clientes, Movimientos, Cuentas
 *
 * @author Karen Daniela Medina Tovar
 * @version 1.0
 */

@Service
public class BusinessApplication implements IBusinessApplication {

	@Autowired
	private IClientesServices clientesServices;

	@Autowired
	private IPersonasService personasService;
	
	@Autowired
	private ICuentasServices cuentasServices;
	
	@Autowired
	private IMovimientosServices movimientosServices;

	@Autowired
	private Tracer tracer;

	/**
     * Método que valida si el cliente se encuentra registrado 
     * y se convierte el objeto recibido en una entidad y se envia la entidad a la clase de servicio de clientes
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> registrarCliente(ClientesDTO dto) {
		Personas getPersona = personasService.getByIdentificacion(dto.getIdentificacion());
		if (Objects.nonNull(getPersona)) {
			throw new BadRequestException("El cliente se encuentra registrado");
		}
		try {
			Clientes cliente = clientesServices.save(dto);
			if (Objects.nonNull(cliente)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", cliente, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede registrar al Cliente", tracer.currentSpan().context().traceIdString());
	}
	
	/**
     * Método que valida si el cliente se encuentra registrado y envia el cliente consultado a modificación
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> modificarCliente(ClientesDTO dto) {
		Personas getPersona = personasService.getByIdentificacion(dto.getIdentificacion());
		if (Objects.isNull(getPersona)) {
			throw new BadRequestException("El cliente no se encuentra registrado");
		}
		try {
			dto.setId(getPersona.getId());
			Clientes cliente = clientesServices.update(dto);
			if (Objects.nonNull(cliente)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", cliente, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede actualizar el Cliente", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que consulta todos los clientes 
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> getAllClientes() {
		List<Personas> list = personasService.getAllPersonas();
		return new ResponseDTO<>(HttpStatus.OK, "OK", list, tracer.currentSpan().context().traceIdString());
	}
	
	/**
     * Método que consulta a un cliente por identificación o por nombres
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> getClienteByParam(String param) {
		Personas getPersona = null;
		if (param.matches("[0-9]*")) {
			getPersona = personasService.getByIdentificacion(param);
		}else {
			getPersona = personasService.getByNombres(param);
		}
		if (Objects.isNull(getPersona)) {
			throw new BadRequestException("No se encuentra el cliente consultado");
		}
		return new ResponseDTO<>(HttpStatus.OK, "OK", getPersona, tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que elimina un cliente por id
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> eliminarCliente(Integer id) {
		clientesServices.delete(id);
		return new ResponseDTO<>(HttpStatus.OK, "OK", "Cliente Eliminado Exitosamente", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que valida si la Cuenta se encuentra registrada
     * y se convierte el objeto recibido en una entidad y se envia la entidad a la clase de servicio de cuentas
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> registrarCuentas(CuentasDTO dto) {
		Personas personas = personasService.getByNombres(dto.getCliente());
		if (Objects.isNull(personas)) {
			throw new BadRequestException("El cliente no se encuentra registrado");
		}
		try {
			Clientes clientes = clientesServices.getById(personas.getId());
			if (!clientes.isEstado()) {
				return new ResponseDTO<>(HttpStatus.OK, "FAILED", "El cliente no se encuentra activo", tracer.currentSpan().context().traceIdString());
			}
			Cuentas cuenta = cuentasServices.save(dto, clientes);
			if (Objects.nonNull(cuenta)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", cuenta, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede registrar la cuenta", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que valida si la cuenta se encuentra registrada y envia la cuenta consultada a modificación
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> modificarCuentas(CuentasDTO dto) {
		Cuentas getCuentas = cuentasServices.getByNumeroCuenta(dto.getNumeroCuenta());
		if (Objects.isNull(getCuentas)) {
			throw new BadRequestException("La cuenta no se encuentra registrada");
		}
		try {
			dto.setId(getCuentas.getId());
			Cuentas cuentas = cuentasServices.update(dto);
			if (Objects.nonNull(cuentas)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", cuentas, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede modificar la cuenta", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que consulta todas las cuentas registradas
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> getAllCuentas() {
		List<Cuentas> cuentas = cuentasServices.getAllCuentas();
		if (cuentas.size() != 0) {
			return new ResponseDTO<>(HttpStatus.OK, "OK", cuentas, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.OK, "No se encuentran registros", cuentas, tracer.currentSpan().context().traceIdString());
	}
	
	/**
     * Método que consulta una cuenta por numero de cuenta
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> getCuentaByParam(String param) {
		Cuentas getCuenta = null;
		if (param.matches("[0-9]*")) {
			getCuenta = cuentasServices.getByNumeroCuenta(Long.parseLong(param));
		}
		if (Objects.isNull(getCuenta)) {
			throw new BadRequestException("No se encuentra la cuenta consultada");
		}
		return new ResponseDTO<>(HttpStatus.OK, "OK", getCuenta, tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que elimina una cuenta por id
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> eliminarCuentas(Integer id) {
		cuentasServices.eliminarCuenta(id);
		return new ResponseDTO<>(HttpStatus.OK, "OK", "Cuenta Eliminada Exitosamente", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que valida si la cuenta asociada al movimiento se encuentra registrada y activa, 
     * se hace un llamado al metodo de calcular saldo y se envia ese valor en el dto, por ultimo se envia a la clase
     * de servicio de movimientos
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> registrarMovimientos(MovimientosDTO dto) {
		Cuentas getCuenta = cuentasServices.getByNumeroCuenta(dto.getCuenta());
		if (Objects.isNull(getCuenta)) {
			throw new BadRequestException("La cuenta no se encuentra registrada");
		}
		if (!getCuenta.isEstado()) {
			throw new BadRequestException("La cuenta no se encuentra activa");
		}
		try {
			Long saldo = movimientosServices.calcularSaldo(getCuenta.getSaldoInicial(), dto.getValor(), dto.getTipoMovimiento());
			if (saldo < 0) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", "Saldo no Disponible", tracer.currentSpan().context().traceIdString());
			}
			dto.setSaldo(saldo);
			Movimientos movimientos = movimientosServices.save(dto, getCuenta);
			if (Objects.nonNull(movimientos)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", movimientos, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede crear el movimiento", tracer.currentSpan().context().traceIdString());
	}
	
	/**
     * Método que consulta todos los movimientos registrados
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> getAllMovimientos() {
		List<Movimientos> list = movimientosServices.getAllMovimientos();
		return new ResponseDTO<>(HttpStatus.OK, "OK", list, tracer.currentSpan().context().traceIdString());
	}
	
	/**
     * Método que consulta movimientos por el numero de cuenta asociada
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> getMovimientoByParam(String param) {
		List<Movimientos> getMovimientos = null;
		if (param.matches("[0-9]*")) {
			getMovimientos = movimientosServices.getByNumeroCuenta(Long.parseLong(param));
		}
		if (Objects.isNull(getMovimientos)) {
			throw new BadRequestException("No se encuentran movimientos relacionados");
		}
		return new ResponseDTO<>(HttpStatus.OK, "OK", getMovimientos, tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que valida si el movimiento se encuentra registrado, 
     * se realiza una consulta por cuenta y se envia a la clase de servicio de movmientos
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     * @throws BadRequestException {@link BadRequestException}
     *
     */
	@Override
	public ResponseDTO<?> modificarMovimientos(MovimientosDTO dto) {
		Cuentas getCuenta = null;
		if (dto.getCuenta() != 0) {
			getCuenta = cuentasServices.getByNumeroCuenta(dto.getCuenta());
			if (Objects.isNull(getCuenta)) {
				throw new BadRequestException("La cuenta no se encuentra registrada");
			}
			if (!getCuenta.isEstado()) {
				throw new BadRequestException("La cuenta no se encuentra activa");
			}
		}
		try {
			Movimientos movimiento = movimientosServices.update(dto, getCuenta);
			if (Objects.nonNull(movimiento)) {
				return new ResponseDTO<>(HttpStatus.OK, "OK", movimiento, tracer.currentSpan().context().traceIdString());
			}
		} catch (Exception e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED", e, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.GATEWAY_TIMEOUT, "FAILED", "No se puede modificar el movimiento", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que elimina un movimiento por id
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> eliminarMovimientos(Integer id) {
		movimientosServices.delete(id);
		return new ResponseDTO<>(HttpStatus.OK, "OK", "Movimiento Eliminado Exitosamente", tracer.currentSpan().context().traceIdString());
	}

	/**
     * Método que consulta los movimientos relacionados a un cliente por una fecha especifica
     *
     * @author Karen Daniela Medina Tovar
     * @return {@link ResponseDto responseDto}
     *
     */
	@Override
	public ResponseDTO<?> getMovimientosByClienteFecha(String fecha, String identificacion) {
		LocalDate date = LocalDate.parse(fecha);
		ConsultaDTO result = movimientosServices.getByFechaAndCliente(date, identificacion);
		if (Objects.nonNull(result)) {
			return new ResponseDTO<>(HttpStatus.OK, "OK", result, tracer.currentSpan().context().traceIdString());
		}
		return new ResponseDTO<>(HttpStatus.OK, "Sin Datos Disponibles", result, tracer.currentSpan().context().traceIdString());
	}

}
