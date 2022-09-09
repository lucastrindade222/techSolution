package br.com.lucasdevjava.techSolution.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nameRole;

	@ManyToMany
	private List<User> usuarios;

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	@Override
	public String getAuthority() {
		return this.nameRole;
	}

}
