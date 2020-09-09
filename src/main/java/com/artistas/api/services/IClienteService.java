package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Cliente;

public interface IClienteService {
	public ArrayList<Cliente> findAll();
	public Cliente save(Cliente cliente);
	public Cliente findByClienteId(Long clienteId);
	public Cliente updateCliente(Cliente cliente);
	public void deleteCliente(Cliente cliente);
	public void deleteClientes(ArrayList<Cliente> clientes);
}