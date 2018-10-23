package com.adicse.facturador.service;
import static com.adicse.facturador.specification.SpecificationBuilder.selectFrom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adicse.facturador.interf.IAdicseService;
import com.adicse.facturador.model.Usuario;
import com.adicse.facturador.repo.IUsuarioDao;
import com.adicse.facturador.specification.ConvertObjectToFormatJson;
import com.adicse.facturador.specification.Filter;



@Service
@Transactional
public class UsuarioService implements IAdicseService<Usuario, Integer> //**mUserDetailsService**// 
	{
	
	@Autowired
	private IUsuarioDao iUsuarioDao;
	

	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson;

	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	

	@Override
	public Page<Usuario> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Usuario> lista = selectFrom(iUsuarioDao).where(f).findPage(pageable);

		return lista;
	}

	@Override
	public List<Usuario> getall() {
		// TODO Auto-generated method stub
		return (List<Usuario>) iUsuarioDao.findAll();
	}

	@Override
	public List<Usuario> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario save(Usuario entidad) {
		// TODO Auto-generated method stub
		Integer nexId = 0;
		
		if(entidad.getIdusuario() == 0){
			nexId = iUsuarioDao.getMax()==null?1: iUsuarioDao.getMax() + 1;
			entidad.setIdusuario(nexId);
		}
		
		

		return iUsuarioDao.save(entidad);
	}

	@Override
	public void delete(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iUsuarioDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iUsuarioDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario getAllByLogin(String login){
		return iUsuarioDao.findAllByLogin(login);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		if(username == null) {
//			logger.error("No Existe el usuario");
//			
//		}
//		String login = username;
//		Usuario usuario = iUsuarioDao.findAllByLogin(login);
//		
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		UserDetails user = new User(username, usuario.getClave(), usuario.getActivo() , true, true, true, authorities);
//		return user;
//	}

	


	

	
}
