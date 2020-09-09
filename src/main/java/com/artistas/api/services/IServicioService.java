package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Servicio;

public interface IServicioService {
	public ArrayList<Servicio> findAll();
	public Servicio saveServicio(Servicio servicio);
	public ArrayList<Servicio> saveServicios(ArrayList<Servicio> servicios);
	public Servicio findByServicioId(Long servicioId);
	public ArrayList<Servicio> findByClienteId(Long clienteId);
	public ArrayList<Servicio> findByProfesional(Long profesional);
	public Servicio updateServicio(Servicio servicio);
	public void deleteServicio(Long servicioId);
	public ArrayList<Servicio> findByRange(Long fechaInicio, Long fechaFin);
	public void deleteServicios(ArrayList<Servicio> servicios);
}
