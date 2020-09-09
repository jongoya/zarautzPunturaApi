package com.artistas.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artistas.api.Models.Login;
import com.artistas.api.dao.ILoginDao;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired 
	private ILoginDao loginDao;

	@Override
	public Login save(Login login) {
		return loginDao.save(login);
	}

	//TODO revisar
	/*@Override
	public Login findByComercioId(Long comercioId) {
		return loginDao.findByComercioId(comercioId).orElse(null);
	}*/

	@Override
	public Login findByUsuario(String usuario) {
		return loginDao.findByUsuario(usuario).orElse(null);
	}

	@Override
	public Login updateLogin(Login login) {
		return loginDao.save(login);
	}

	@Override
	public ArrayList<Login> findAllLogins() {
		return (ArrayList<Login>) loginDao.findAll();
	}

	@Override
	public void deleteLogin(Login login) {
		loginDao.delete(login);
	}
}
