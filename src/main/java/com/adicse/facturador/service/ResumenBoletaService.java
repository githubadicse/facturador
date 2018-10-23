package com.adicse.facturador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.interf.IAdicseService;
import com.adicse.facturador.model.ResumenBoleta;
import com.adicse.facturador.repo.ResumenBoletaDao;

@Service
@Transactional
public class ResumenBoletaService implements IAdicseService<ResumenBoleta, String> {
	
	@Autowired
	ResumenBoletaDao resumenBoletaDao;

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResumenBoleta> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResumenBoleta> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResumenBoleta save(ResumenBoleta entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ResumenBoleta entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ResumenBoleta> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getMax() {
		return resumenBoletaDao.getMax() == null?1:resumenBoletaDao.getMax()+1; 
	}
	
	

}
