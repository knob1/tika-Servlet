package org.knob1.tika;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletInputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class test {

	public static void main(String[] args) {
		try {
			extractText("testhtm.html");
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
	public static void extractText(String fileName) throws IOException, SAXException, TikaException {
		//Parser parser = new PDFParser();
		//Parser parser = new AutoDetectParser();
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext();
		StringWriter writer = new StringWriter();
		ContentHandler handler = new BodyContentHandler(writer);
		
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
	    BufferedInputStream bis = new BufferedInputStream(fis);
	    
	   // Parser p = new PDFParser();
	    Parser p = new AutoDetectParser();
	   
	    p.parse(bis, handler, metadata, parseContext);
	 
	    bis.close();
	    
	    System.out.println(writer.toString());
	  }
}
