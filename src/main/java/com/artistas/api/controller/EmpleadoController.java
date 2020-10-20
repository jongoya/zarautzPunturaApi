package com.artistas.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.IEmpleadoService;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.IServicioService;
import com.artistas.api.services.ITipoServicioService;
import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Commons.Constants;
import com.artistas.api.Models.Empleado;
import com.artistas.api.Models.EmpleadoMasServicios;
import com.artistas.api.Models.Servicio;
import com.artistas.api.Models.TipoServicio;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ITipoServicioService tipoServicioService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_empleados")
	public ResponseEntity<ArrayList<Empleado>> getEmpleados(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Empleado> empleados = empleadoService.findAll();
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	
	@PostMapping("/save_empleado")
	public ResponseEntity<?> saveEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Empleado resultado = empleadoService.saveEmpleado(empleado);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_empleados")
	public ResponseEntity<?> saveEmpleados(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ArrayList<Empleado> empleados) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Empleado> resultado = empleadoService.saveEmpleados(empleados);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_empleado")
	public ResponseEntity<?> updateEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (empleadoService.findByEmpleadoId(empleado.getEmpleadoId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Empleado resultado = empleadoService.updateEmpleado(empleado);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
	
	@GetMapping("get_empleado/{id}")
	public ResponseEntity<?>getEmpleadoWithId(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Empleado empleado = empleadoService.findByEmpleadoId(id);
		if (empleado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(empleado, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_empleado")
	public ResponseEntity<?> deleteEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (empleadoService.findByEmpleadoId(empleado.getEmpleadoId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			empleadoService.deleteEmpleado(empleado.getEmpleadoId());
			ArrayList<Empleado> empleados = empleadoService.findAll();

			Empleado empleadoJefe = new Empleado();
			ArrayList<Servicio> resultados = new ArrayList<>();
			if (empleados.size() > 0) {
				empleadoJefe = getEmpleadoJefe(empleados);
				ArrayList<Servicio> servicios = servicioService.findByProfesional(empleado.getEmpleadoId());
				for (Servicio servicio : servicios) {
					servicio.setEmpleadoId(empleadoJefe.getEmpleadoId());
				}
				
				ArrayList<TipoServicio> tipoServicios = tipoServicioService.findAll();
				for (TipoServicio tipoServicio : tipoServicios) {
					if (tipoServicio.getTerapeuta() == empleado.getEmpleadoId()) {
						tipoServicio.setTerapeuta(empleadoJefe.getEmpleadoId());
					}
				}
				
				tipoServicioService.saveTipoServicios(tipoServicios);
				
				resultados = servicioService.saveServicios(servicios);
			}

			return new ResponseEntity<>(new EmpleadoMasServicios(empleado, resultados), HttpStatus.OK);
		}
	}
	
	private Empleado getEmpleadoJefe(ArrayList<Empleado> empleados) {
		for (Empleado empleado : empleados) {
			if (empleado.getIs_empleado_jefe()) {
				return empleado;
			}
		}
		
		return empleados.get(0);
	}
}
