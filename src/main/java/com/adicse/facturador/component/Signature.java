package com.adicse.facturador.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.adicse.facturador.util.VariablesGlobales;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:config.properties")
@Component
public class Signature {

	static VariablesGlobales VarGlo = new VariablesGlobales();

	public void firmarArchivoXml(String rutaXML,String rutaFirma,String nombreArchivoCertificado, String passFirma) {
	        
			

	        try {
	        	//Procedimiento para firmar el archivo xml
//	            String rutaXML = "D:\\proyectos\\factura xml\\20209098114-03-B002-00715015";
//	            String rutaFirma = "D:\\proyectos\\factura xml\\NnBpRnFEQ3hpMzZwV2hjNQ==.pfx";
//	            String passFirma = "pxe7aAhuF9bRLP8h";
	            
				Signature(rutaXML, rutaFirma, nombreArchivoCertificado, passFirma);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MarshalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	}
	
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int Signature(String rutaXML, String rutaFirma, String nombreArchivoCertificado, String passFirma) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {

		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
		Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
				Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null,
				null);

		SignedInfo si = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

		// TODO code application logic here
		String fileCertificado = rutaFirma + "/" + nombreArchivoCertificado;
		KeyStore p12 = KeyStore.getInstance("pkcs12");
		p12.load(new FileInputStream(fileCertificado), passFirma.toCharArray());

		Enumeration e = p12.aliases();
		String alias = (String) e.nextElement();
		System.out.println("Alias certifikata:" + alias);

		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12.getEntry(alias,
				new KeyStore.PasswordProtection(passFirma.toCharArray()));
		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

		System.out.println("cert name:" + cert.getSubjectX500Principal().getName());
		System.out.println("cert serial number: " + cert.getSerialNumber());

		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		
		x509Content.add(cert.getSubjectX500Principal().getName());
		
		x509Content.add(cert);
		
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = (KeyInfo) kif.newKeyInfo(Collections.singletonList(xd));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);

		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(rutaXML + ".XML"), "ISO-8859-1");// origen
		doc.getDocumentElement().normalize();

		DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(),
				doc.getDocumentElement().getElementsByTagNameNS("*", "ExtensionContent").item(0));
		javax.xml.crypto.dsig.XMLSignature signature = fac.newXMLSignature(si, (KeyInfo) ki, null, "SignatureSP", null);
		signature.sign(dsc);

		OutputStream os = new FileOutputStream(rutaXML + ".XML");
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();

		trans.transform(new DOMSource(doc), new StreamResult(os));

		///////////////////////ZIPEAMOS///////////////////////
		VarGlo.add_unzip(rutaXML + ".XML", rutaXML + ".ZIP");

		return 1;
	}
}
