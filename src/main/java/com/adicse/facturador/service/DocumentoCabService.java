package com.adicse.facturador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.interf.IAdicseService;
import com.adicse.facturador.model.DocumentoCab;
import com.adicse.facturador.repo.IDocumentoCabDao;

@Service
@Transactional
public class DocumentoCabService implements IAdicseService<DocumentoCab, String> {
	
	@Autowired
	private IDocumentoCabDao iDocumentoCabDao;
	
	

	@Override
	public Page<DocumentoCab> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentoCab> getall() {
		// TODO Auto-generated method stub
		return (List<DocumentoCab>) iDocumentoCabDao.findAll();
	}

	@Override
	public List<DocumentoCab> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentoCab save(DocumentoCab entidad) {
		// TODO Auto-generated method stub
		return iDocumentoCabDao.save(entidad);
	}

	@Override
	public void delete(DocumentoCab entidad) {
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
	public Optional<DocumentoCab> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
