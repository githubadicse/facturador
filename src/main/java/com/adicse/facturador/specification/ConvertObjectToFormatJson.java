package com.adicse.facturador.specification;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import com.adicse.facturador.org.json.JSONArray;
import com.adicse.facturador.org.json.JSONObject;

@Component
public class ConvertObjectToFormatJson {

	//Este evento convierte el json generado por PRIMEGN en su componente tabla filter
	//aca convertimos para la busqueda y generacion del predicate devolviendo el objeto Filter
	//para specification.
	
	public Filter ConvertObjectToFormatSpecification(Object object) {
		
		if(object.equals("undefined")) {
			return new Filter();
		}
		String s = object.toString();
		JSONObject obj = new JSONObject();

		JSONObject json = null;
		JSONObject propiedadObj = new JSONObject();
		String value = null, operator=null;
			
		json = new JSONObject(s);
		
		JSONArray ajson = new JSONArray();
		ajson.put(json);
	
			
		Filter filter = new Filter();
		
		List<Filter> lstFilter = new ArrayList<>();
	
		
		filter.setLogic("and");
		
		for (int i = 0; i < ajson.length(); i++) {
	
			
			obj = ajson.getJSONObject(i);
			
			
			for (Object key : obj.keySet()) {
				propiedadObj = (JSONObject) obj.get(key.toString());
				if(propiedadObj.get("value").getClass().getSimpleName().equals("Integer") ) {
					value = propiedadObj.get("value").toString() ;
				}
				if(propiedadObj.get("value").getClass().getSimpleName().equals("String") ) {
					value =  propiedadObj.get("value").toString();
				}
					
			
				operator = propiedadObj.get("matchMode") == null?"":(String) propiedadObj.get("matchMode");	
				
				switch (operator) {
				case "equals":
					operator = "eq";
					break;
				case "contains":
					operator = "contains";
					break;
				default:
					operator="";
				}
				
				Filter filterItem = new Filter();
		
				filterItem.setField(key.toString());
				filterItem.setOperator(operator);
				filterItem.setValue(value);
				
				lstFilter.add(filterItem);
	
				
			}
	
		}
		
		filter.setFilters(lstFilter);
		
		
		return filter;
	}

}
