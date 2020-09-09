package com.artistas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artistas.api.Models.JwtUser;
import com.artistas.api.Models.Login;
import com.artistas.api.security.JwtGenerator;
import com.artistas.api.services.ILoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	
	private JwtGenerator jwtGenerator;

	public LoginController() {
		this.jwtGenerator = new JwtGenerator();
	}

	@PostMapping("/login_comercio")
	public ResponseEntity<Login> loginComercio(@RequestBody Login login) {
		Login comercioLogin = loginService.findByUsuario(login.getUsuario());
		
		if (comercioLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (comercioLogin.getPassword().compareTo(login.getPassword()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(412));
		}
		
		if (!comercioLogin.isActive()) {
			comercioLogin.setActive(true);
			comercioLogin.setToken(generarToken(comercioLogin));
			comercioLogin = loginService.updateLogin(comercioLogin);
		}
		
		return new ResponseEntity<>(comercioLogin, HttpStatus.OK);
	}
	
	private String generarToken(Login login) {
		JwtUser user = new JwtUser();
		user.setUsername(login.getNombre());
		user.setId(login.getComercioId());
		user.setRole("Admin");
		return jwtGenerator.generate(user);
	}
}
