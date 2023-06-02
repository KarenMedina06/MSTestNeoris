package co.com.mstestneoris.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.mstestneoris.dto.ClientesDTO;
import co.com.mstestneoris.entity.Clientes;
import co.com.mstestneoris.repository.IClientesRepository;
import co.com.mstestneoris.util.Encrypted;

@Service
@Transactional
public class ClientesServices implements IClientesServices{
	
	@Autowired
	private Encrypted encrypted;
	
	@Autowired
	private IClientesRepository repository;

	@Override
	public Clientes save(ClientesDTO dto) throws Exception {
		Clientes cliente = null;
		try {
			dto.setContrasena(encrypted.encriptar(dto.getIdentificacion(), dto.getContrasena()));
			dto.setEstado(true);
			cliente = new Clientes(dto);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return repository.save(cliente);
	}

	@Override
	public Clientes update(ClientesDTO dto) {
		Clientes clientes = new Clientes(dto);
		return repository.save(clientes);
	}

	@Override
	public List<Clientes> getAllClientes() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public Clientes getById(Integer id) {
		Optional<Clientes> optional = repository.findById(id);
		return optional.get();
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
