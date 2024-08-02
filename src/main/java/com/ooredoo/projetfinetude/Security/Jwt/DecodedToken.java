package com.ooredoo.projetfinetude.Security.Jwt;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class DecodedToken implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DecodedToken.class);
	@Value("${com.ooredoo.jwtSecret}")
	private   String jwtSecret;
	public  Claims getAllClaimsFromToken(String token) {
		
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
			logger.error("Error DecodedToken in getAllClaimsFromToken :: " + e.toString());

		}
		return claims;
	}
 

}
