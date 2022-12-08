package br.dev.botecodigital.microblog.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.dev.botecodigital.microblog.users.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(User user) {
	    Key key = getSigningKey();
	    	    
	    String jwtToken = Jwts.builder()
	    			.setSubject(user.getId().toString())
	    			.setIssuedAt(new Date())
	    			.signWith(key)
	    			.compact();

	    return jwtToken;
	  }
	
	private Key getSigningKey() {
		  byte[] keyBytes = Decoders.BASE64.decode(this.secret);
		  return Keys.hmacShaKeyFor(keyBytes);
	}
}
