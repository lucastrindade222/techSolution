package br.com.lucasdevjava.techSolution.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucasdevjava.techSolution.model.User;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.service.exception.GenericException;
import br.com.lucasdevjava.techSolution.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private  JWTUtil jwtUtil;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {


		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Credentials usuario = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);

			return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getEmail(), usuario.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {


		String token = generateToken(authResult);
		response.setStatus(200);
		response.setHeader(SecurityConstants.HEADER_STRING,token);

	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {


		var genericException =	 GenericException
					 .builder()
					 .error("Não autorizado")
					 .message("Email ou senha inválidos.")
					 .path("/api/v1/login")
					 .status(401)
				     .build();
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(genericException.tojson());
		}


	}

	public String generateToken(Authentication authResult ) {

		String email = ((User) authResult.getPrincipal()).getUsername();
		Collection<? extends GrantedAuthority> profile = ((User) authResult.getPrincipal()).getAuthorities();
		Integer id = ((User) authResult.getPrincipal()).getId();

		String token = Jwts.builder()
				.setSubject(email)
				.claim("id", id)
				.claim("email", email)
				.claim("role", profile)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.compact();



		return SecurityConstants.TOKEN_PREXI+token;

	}
 
}