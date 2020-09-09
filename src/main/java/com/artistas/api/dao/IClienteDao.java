package com.artistas.api.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.artistas.api.Models.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
	public Optional<Cliente> findById(Long clienteId);
}
