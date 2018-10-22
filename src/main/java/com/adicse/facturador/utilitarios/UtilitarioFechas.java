package com.adicse.facturador.utilitarios;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilitarioFechas {
	
	public String convertirDateToString(Date date){
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String fecha = df.format(date);
        

		
		
		return fecha;
	}
	
	
	public String convertirTimestampToString(Timestamp  ts, String format){
		
		
		Date date = new Date();
		date.setTime(ts.getTime());
		String formattedDate = new SimpleDateFormat(format).format(date);
		
		return formattedDate;
	}

}
