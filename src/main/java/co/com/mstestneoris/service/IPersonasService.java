package co.com.mstestneoris.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.mstestneoris.entity.Personas;

@Component
public interface IPersonasService {

	public List<Personas> getAllPersonas();
	
	public Personas getByIdentificacion(String identificacion);
	
	public Personas getByNombres(String nombres);
	
}
