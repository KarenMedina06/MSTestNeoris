package co.com.mstestneoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import co.com.mstestneoris.entity.Clientes;

@Component
public interface IClientesRepository extends JpaRepository<Clientes, Integer> {

}
