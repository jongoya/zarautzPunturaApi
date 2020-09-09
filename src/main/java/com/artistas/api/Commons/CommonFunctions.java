package com.artistas.api.Commons;

import com.artistas.api.Models.JwtUser;
import com.artistas.api.Models.Login;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ILoginService;

public class CommonFunctions {

	public static boolean hasTokenAuthorization(String headerToken, JwtValidator validator, ILoginService loginService) {
		if (headerToken == null || !headerToken.startsWith(Constants.BEARER_TOKEN)) {
			return false;
		}
		
		String token = headerToken.substring(7);
		
		JwtUser jwtUser = validator.validate(token);
		
		if (jwtUser == null) {
			return false;
		}
		
		//TODO revisar
		/*Long comercioId = jwtUser.getId();
		Login login = loginService.findByComercioId(comercioId);
		System.out.println(comercioId);
		if (login == null) {
			return false;
		}*/
		
		return true;
	}
}
