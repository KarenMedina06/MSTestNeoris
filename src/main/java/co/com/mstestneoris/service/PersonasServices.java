package co.com.mstestneoris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.mstestneoris.entity.Personas;
import co.com.mstestneoris.repository.IPersonasRepository;

@Service
@Transactional
public class PersonasServices implements IPersonasService{
	
	@Autowired
	private IPersonasRepository repository;

	@Override
	public List<Personas> getAllPersonas() {
		return repository.findAll();
	}

	@Override
	public Personas getByIdentificacion(String identificacion) {
		return repository.findByIdentification(identificacion);
	}

	@Override
	public Personas getByNombres(String nombres) {
		return repository.findByNombres(nombres);
	}

}
