package com.artistas.api.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artistas.api.Models.Login;

public interface ILoginDao extends CrudRepository<Login, Long> {
	public Optional<Login> findByUsuario(String usuario);
}
