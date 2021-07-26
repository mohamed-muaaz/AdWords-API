package com.domo.trining.adwords.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.domo.trining.adwords.Properties;

public class Client {
	protected static HttpURLConnection connection;
	String bodyContentFilePath;
	
	protected void preRequest() throws IOException {
		//Setup request
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		//Setup headers
		connection.setRequestProperty("Authorization", "Bearer " + Properties.ACCESS_TOKEN);
		connection.setRequestProperty("Content-Type", "application/soap+xml");
		connection.setRequestProperty("clientCustomerId", Properties.CLIENT_CUSTOMER_ID);
		connection.setRequestProperty("userAgent", Properties.USER_AGENT);
		connection.setRequestProperty("developerToken", Properties.DEVELOPER_TOKEN);
	}
	
	protected void constructBody(String XmlBodyPath) throws IOException {
		Path path = Paths.get(XmlBodyPath);
		byte[] bodyContent = Files.readAllBytes(path);
		
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
		os.write(bodyContent);
		os.flush();
		os.close();
	}
	
	protected String getResponse() throws IOException {
		BufferedReader reader;
		String line;
		StringBuffer response = new StringBuffer();
		
		int status = connection.getResponseCode();
//		System.out.println(status);
		
		if(status > 299) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		} 
		else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		return response.toString();
	}
	
	protected void printXml(String xml) throws Throwable {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		// initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = result.getWriter().toString();
		System.out.println(xmlString);
	}
	
	public void get() throws Throwable {
		System.out.println("Printing from Parent get method");
	}
}
