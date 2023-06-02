package co.com.mstestneoris.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.mstestneoris.dto.ClientesDTO;
import co.com.mstestneoris.entity.Clientes;

@Component
public interface IClientesServices {

	public Clientes save(ClientesDTO clientes) throws Exception;
	
	public Clientes update(ClientesDTO Clientes);
	
	public List<Clientes> getAllClientes();
	
	public Clientes getById(Integer id);
	
	public void delete(Integer id);
}