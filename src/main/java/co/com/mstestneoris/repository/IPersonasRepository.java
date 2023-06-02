package co.com.mstestneoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import co.com.mstestneoris.entity.Personas;

@Component
public interface IPersonasRepository extends JpaRepository<Personas, Integer> {

	@Query(value = "SELECT * FROM personas WHERE personas.identificacion = :identificacion", nativeQuery = true)
	Personas findByIdentification(String identificacion);
	
	@Query(value = "SELECT * FROM personas WHERE personas.nombres = :nombres", nativeQuery = true)
	Personas findByNombres(String nombres);
	
	
}
