package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Empleado;

public interface IEmpleadoService {
	public ArrayList<Empleado> findAll();
	public Empleado saveEmpleado(Empleado empleado);
	public ArrayList<Empleado> saveEmpleados(ArrayList<Empleado> empleados);
	public Empleado findByEmpleadoId(Long empleadoId);
	public Empleado updateEmpleado(Empleado empleado);
	public void deleteEmpleado(Long empleadoId);
	public void deleteEmpleados(ArrayList<Empleado> empleados);
}
