package fr.inria.oak.paxquery.xparser.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLUnit;

import fr.inria.oak.paxquery.xparser.client.XClient;

@RunWith(Parameterized.class)
public class TestXClient {
	public final String query_to_test_filepath = "/Users/jalvaro/XML/query_outputs/query";
	public final String query_to_test_fileextension = "txt";
	
	public String query;
	public String result;
	public String test_number;
	
	public TestXClient(String query, String output, String test_number)  {
		this.query = query;
		this.result = output;
		this.test_number = test_number;
	}
	
	@Parameters 
	public static Collection<String[]> dataSets() throws SAXException, IOException, ParserConfigurationException 
	{
		ArrayList<String[]> testCases = new ArrayList<String[]>();
		
		String testCaseFilePath = "src/test/resources/xclient_tests.xml";
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse (new File(testCaseFilePath));
        
        // normalize text representation
        doc.getDocumentElement().normalize();
        System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
		
        NodeList nodeList = doc.getElementsByTagName("test");
        System.out.println("Number of tests: " + nodeList.getLength());

        for(int i=0; i<nodeList.getLength(); i++) {
        	String[] testcase = new String[3];
        	Element element = (Element)nodeList.item(i);
        	testcase[0] = element.getElementsByTagName("query").item(0).getTextContent();
        	testcase[1] = element.getElementsByTagName("result").item(0).getTextContent();
        	testcase[2] = element.getElementsByTagName("order").item(0).getTextContent();
        	testCases.add(testcase);
        }
        return testCases;        
	}

	@Test
	public void test() {
		try {
			System.out.println("\nTest "+test_number);
			System.out.println("Query: "+query);
			
			String outputfile = query_to_test_filepath+test_number+"."+query_to_test_fileextension;
			//process query
			XClient.test_main(query, outputfile, "1");

			//PAXQuery output
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document documentToTest = docBuilder.parse(insertDummyRootElement(new FileInputStream(outputfile)));
	
	        //BaseX output
	        docBuilderFactory = DocumentBuilderFactory.newInstance();
	        docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document referenceDocument = docBuilder.parse(insertDummyRootElement(new ByteArrayInputStream(result.getBytes("utf-8"))));
	
	        
			System.out.println("Query results are in file "+outputfile);
			
			//compare with expected result
			XMLUnit.setIgnoreWhitespace(true);
	        XMLUnit.setIgnoreAttributeOrder(true);
	        XMLUnit.setIgnoreComments(true);
	        XMLUnit.setNormalizeWhitespace(true);
	        XMLUnit.setNormalize(true);
	        XMLUnit.setCompareUnmatched(false);
	        
	        Diff diff = new Diff(documentToTest, referenceDocument);
	        diff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
	        assertTrue("Differences found: "+diff.toString(), diff.similar());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	private SequenceInputStream insertDummyRootElement(InputStream inputstream) {
		String dummyStart = "<?xml version=\"1.0\"?><DummyRootElement>";
	    String dummyEnd = "</DummyRootElement>";
	    
		return new SequenceInputStream(Collections.enumeration(Arrays.asList(new InputStream[] {new ByteArrayInputStream(dummyStart.getBytes()), inputstream, new ByteArrayInputStream(dummyEnd.getBytes())})));
	}
	
	@SuppressWarnings("unused")
	private String documentToString(Document doc) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
			return output;
		} catch(Exception e) {
			System.out.println("documentToString exception: "+e.getMessage());
			return "";
		}
	}

}
