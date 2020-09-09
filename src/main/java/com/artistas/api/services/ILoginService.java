package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Login;

public interface ILoginService {
	public Login save(Login login);
	public Login findByUsuario(String usuario);
	public Login updateLogin(Login login);
	public ArrayList<Login> findAllLogins();
	public void deleteLogin (Login login);
}