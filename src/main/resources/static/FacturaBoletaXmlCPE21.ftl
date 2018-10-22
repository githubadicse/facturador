<?xml version="1.0" encoding="utf-8"?>
<#-- PLANTILLAS ELECTRONICAS -->
<#-- https://gist.github.com/giansalex/53d3b6dadb5305ee95928a854ee3abc4 -->
	
	<Invoice xmlns="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	  <ext:UBLExtensions>
	    <ext:UBLExtension>
	      <ext:ExtensionContent>
	         
	      </ext:ExtensionContent> 
	    </ext:UBLExtension> 
	  </ext:UBLExtensions> 
	  <cbc:UBLVersionID>2.1</cbc:UBLVersionID> 
	  <cbc:CustomizationID schemeAgencyName="PE:SUNAT">2.0</cbc:CustomizationID> 
	  <cbc:ProfileID schemeName="Tipo de Operacion" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51">0101</cbc:ProfileID> 
	  <cbc:ID>${numeroDeDocumentoEmitido}</cbc:ID> 

	  <cbc:IssueDate>${fechaEmision}</cbc:IssueDate> 

	  <cbc:IssueTime>${horaEmision}</cbc:IssueTime> 


	  <cbc:DueDate>${fechaVencimiento}</cbc:DueDate> 

	  <cbc:InvoiceTypeCode name="Tipo de Operacion" listAgencyName="PE:SUNAT" listName="Tipo de Documento" listID="0101" listSchemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51">${codigoDocumentoSunat}</cbc:InvoiceTypeCode> 


	  <cbc:Note languageLocaleID="1000">${importeEnLetras}</cbc:Note> 


	  <cbc:DocumentCurrencyCode listID="ISO 4217 Alpha" listName="Currency" listAgencyName="United Nations Economic Commission for Europe">${tipoDeMoneda}</cbc:DocumentCurrencyCode> 
	  <cbc:LineCountNumeric>${numeroDeItems}</cbc:LineCountNumeric> 

	  <cac:Signature> 
	    <cbc:ID>${numeroDeDocumentoEmitido}</cbc:ID> 
	    <cac:SignatoryParty> 
	      <cac:PartyIdentification> 
	        <cbc:ID>${numeroDocumentoCliente}</cbc:ID> 
	      </cac:PartyIdentification> 
	      <cac:PartyName> 
	        <cbc:Name>${razonSocialCliente}></cbc:Name> 
	      </cac:PartyName> 
	    </cac:SignatoryParty> 
	    <cac:DigitalSignatureAttachment> 
	      <cac:ExternalReference> 
	        <cbc:URI>#${numeroDeDocumentoEmitido}</cbc:URI> 
	      </cac:ExternalReference> 
	    </cac:DigitalSignatureAttachment> 
	  </cac:Signature> 


	  <cac:AccountingSupplierParty> 
	    <cac:Party> 
	      <cac:PartyIdentification> 
	        <cbc:ID schemeID="6" schemeName="Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${rucEmisor}</cbc:ID> 
	      </cac:PartyIdentification> 

	      <cac:PartyName> 
	        <cbc:Name><![CDATA[${emisor}]]></cbc:Name> 
	      </cac:PartyName> 

	      <cac:PartyTaxScheme> 
	        <cbc:RegistrationName><![CDATA[${emisor}]]></cbc:RegistrationName> 
	        <cbc:CompanyID schemeID="6" schemeName="SUNAT:Identificador de Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${rucEmisor}</cbc:CompanyID> 
	        <cac:TaxScheme> 
	          <cbc:ID schemeID="6" schemeName="SUNAT:Identificador de Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${rucEmisor}</cbc:ID> 
	        </cac:TaxScheme> 
	      </cac:PartyTaxScheme> 

	      <cac:PartyLegalEntity> 
	        <cbc:RegistrationName><![CDATA[${emisor}]]></cbc:RegistrationName> 

	        <cac:RegistrationAddress> 
	          <cbc:ID schemeName="Ubigeos" schemeAgencyName="PE:INEI" /> 
	          <cbc:AddressTypeCode listAgencyName="PE:SUNAT" listName="Establecimientos anexos">0000</cbc:AddressTypeCode> 
	          <cbc:CityName><![CDATA[]]></cbc:CityName> 
	          <cbc:CountrySubentity><![CDATA[]]></cbc:CountrySubentity> 
	          <cbc:District><![CDATA[]]></cbc:District> 

	          <cac:AddressLine> 
	            <cbc:Line><![CDATA[]]></cbc:Line> 
	          </cac:AddressLine> 

	          <cac:Country> 
	            <cbc:IdentificationCode listID="ISO 3166-1" listAgencyName="United Nations Economic Commission for Europe" listName="Country">PE</cbc:IdentificationCode> 
	          </cac:Country> 

	        </cac:RegistrationAddress> 

	      </cac:PartyLegalEntity> 

	      <cac:Contact> 
	        <cbc:Name><![CDATA[]]></cbc:Name> 
	      </cac:Contact> 

	    </cac:Party> 
	  </cac:AccountingSupplierParty> 


	  <cac:AccountingCustomerParty> 
	    <cac:Party>
	    
	      <#-- Catálogo No. 06: Códigos de Tipos de Documentos de Identidad -->
	      <#-- 0 DOC.TRIB.NO.DOM.SIN.RUC, 1 DOC. NACIONAL DE IDENTIDAD ,4 CARNET DE EXTRANJERIA,6 REG. UNICO DE CONTRIBUYENTES  -->
	      <cac:PartyIdentification>
	        <cbc:ID schemeID="${tipoDocumentoCliente}" schemeName="Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${numeroDocumentoCliente}</cbc:ID> 
	      </cac:PartyIdentification>
	      
	      <#-- Razon Social, si Tipo de Documento es 0, Publico General -->
	      <cac:PartyName> 
	        <cbc:Name><![CDATA[${razonSocialCliente}]]></cbc:Name> 
	      </cac:PartyName>
	       
	      <cac:PartyTaxScheme> 
	        <cbc:RegistrationName><![CDATA[${razonSocialCliente}]]></cbc:RegistrationName> 
	        <cbc:CompanyID schemeID="${tipoDocumentoCliente}" schemeName="SUNAT:Identificador de Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${numeroDocumentoCliente}</cbc:CompanyID> 
	        <cac:TaxScheme> 
	          <cbc:ID schemeID="${tipoDocumentoCliente}" schemeName="SUNAT:Identificador de Documento de Identidad" schemeAgencyName="PE:SUNAT" schemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06">${numeroDocumentoCliente}</cbc:ID> 
	        </cac:TaxScheme> 
	      </cac:PartyTaxScheme> 
	      
	      <cac:PartyLegalEntity> 
	        <cbc:RegistrationName><![CDATA[${razonSocialCliente}]]></cbc:RegistrationName> 
	        <cac:RegistrationAddress> 
	          <cbc:ID schemeName="Ubigeos" schemeAgencyName="PE:INEI"> 
	          </cbc:ID> 
	          <cbc:CityName><![CDATA[]]></cbc:CityName> 
	          <cbc:CountrySubentity><![CDATA[]]></cbc:CountrySubentity> 
	          <cbc:District><![CDATA[]]></cbc:District> 
	          <cac:AddressLine> 
	            <cbc:Line><![CDATA[DIRECCION]]></cbc:Line> 
	          </cac:AddressLine> 
	          <cac:Country> 
	            <cbc:IdentificationCode listID="ISO 3166-1" listAgencyName="United Nations Economic Commission for Europe" listName="Country">PE</cbc:IdentificationCode> 
	          </cac:Country> 
	        </cac:RegistrationAddress> 
	      </cac:PartyLegalEntity>
	       
	    </cac:Party> 
	  </cac:AccountingCustomerParty> 



	  <cac:TaxTotal> 
	    <cbc:TaxAmount currencyID="${tipoDeMoneda}">${sumaTotalDeImpuestos}</cbc:TaxAmount> 

	  <#-- VENTAS GRABADAS  -->
	  <#if sumaValorVentaGrabada gt 0.00 >
	    <cac:TaxSubtotal> 
	      <cbc:TaxableAmount currencyID="${tipoDeMoneda}">${sumaValorVentaGrabada}</cbc:TaxableAmount> 
	      <cbc:TaxAmount currencyID="${tipoDeMoneda}">${sumaTotalDeImpuestos}</cbc:TaxAmount> 

	      <cac:TaxCategory> 
	        <cbc:ID schemeID="UN/ECE 5305" schemeName="Tax Category Identifier" schemeAgencyName="United Nations Economic Commission for Europe">${tipoDeAfectacion}</cbc:ID> 
	        <cac:TaxScheme> 
	          <cbc:ID schemeID="UN/ECE 5153" schemeAgencyID="6">1000</cbc:ID> 
	          <cbc:Name>${afecto}</cbc:Name> 
	          <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode> 
	        </cac:TaxScheme> 
	      </cac:TaxCategory> 

	    </cac:TaxSubtotal> 
	  </#if>

	  <#-- VENTAS EXONERADAS  -->
      <#if sumaValorVentaExonerado gt 0>
	    <cac:TaxSubtotal> 
	      <cbc:TaxableAmount currencyID="${tipoDeMoneda}">${sumaValorVentaExonerado}</cbc:TaxableAmount> 
	      <cbc:TaxAmount currencyID="${tipoDeMoneda}">0.00</cbc:TaxAmount> 

	      <cac:TaxCategory> 
	        <cbc:ID schemeID="UN/ECE 5305" schemeName="Tax Category Identifier" schemeAgencyName="United Nations Economic Commission for Europe">E</cbc:ID> 
	        <cac:TaxScheme> 
	          <cbc:ID schemeID="UN/ECE 5153" schemeAgencyID="6">9997</cbc:ID> 
	          <cbc:Name>${afecto}</cbc:Name> 
	          <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode> 
	        </cac:TaxScheme> 
	      </cac:TaxCategory> 

	    </cac:TaxSubtotal> 

	  	</cac:TaxTotal> 
      </#if>


	  <cac:LegalMonetaryTotal> 
	    <cbc:PayableAmount currencyID="${tipoDeMoneda}">${sumaImporteTotalVenta}</cbc:PayableAmount> 
	  </cac:LegalMonetaryTotal>


	<#--                 DETALLE DEL DOCUMENTO                       -->
	<#list detalleLineas as item>

	  

		  <cac:InvoiceLine> 
		    <cbc:ID>${item.item}</cbc:ID> 
		    
		    <#-- Cantidad -->
		    <cbc:InvoicedQuantity unitCode="NIU" unitCodeListID="UN/ECE rec 20" unitCodeListAgencyName="United Nations Economic Commission for Europe">${item.cantidad}</cbc:InvoicedQuantity> 
	
		    <#-- Valor venta-->
		    <#if afecto == 'IGV'> 
		    	<cbc:LineExtensionAmount currencyID="${tipoDeMoneda}">${item.valorVentaGrabado}</cbc:LineExtensionAmount>
		    </#if> 
			<#if afecto == 'EXO'> 
		    	<cbc:LineExtensionAmount currencyID="${tipoDeMoneda}">${item.valorVentaExonerado}</cbc:LineExtensionAmount>
		    </#if> 
		    
		    <cac:PricingReference> 
	
		      <cac:AlternativeConditionPrice> 
	
				<#-- Precio Unitario Incluido Igv -->
		        <cbc:PriceAmount currencyID="PEN">${item.precioUnitarioIncluidoIgv}</cbc:PriceAmount> 
	
				<#-- Tipo Precio de Venta 01 incluye Igv 02 Valor referencial unitario en operaciones no onerosas -->
		        <cbc:PriceTypeCode listName="Tipo de Precio" listAgencyName="PE:SUNAT" listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16">${item.tipoPrecioVenta}</cbc:PriceTypeCode> 
	
		      </cac:AlternativeConditionPrice> 
	
		    </cac:PricingReference> 
	
		    <#-- RUBRO PARA DESCUENTOS DEL ITEM -->	
		    <#if item.totalDescuento gt 0 >    
			    <cac:AllowanceCharge>
		            <cbc:ChargeIndicator>false</cbc:ChargeIndicator>
					<cbc:AllowanceChargeReasonCode>00</cbc:AllowanceChargeReasonCode>
			
					<#-- porcentaje de descuento ejemplo 10% seria 0.10 -->	  
					<cbc:MultiplierFactorNumeric>${item.porcentajeDescuento}</cbc:MultiplierFactorNumeric>
			
					<#-- descuentoUnitario = precioUnitarioSinIgv * porcentajeDescuento -> 83.050847 * 0.10 = 8.3050847 -->
					<#-- totalDescuento= cantidad * descuentoUnitario -> 2000 * 8.3050847 = 16,610.17 -->	  
					<cbc:Amount currencyId=${tipoDeMoneda}>${item.totalDescuento}</cbc:Amount>
				
					<cbc:BaseAmount currencyId=${tipoDeMoneda}>${baseImponible}</cbc:BaseAmount>
				
			    </cac:AllowanceCharge>
			    
		    </#if>
	
		    <cac:TaxTotal>
		      <#-- impuestoTotal = valorVenta * (porcentajeIgv / 100) -> 149,491.52 * 0.18  = 26,908.47  -->	  
		      <cbc:TaxAmount currencyID="${tipoDeMoneda}">${item.impuestoTotal}</cbc:TaxAmount> 
	
			  <#if afecto == 'IGV'> 
			      <cac:TaxSubtotal>
		
					<#-- valorVenta = baseImponible - totalDescuento -> 166,101.69 -  16,610.17 = 149,491.52 -->
			        <cbc:TaxableAmount currencyID="${tipoDeMoneda}">${item.valorVentaGrabado}</cbc:TaxableAmount> 
		
			        <cbc:TaxAmount currencyID="${tipoDeMoneda}">${item.impuestoTotal}</cbc:TaxAmount> 
		
			        <cac:TaxCategory> 
			          	<cbc:ID schemeID="UN/ECE 5305" schemeName="Tax Category Identifier" schemeAgencyName="United Nations Economic Commission for Europe">${tipoDeAfectacion}</cbc:ID> 
			          
				  		<cbc:Percent>${item.porcentajeIgv}</cbc:Percent> 
		
			          	<cbc:TaxExemptionReasonCode listAgencyName="PE:SUNAT" listName="SUNAT:Codigo de Tipo de Afectación del IGV" listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07">${item.tipoAfectacionIgv}</cbc:TaxExemptionReasonCode> 
		
				        <cac:TaxScheme> 
				            <cbc:ID schemeID="UN/ECE 5153" schemeName="Tax Scheme Identifier" schemeAgencyName="United Nations Economic Commission for Europe">${item.tipoDeTributo}</cbc:ID> 
				            <cbc:Name>${afecto}</cbc:Name> 
				            <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode> 
				        </cac:TaxScheme> 
		
			        </cac:TaxCategory> 
		
			      </cac:TaxSubtotal> 
		       </#if>
		       
			  <#if afecto == 'EXO'> 
			      <cac:TaxSubtotal>
		
					<#-- valorVenta = baseImponible - totalDescuento -> 166,101.69 -  16,610.17 = 149,491.52 -->
			        <cbc:TaxableAmount currencyID="${tipoDeMoneda}">${item.valorVentaExonerado}</cbc:TaxableAmount> 
		
			        <cbc:TaxAmount currencyID="${tipoDeMoneda}">0.00</cbc:TaxAmount> 
		
			        <cac:TaxCategory> 
			          	<cbc:ID schemeID="UN/ECE 5305" schemeName="Tax Category Identifier" schemeAgencyName="United Nations Economic Commission for Europe">${tipoDeAfectacion}</cbc:ID> 
			          
				  		<cbc:Percent>${item.porcentajeIgv}</cbc:Percent> 
		
			          	<cbc:TaxExemptionReasonCode listAgencyName="PE:SUNAT" listName="SUNAT:Codigo de Tipo de Afectación del IGV" listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07">${item.tipoAfectacionIgv}</cbc:TaxExemptionReasonCode> 
		
				        <cac:TaxScheme> 
				            <cbc:ID schemeID="UN/ECE 5153" schemeName="Tax Scheme Identifier" schemeAgencyName="United Nations Economic Commission for Europe">${item.tipoDeTributo}</cbc:ID> 
				            <cbc:Name>${afecto}</cbc:Name> 
				            <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode> 
				        </cac:TaxScheme> 
		
			        </cac:TaxCategory> 
		
			      </cac:TaxSubtotal> 
		       </#if>		       
		       
		       
		       
		    </cac:TaxTotal> 
		   
	
		    <cac:Item> 
		      <cbc:Description><![CDATA[${item.producto}]]></cbc:Description> 
		      <cac:SellersItemIdentification> 
		        <cbc:ID><![CDATA[0001]]></cbc:ID> 
		      </cac:SellersItemIdentification> 
		    </cac:Item> 
		    
		    <cac:Price> 
		      <cbc:PriceAmount currencyID="${tipoDeMoneda}">${item.precioUnitarioSinIgv}</cbc:PriceAmount> 
		    </cac:Price> 
		  </cac:InvoiceLine> 


	</#list>
            		
 </Invoice>
