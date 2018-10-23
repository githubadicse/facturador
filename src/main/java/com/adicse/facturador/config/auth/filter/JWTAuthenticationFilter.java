//package com.adicse.facturador.config.auth.filter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.adicse.facturador.config.auth.model.UsuarioAuth;
//import com.adicse.facturador.config.auth.service.JWTService;
//import com.adicse.facturador.config.auth.service.JWTServiceImpl;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	
//	
//	private AuthenticationManager authenticationManager;
//	
//	
//	private JWTService  jwtService;
//	
//	
//
//	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService  jwtService) {
//		this.authenticationManager = authenticationManager;
//		setRequiresAuthenticationRequestMatcher( new AntPathRequestMatcher("/usuario/login2", "POST"));
//		this.jwtService = jwtService;
//	}
//
//
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		// TODO Auto-generated method stub
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		String username = obtainUsername(request);
//		String password = obtainPassword(request);
//		
//
//		if(username != null && password != null) {
//			logger.info("Username desde request parameter (form-data) :" + username);
//			logger.info("Passwork desde request parameter (form-data) :" + password);
//		}else {
//			UsuarioAuth user = null;
//			try {
//				user = new ObjectMapper().readValue(request.getInputStream(), UsuarioAuth.class);
//				
//				username = user.getUsername();
//				password = user.getPassword();
//				
//				logger.info("Username desde request parameter (raw-data) :" + username);
//				logger.info("Passwork desde request parameter (raw-data) :" + password);				
//				
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		}
//		
//		
//		username = username.trim();		
//		
//		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//				username, password);		
//		
//		Authentication auth = authenticationManager.authenticate(authToken); 
//		return auth;
//	}
//
//
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		String token = jwtService.create(authResult);
//
//		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);
//		
//		Map<String, Object> body = new HashMap<String,Object>();
//		
//		body.put("token", token);
//		body.put("user", (User)authResult.getPrincipal() );
//		body.put("mensaje", "Hola " + ((User)authResult.getPrincipal()).getUsername() +"  has iniciado session con exito ! ");
//		
//		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//		response.setStatus(200);
//		response.setContentType("application/json");
//		
//		
//	}
//
//
//
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException failed) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//		Map<String, Object> body = new HashMap<String,Object>();
//		
//		body.put("mensaje", "Error de autenticacion password o username");		
//		body.put("error", failed.getMessage());
//		
//		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//		response.setStatus(401);
//		response.setContentType("application/json");		
//		
//	}
//	
//	
//
//	
//}
