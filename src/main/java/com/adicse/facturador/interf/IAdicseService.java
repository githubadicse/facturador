package com.adicse.facturador.interf;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


public interface IAdicseService <T,ID > {
	
	
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String  sortcolumn, Object filter );
	
	public List<T> getall();
	
	public List<T> getallbyid(List<?> lst );
	
	public T save(T entidad);
	
	public void delete(T entidad);
	
	public void deletebyid(ID id);
	
	public Boolean exists(ID id);
	
	public Optional<T> findbyid(ID id);
	
	public Long count();
	

}
