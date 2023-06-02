package co.com.mstestneoris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.mstestneoris.dto.ClientesDTO;
import co.com.mstestneoris.application.IBusinessApplication;
import co.com.mstestneoris.dto.CuentasDTO;
import co.com.mstestneoris.dto.MovimientosDTO;
import co.com.mstestneoris.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "Api/MSTestNeoris")
public class AppController {
	
	@Autowired
	private IBusinessApplication businessApplication;
	
	@GetMapping(value = "/clientes", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar todos los clientes", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Obtener todos los clientes"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getAllClientes(){
		return ResponseEntity.ok(businessApplication.getAllClientes());
	}
	
	@GetMapping(value = "/clientes/get", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar clientes por parametros", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Obtener clientes por parametros"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getClientesByParam(@RequestParam String param){
		return ResponseEntity.ok(businessApplication.getClienteByParam(param));
	}

	@PostMapping(value = "/clientes", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite registrar un cliente", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Cliente Registrado Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> registrarClientes(@RequestBody ClientesDTO dto){
		return ResponseEntity.ok(businessApplication.registrarCliente(dto));
	}
	
	@PutMapping(value = "/clientes", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite modificar un cliente", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Cliente Modificado Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> modificarClientes(@RequestBody ClientesDTO clienteDTO){
		return ResponseEntity.ok(businessApplication.modificarCliente(clienteDTO));
	}
	
	@DeleteMapping(value = "/clientes/{id}", produces = "application/json")
	@ApiOperation(value = "Recurso que permite eliminar un cliente", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Eliminar un cliente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> deleteClientes(@PathVariable("id") Integer id){
		return ResponseEntity.ok(businessApplication.eliminarCliente(id));
	}
	
	@GetMapping(value = "/cuentas", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar todas las cuentas", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Obtener todas las cuentas"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getAllCuentas(){
		return ResponseEntity.ok(businessApplication.getAllCuentas());
	}
	
	@GetMapping(value = "/cuentas/get", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar todas las cuentas", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Obtener cuentas por parametros"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getCuentasByParam(@RequestParam String param){
		return ResponseEntity.ok(businessApplication.getCuentaByParam(param));
	}
	
	@PostMapping(value = "/cuentas", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite registrar una cuenta", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Cuenta Registrada Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> registrarCuentas(@RequestBody CuentasDTO cuentasDTO){
		return ResponseEntity.ok(businessApplication.registrarCuentas(cuentasDTO));
	}
	
	@PutMapping(value = "/cuentas", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite modificar una cuenta", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Cuenta Modificada Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> modificarCuentas(@RequestBody CuentasDTO cuentasDTO){
		return ResponseEntity.ok(businessApplication.modificarCuentas(cuentasDTO));
	}
	
	@DeleteMapping(value = "/cuentas/{id}", produces = "application/json")
	@ApiOperation(value = "Recurso que permite eliminar una cuenta", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Eliminar Cuenta"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> deleteCuentas(@PathVariable("id") Integer id){
		return ResponseEntity.ok(businessApplication.eliminarCuentas(id));
	}
	
	@GetMapping(value = "/movimientos", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar todos los movimientos", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Consulta de todos los movimientos"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getAllMovimientos(){
		return ResponseEntity.ok(businessApplication.getAllMovimientos());
	}
	
	@GetMapping(value = "/movimientos/get", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar movimientos por parametros", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Consulta de Movimiento por parametro"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getMovimientosByParam(@RequestParam String param){
		return ResponseEntity.ok(businessApplication.getMovimientoByParam(param));
	}
	
	@PostMapping(value = "/movimientos", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite registrar un movimiento", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Movimiento Registrado Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> registrarMovimientos(@RequestBody MovimientosDTO movimientosDTO){
		return ResponseEntity.ok(businessApplication.registrarMovimientos(movimientosDTO));
	}
	
	@PutMapping(value = "/movimientos", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Recurso que permite modificar un movimiento", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Movimiento Modificado Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> modificarMovimientos(@RequestBody MovimientosDTO movimientosDTO){
		return ResponseEntity.ok(businessApplication.modificarMovimientos(movimientosDTO));
	}
	
	@DeleteMapping(value = "/movimientos/{id}", produces = "application/json")
	@ApiOperation(value = "Recurso que permite eliminar un movimiento", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Movimiento Eliminado Exitosamente"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> deleteMovimientos(@PathVariable("id") Integer id){
		return ResponseEntity.ok(businessApplication.eliminarMovimientos(id));
	}
	
	
	@GetMapping(value = "/clientes/movimientos/get", produces = "application/json")
	@ApiOperation(value = "Recurso que permite consultar movimientos por parametros", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDTO.class, message = "Consulta de Movimientos por cliente y echa"),
            @ApiResponse(code = 400, response = ResponseDTO.class, message = "Petición Erronea"),
            @ApiResponse(code = 500, response = ResponseDTO.class, message = "Error Interno del Sistema"),
            @ApiResponse(code = 504, response = ResponseDTO.class, message = "Gateway timeout") })
	public ResponseEntity<?> getMovimientosByParam(@RequestParam String fecha, @RequestParam String identificacion){
		return ResponseEntity.ok(businessApplication.getMovimientosByClienteFecha(fecha, identificacion));
	}
}
