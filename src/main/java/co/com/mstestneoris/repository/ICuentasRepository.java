package co.com.mstestneoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import co.com.mstestneoris.entity.Cuentas;

@Component
public interface ICuentasRepository extends JpaRepository<Cuentas, Integer>{
	
	@Query(value = "SELECT * FROM cuentas WHERE cuentas.numero_cuenta = :numeroCuenta", nativeQuery = true)
	Cuentas findByNumeroCuenta(Long numeroCuenta);

}
