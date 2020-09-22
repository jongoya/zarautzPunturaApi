package com.artistas.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artistas.api.Models.SistemasEquilibrio;
import com.artistas.api.dao.ISistemaDao;

@Service
public class SistemaServiceImpl implements ISistemaService{

	@Autowired
	private ISistemaDao sistemaDao;
	
	@Override
	public ArrayList<SistemasEquilibrio> findAll() {
		return (ArrayList<SistemasEquilibrio>) sistemaDao.findAll();
	}
}
