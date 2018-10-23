<?xml version="1.0" encoding="UTF-8"?>
<p:SummaryDocuments
	xmlns:p="urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1"
	xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2"
	xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
	xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
	xmlns:sac="urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1">
	<ext:UBLExtensions>
		<ext:UBLExtension>
			<ext:ExtensionContent>

			</ext:ExtensionContent>
		</ext:UBLExtension>
	</ext:UBLExtensions>
	<#-- Version del UBL utilizado para establecer el formato XML -->
	<cbc:UBLVersionID>2.0</cbc:UBLVersionID>
	<#-- Version de la estructura del documento -->
	<cbc:CustomizationID>1.1</cbc:CustomizationID>

	<#-- Identificador del resumen RC+ "-" + anno + "-" + mes + "-" + dia + "-" + "00001" correlativo  -->
	
	<cbc:ID>${identificadorArchivoEnvio}</cbc:ID>

	<#-- Fecha de generacion del resumen -->
	<cbc:ReferenceDate>${fechaeGeneracionResumen}</cbc:ReferenceDate>

	<#-- Fecha de emision de los documentos -->
	<cbc:IssueDate>${fechaEmisionDocumentos}</cbc:IssueDate>

	<cbc:Note><![CDATA[CONSOLIDADO DE BOLETAS DE VENTA]]></cbc:Note>
	<cac:Signature>
		<cbc:ID>${identificadorArchivoEnvio}</cbc:ID>
		<cac:SignatoryParty>
			<cac:PartyIdentification>
				<cbc:ID>${rucEmisor}</cbc:ID>
			</cac:PartyIdentification>
			<cac:PartyName>
				<cbc:Name><![CDATA[SUNAT]]></cbc:Name>
			</cac:PartyName>
		</cac:SignatoryParty>
		<cac:DigitalSignatureAttachment>
			<cac:ExternalReference>
				<cbc:URI>#${identificadorArchivoEnvio}</cbc:URI>
			</cac:ExternalReference>
		</cac:DigitalSignatureAttachment>
	</cac:Signature>

	<cac:AccountingSupplierParty>
		<#-- Numero de RUC -->
		<cbc:CustomerAssignedAccountID>${rucEmisor}
		</cbc:CustomerAssignedAccountID>

		<#-- Tipo de documento de identidad - Catalogo No. 06 -->
		<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>
		<cac:Party>
			<cac:PartyLegalEntity>
				<#-- Apellidos y nombres o denominacion o razon social -->
				<cbc:RegistrationName><![CDATA[${emisor}]]></cbc:RegistrationName>
			</cac:PartyLegalEntity>
		</cac:Party>
	</cac:AccountingSupplierParty>
	
	<#--                 DETALLE DEL DOCUMENTO                       -->
	<#list detalleLineas as item>	
	
		<#-- INFORMACION DE CADA DOCUMENTO BOLETA -->
		<sac:SummaryDocumentsLine>
			<cbc:LineID>${item.item}</cbc:LineID>
			<#-- Tipo de documento - Catalogo No. 01 -->
			<cbc:DocumentTypeCode>03</cbc:DocumentTypeCode>
			<#-- Serie y número de comprobante -->
			<cbc:ID>${item.comprobanteEmitidoSunat}</cbc:ID>
	
			<cac:AccountingCustomerParty>
				<#-- Numero de documento de identidad -->
				<cbc:CustomerAssignedAccountID>${item.numeroDocumentoCliente}
				</cbc:CustomerAssignedAccountID>
	
				<#-- Tipo de documento de identidad - Catalogo No. 06 si el cliente no 
					tiene ruc ni dni el codigo es 0 -->
				<cbc:AdditionalAccountID>${item.codigoTipoDocumentoCliente}
				</cbc:AdditionalAccountID>
			</cac:AccountingCustomerParty>
	
			<cac:Status>
				<#-- (Codigo de operacion del item - catalogo No. 19) 1)adicionar 2)Modificar 
					3)Anulado 4)anulado en el dia(antes de informar el comprobante) -->
				<cbc:ConditionCode>1</cbc:ConditionCode>
			</cac:Status>
	
			<#-- Importe total de la venta, cesion en uso o del servicio prestado -->
			<sac:TotalAmount currencyID="${item.tipoMoneda}">${item.sumTotalVenta}
			</sac:TotalAmount>
	
			<#-- Total valor de venta - operaciones gravadas -->
			<!-- Total Venta Grabadas -->
			<sac:BillingPayment>
				<cbc:PaidAmount currencyID="${item.tipoMoneda}">${item.sumValorVentaGrabada}
				</cbc:PaidAmount>
				<cbc:InstructionID>01</cbc:InstructionID>
			</sac:BillingPayment>
	
			<#-- Total valor de venta - operaciones exoneradas -->
			<!-- Total Venta Exoneradas -->
			<sac:BillingPayment>
				<cbc:PaidAmount currencyID="${item.tipoMoneda}">${item.sumValorVentaExonerada}
				</cbc:PaidAmount>
				<cbc:InstructionID>02</cbc:InstructionID>
			</sac:BillingPayment>
	
			<#-- Total valor de venta - operaciones inafectas -->
			<!-- Total Venta Inafectas -->
			<sac:BillingPayment>
				<cbc:PaidAmount currencyID="${item.tipoMoneda}">0.00</cbc:PaidAmount>
				<cbc:InstructionID>03</cbc:InstructionID>
			</sac:BillingPayment>
	
			<#-- Importe total de sumatoria otros cargos del item -->
			<cac:AllowanceCharge>
				<cbc:ChargeIndicator>true</cbc:ChargeIndicator>
				<cbc:Amount currencyID="${item.tipoMoneda}">0.00</cbc:Amount>
			</cac:AllowanceCharge>
	
			<#-- Total IGV -->
			<cac:TaxTotal>
	
				<#-- Monto Total y Moneda -->
				<cbc:TaxAmount currencyID="${item.tipoMoneda}">${item.sumIgv}</cbc:TaxAmount>
				
	  		<#-- VENTAS GRABADAS  -->
		  	<#if item.sumValorVentaGrabada gt 0.00 >			
				<cac:TaxSubtotal>
					<#-- Monto Total y Moneda -->
					<cbc:TaxAmount currencyID="${item.tipoMoneda}">${item.sumIgv}</cbc:TaxAmount>
					<cac:TaxCategory>
						<cac:TaxScheme>
							<#-- Codigo de tributo - Catalogo No. 05 -->
							<cbc:ID>1000</cbc:ID>
							<#-- Nombre de tributo - Catalogo No. 05 -->
							<cbc:Name>IGV</cbc:Name>
							<#-- Codigo internacional tributo - Catalogo No. 05 -->
							<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>
						</cac:TaxScheme>
					</cac:TaxCategory>
				</cac:TaxSubtotal>
			</#if>
			
	  		<#-- VENTAS EXONERADAS  -->
		  	<#if item.sumValorVentaExonerada gt 0.00 >			
				<cac:TaxSubtotal>
					<#-- Monto Total y Moneda -->
					<cbc:TaxAmount currencyID="${item.tipoMoneda}">${item.sumIgv}</cbc:TaxAmount>
					<cac:TaxCategory>
						<cac:TaxScheme>
							<#-- Codigo de tributo - Catalogo No. 05 -->
							<cbc:ID>9997</cbc:ID>
							<#-- Nombre de tributo - Catalogo No. 05 -->
							<cbc:Name>EXO</cbc:Name>
							<#-- Codigo internacional tributo - Catalogo No. 05 -->
							<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>
						</cac:TaxScheme>
					</cac:TaxCategory>
				</cac:TaxSubtotal>
			</#if>		
			
			</cac:TaxTotal>
	
			<#-- Total Otros tributos -->
			<cac:TaxTotal>
				<#-- Monto Total y Moneda -->
				<cbc:TaxAmount currencyID="${item.tipoMoneda}">0.00</cbc:TaxAmount>
				<cac:TaxSubtotal>
					<#-- Monto Total y Moneda -->
					<cbc:TaxAmount currencyID="${item.tipoMoneda}">0.00</cbc:TaxAmount>
					<cac:TaxCategory>
						<cac:TaxScheme>
							<#-- Codigo de tributo - Catalogo No. 05 -->
							<cbc:ID>9999</cbc:ID>
							<#-- Nombre de tributo - Catalogo No. 05 -->
							<cbc:Name>OTR</cbc:Name>
							<#-- Codigo internacional tributo - Catalogo No. 05 -->
							<cbc:TaxTypeCode>OTH</cbc:TaxTypeCode>
						</cac:TaxScheme>
					</cac:TaxCategory>
				</cac:TaxSubtotal>
			</cac:TaxTotal>
		</sac:SummaryDocumentsLine>
	</#list>

</p:SummaryDocuments>