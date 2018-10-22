package com.adicse.facturador.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.model.DocumentoCab;
import com.adicse.facturador.service.DocumentoCabService;



@RestController
@RequestMapping("/comprobante")
public class DocumentoCabController {
	
	@Autowired
	private DocumentoCabService  documentoCabService;
	
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filters		
			){
	
		Map<String,Object> response = new HashMap<String, Object>();
	
		Page<DocumentoCab> page = documentoCabService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<DocumentoCab> lst = page.getContent() ;
		if(lst.size() == 0 ) {
			 lst = new ArrayList<>();
		}
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		
		return response;
	
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public DocumentoCab getEdit(@RequestParam("id") String id) {
		return documentoCabService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public DocumentoCab postCreate(@RequestBody DocumentoCab documentoCab) {
		documentoCab.setIdDocumentoCab("");
		return documentoCabService.save(documentoCab);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public DocumentoCab putUdate(@RequestBody DocumentoCab documentoCab) {
		
	DocumentoCab documentoCabUpdate = documentoCabService.findbyid(documentoCab.getIdDocumentoCab()).get();
		
		BeanUtils.copyProperties(documentoCab, documentoCabUpdate);
		
		return documentoCabService.save(documentoCabUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		documentoCabService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<DocumentoCab> getall(){
		return documentoCabService.getall();
	}

}
