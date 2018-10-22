package com.adicse.facturador.utilitarios;

public class UtilitarioNexPeriodo {
	
	public Integer getNexMes(Integer mes){
		Integer nexmes = mes;
		if(mes == 12){
			nexmes = 1;
		}else{
			nexmes++;
		}
		
		return nexmes;
	}

	@SuppressWarnings("null")
	public Integer getNexAno(Integer mes,Integer anno){
		Integer rtnAnno = null;
		if(mes == 12){
			rtnAnno++;
	
		}else{
			rtnAnno = anno;
		}
		
		return rtnAnno;
	}	
}
