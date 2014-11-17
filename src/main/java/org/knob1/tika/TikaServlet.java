package org.knob1.tika;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

//String[] s_tika =  request.getRequestURI().toString().split("/");
//response.setContentType("text/plain");
//response.setStatus(200);
//writer.println(request.getContentType());
//writer.println("Angekommene Laenge: "+request.getContentLength());
//if(s_tika[1].equals("tika")){
//String myString = IOUtils.toString(bis);
//writer.println(myString);


public class TikaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer;
		try {
			response.setCharacterEncoding("UTF-8");
			Parser parser = new AutoDetectParser();
			Metadata metadata = new Metadata();
			ParseContext parseContext = new ParseContext();
			StringWriter textBuffer = new StringWriter();
			ContentHandler handler = new BodyContentHandler(textBuffer);
			writer = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			parser.parse(request.getInputStream(), handler, metadata, parseContext);
			
			String[] uri =  request.getRequestURI().toString().split("/");
			
			if(uri[1].equals("tika")){
				if(uri[2].equals("text")){
					writer.println(textBuffer.toString());
				}
				else if(uri[2].equals("metadata")){
					for(String name : metadata.names()){
						writer.println("metadata: "+name+" - "+metadata.get(name));
					}
					writer.println("content: "+textBuffer.toString());
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			String[] s_tika =  request.getRequestURI().toString().split("/");
			response.setContentType("text/plain");
			response.setStatus(200);
			
			if(s_tika[1].equals("tika")){
				
			}
			writer.println(request.getRequestURI().toString());
			writer.println(request.getMethod().toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		if(request.getMethod() == "GET"){
			writer.println("hi vom get");
		}
		writer.println(request.getRequestURI().toString());
		writer.println(request.getMethod().toString());
		writer.close();
	}
}