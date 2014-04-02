package fr.inria.oak.paxquery.xparser.test;


import static org.junit.Assert.*;

//import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.inria.oak.paxquery.xparser.XQueryMain;

@RunWith(Parameterized.class)
public class TestXQueryMain 
{
	public String query;
	
	public TestXQueryMain(String query)
	{
		this.query = query;
	}
	
	@Parameters 
	public static Collection<String[]> dataSets() throws SAXException, IOException, ParserConfigurationException 
	{
		ArrayList<String[]> testCases = new ArrayList<String[]>();
		
		String testCaseFilePath = "./testcases/queryx1-tests.xml";
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse (new File(testCaseFilePath));
        
        // normalize text representation
        doc.getDocumentElement().normalize();
        System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
		
        NodeList nodeList = doc.getElementsByTagName("test");
        System.out.println("Number of tests: " + nodeList.getLength());

        for(int i=0; i<nodeList.getLength(); i++)
        	testCases.add(new String[] {nodeList.item(i).getAttributes().getNamedItem("value").getNodeValue()});
                
        return testCases;        
	}

	@Test
	public void test() 
	{
		assertEquals(true, XQueryMain.test_main(query));
	}

}
