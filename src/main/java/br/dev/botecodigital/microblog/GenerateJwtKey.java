package br.dev.botecodigital.microblog;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class GenerateJwtKey {

	public static void main(String[] args) {
		
		
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String secretString = Encoders.BASE64.encode(key.getEncoded());
	    System.out.println(secretString);

	}

}
