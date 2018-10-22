package com.adicse.facturador.utilitarios;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class Idunico {
	private static final DecimalFormat timeFormat4 = new DecimalFormat("0000;0000");
	public String getIdunico(){
		  Calendar cal = Calendar.getInstance();
		    String val = String.valueOf(cal.get(Calendar.YEAR));
		    val += timeFormat4.format(cal.get(Calendar.DAY_OF_YEAR));
		    val += UUID.randomUUID().toString().replaceAll("-", "");		

		    		
		return val;
	}

}
