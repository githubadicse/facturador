package com.adicse.facturador.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.adicse.facturador.config.auth.filter.JWTAuthenticationFilter;
import com.adicse.facturador.config.auth.filter.JWTAuthorizationFilter;
import com.adicse.facturador.config.auth.service.JWTService;
import com.adicse.facturador.service.UsuarioService;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/css/**","/res/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter( new JWTAuthenticationFilter( authenticationManager(), jwtService ))
			.addFilter( new JWTAuthorizationFilter( authenticationManager(),jwtService ))
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			;
		
		super.configure(http);
	}

	
	//este evento es el que se dispara primero que todos.
	//es decir se inyecta el servicio que devolvera el User que contendra informacion de login y clave.
	//si se encuentra regsitrada en la bd.
	@Override
	protected void configure (AuthenticationManagerBuilder build) throws Exception {
		
		build.userDetailsService(usuarioService).passwordEncoder(passwordEncoder) ;

	}




	
	

	

}
