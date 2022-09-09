package br.com.lucasdevjava.techSolution.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.lucasdevjava.techSolution.model.User;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.security.SecurityConstants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {



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

	public Integer getIdDoUsuario(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return Integer.parseInt(claims.get("id").toString());
		}
		return null;
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}

		}
		return false;

	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	public String jwtdecode(String token) throws UnsupportedEncodingException {

		String pla = token.split("\\.")[1];

		return new String(Base64.decodeBase64(pla), "UTF-8");
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader("Authorization").replace("Bearer ", "");
	}

}
