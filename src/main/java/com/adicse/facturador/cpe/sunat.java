/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adicse.facturador.cpe;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
/*=============================================*/
import java.io.StringWriter;
/*==================================*/
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import com.adicse.facturador.util.VariablesGlobales;
import java.io.File;
import org.w3c.dom.NodeList;

public class sunat {

    static VariablesGlobales VarGlo = new VariablesGlobales();

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    //FIRMAR DOCUMENTO XML
    //https://gist.github.com/jsbsantos/4118253
    //http://www.oracle.com/technetwork/articles/javase/dig-signature-api-140772.html
    public static void main(String[] args) {
        try {

            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    = new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });
            String ruc = "20209098114";
            String UsuSol = "SERVICIO";
            String PassSol = "CONTABILIDAD";
            String RutaCPE = "D:\\proyectos\\factura xml\\20209098114-03-B002-00715015";
            String NombreCPE = "20209098114-03-B002-00715015";
            String RutaRta = "D:\\proyectos\\factura xml\\respuesta\\";
            String NombreArchvoRta = "R-20209098114-03-B002-00715015";

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuSol, PassSol, NombreCPE, RutaCPE), url);
            
            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();

            VarGlo.decode(str1, RutaRta + NombreArchvoRta);

            System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            e.printStackTrace();
        }
    }

    
    
    
    /*
     PARA CARGAR CATALOGO DE COMISIONES
     ========GetCommissions=========
     */
    
    public String[] ConexionCPEBeta(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";

            if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    //======ENVIO DOCUMENTOS (BOLETA, FACTURA, NOTA CREDITO, NOTA DEBITO, ANULADOS, RESUMEN BOLETA)=========================================
    private static SOAPMessage CPEsendBill(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "iso-8859-1");
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("ser", serverURI_ser);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);

        SOAPHeader Header = envelope.getHeader();
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);//"20173014971MODDATOS");
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement sendBill = null;
//        if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC") || NombreCPE.substring(12, 14).equals("RR")) {
//            sendBill = soapBody.addChildElement("sendSummary", "ser");
//            //System.out.println("RTA: "+NombreCPE.substring(12, 14));
//        } else {
        sendBill = soapBody.addChildElement("sendBill", "ser");
        //}

        //SOAPElement sendBill = soapBody.addChildElement("sendBill", "ser");
        SOAPElement fileName = sendBill.addChildElement("fileName").addTextNode(NombreCPE + ".ZIP");

        String ZIP64 = VarGlo.encode(RutaCPE + ".ZIP");
        SOAPElement contentFile = sendBill.addChildElement("contentFile").addTextNode(ZIP64);

        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

      /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(System.out);
        //StreamResult result = new StreamResult(writer);
        transformer.transform(sourceContent, result);

        String strResult = writer.toString();
        //GuardarComision(strResult);
    }

}
