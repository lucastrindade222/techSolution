package br.com.lucasdevjava.techSolution.config;


import java.util.Arrays;

import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.security.JWTAuthenticationFilter;
import br.com.lucasdevjava.techSolution.security.JWTAuthorizationFilter;
import br.com.lucasdevjava.techSolution.service.impl.UserDetailsServiceImpl;
import br.com.lucasdevjava.techSolution.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {



	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Autowired
	UserRepository usuarioRepository;




	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();
		http
				.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),jwtUtil))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), userDetailsService))
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
		        .sessionManagement().sessionCreationPolicy(STATELESS);
	return  http.build();
	}




	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
