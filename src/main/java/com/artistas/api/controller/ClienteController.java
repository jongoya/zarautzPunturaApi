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

import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Models.Cliente;
import com.artistas.api.Models.ClienteMasServicios;
import com.artistas.api.Models.Servicio;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.IClienteService;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.IServicioService;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_clientes")
	public ResponseEntity<ArrayList<Cliente>> getClientes(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save_cliente")
	public ResponseEntity<ClienteMasServicios> saveClienteYServicios(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ClienteMasServicios body) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Cliente cliente = clienteService.save(body.getCliente());
		ArrayList<Servicio> servicios = body.getServicios();
		
		for (Servicio servicio: servicios) {
			servicio.setClientId(cliente.getId());
		}
		
		ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
		body.setCliente(cliente);
		body.setServicios(resultados);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@PostMapping("/save_clientes")
	public ResponseEntity<ArrayList<ClienteMasServicios>> saveClientes(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ArrayList<ClienteMasServicios> body) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		for (ClienteMasServicios clienteMasServicios: body) {
			Cliente cliente = clienteService.save(clienteMasServicios.getCliente());
			
			ArrayList<Servicio> servicios = clienteMasServicios.getServicios();
			
			for (Servicio servicio: servicios) {
				servicio.setClientId(cliente.getId());
			}
			
			ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
			
			clienteMasServicios.setCliente(cliente);
			clienteMasServicios.setServicios(resultados);
		}
		
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
	@GetMapping("/get_cliente/{id}")
	public ResponseEntity<?> getCliente(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Cliente resultado = clienteService.findByClienteId(id);
		
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update_cliente")
	public ResponseEntity<?> updateCliente(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Cliente cliente) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (clienteService.findByClienteId(cliente.getId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Cliente resultado = clienteService.updateCliente(cliente);
			ArrayList<Servicio> servicios = servicioService.findByClienteId(cliente.getId());
			
			return new ResponseEntity<>(new ClienteMasServicios(resultado, servicios), HttpStatus.OK);
		}
	}
}
