package co.com.mstestneoris.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import co.com.mstestneoris.entity.Movimientos;

@Component
public interface IMovimientosRepository extends JpaRepository<Movimientos, Integer> {

	@Query(value = "SELECT * FROM movimientos WHERE movimientos.id_cuenta = :idCuenta", nativeQuery = true)
	List<Movimientos> findAllByNumeroCuenta(Long idCuenta);
	
	@Query(value = "SELECT mo.fecha, pe.nombres, cu.numero_cuenta, cu.tipo_cuenta, cu.saldo_inicial, mo.tipo_movimiento, mo.valor, mo.saldo "
			+ "FROM movimientos as mo "
			+ "INNER JOIN cuentas as cu ON cu.id_cuenta = mo.id_cuenta "
			+ "INNER JOIN personas as pe ON cu.id_cliente = pe.id "
			+ "WHERE mo.fecha = :fechaMovimiento AND pe.identificacion = :identificacion", nativeQuery = true)
	String findByFechaAndCliente(LocalDate fechaMovimiento, String identificacion);
	
	
}
