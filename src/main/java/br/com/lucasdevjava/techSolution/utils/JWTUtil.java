package br.com.lucasdevjava.techSolution.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.lucasdevjava.techSolution.dto.RoleDTO;
import br.com.lucasdevjava.techSolution.model.User;
import br.com.lucasdevjava.techSolution.security.SecurityConstants;
import org.apache.tomcat.util.codec.binary.Base64;
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

	public Integer getIdByToken(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return Integer.parseInt(claims.get("id").toString());
		}
		return null;
	}
	public String getEmailFromSub(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	public String getProfile(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {

			return claims.get("role").toString();
		}
		return null;
	}



	public List<RoleDTO> getRolesByRequest(HttpServletRequest request){

		String token =	this.getTokenFromRequest(request);
		String json = this.getProfile(token);



		return fromJsonToRoles(json);

	}
	public Integer  getIdByRequest(HttpServletRequest request){
		String token =	this.getTokenFromRequest(request);
		Integer idUser = this.getIdByToken(token);
		return  idUser;
	}

	public String  getEmailByRequest(HttpServletRequest request){
		String token =	this.getTokenFromRequest(request);
		String emailUser = this.getEmailFromSub(token);
		return  emailUser;
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
	public String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader("Authorization").replace("Bearer ", "");
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
  public List<RoleDTO> fromJsonToRoles(String json) {

	  String initialMarker = "e=";
	  String endMarker = ", ";

	  List<RoleDTO> roles = new ArrayList<>();


	  List<Integer> start = new ArrayList<>();
	  List<Integer> end = new ArrayList<>();

	  for (int index = json.indexOf(initialMarker); index >= 0; index = json.indexOf(initialMarker, index + 1)) {

		  start.add(index +2);
	  }
	  for (int index = json.indexOf(endMarker); index >= 0; index = json.indexOf(endMarker, index + 1)) {
		  System.out.println(json.charAt(index));

		  end.add(index);
	  }
	  int counter = 0;
	  for (Integer integer : start) {
		  String role = json.substring(integer, end.get(counter));
		  System.out.println(role);
	     roles.add(new RoleDTO(role,role));
		  counter++;
	  }

	  return roles;
  }


}
