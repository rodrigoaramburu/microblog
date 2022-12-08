package br.dev.botecodigital.microblog.security;

import java.io.IOException;
import java.security.Key;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends GenericFilterBean {

	@Value("${jwt.secret}")
	private String secret;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		final String authHeader = request.getHeader("authorization");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
			return;
		}
		if (!checkAuthHeaderPresent(authHeader)) {
			Map<String, String> error = Maps.of("code", "401").and("message", "Não autorizado").build();
			response.sendError(HttpStatus.UNAUTHORIZED.value(), new ObjectMapper().writeValueAsString(error));
			return;
		}

		final String token = authHeader.substring(7);

		try {
			Claims claims = generateClaim(token);
			request.setAttribute("authUserId", claims.getSubject());
			chain.doFilter(request, response);
		} catch (SignatureException ex) {
			Map<String, String> error = Maps.of("code", "401").and("message", "Token inválido").build();
			response.sendError(HttpStatus.UNAUTHORIZED.value(), new ObjectMapper().writeValueAsString(error));
			return;
		}

	}

	private Claims generateClaim(final String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
		return claims;
	}

	private boolean checkAuthHeaderPresent(final String authHeader) throws ServletException {
		return authHeader != null && authHeader.startsWith("Bearer ");
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(this.secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
