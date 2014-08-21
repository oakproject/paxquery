package fr.inria.oak.paxquery.pact.io;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Test;

import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePattern;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;



public class TestXMLConsTreePatternOutputFormat {
	
	RecordList recordList;
	NestedMetadata signature;
	AtomicBoolean[] nullResults;
	
	
	@Before 
	public void init() {
		//Init record list
		this.recordList = new RecordList();
		Record record1 = new Record();
		record1.addField(new StringValue("<name>Martin</name>"));
		RecordList nestedListRecords1 = new RecordList();
		Record nestedRecord1List1 = new Record();
		nestedRecord1List1.addField(new StringValue("<zip>75</zip>"));
		nestedRecord1List1.addField(new StringValue("<city>Paris</city>"));
		nestedListRecords1.add(nestedRecord1List1);
		record1.addField(nestedListRecords1);
		//
		Record record2 = new Record();
		record2.addField(new StringValue("<name>Bernard</name>"));
		RecordList nestedListRecords2 = new RecordList();
		Record nestedRecord1List2 = new Record();
		nestedRecord1List2.addField(new StringValue("<zip>69</zip>"));
		nestedRecord1List2.addField(new StringValue("<city>Lyon</city>"));
		nestedListRecords2.add(nestedRecord1List2);	
		Record nestedRecord2List2 = new Record();
		nestedRecord2List2.addField(new StringValue("<zip>75</zip>"));
		nestedRecord2List2.addField(new StringValue("<city>Paris</city>"));
		nestedListRecords2.add(nestedRecord2List2);	
		record2.addField(nestedListRecords2);
		//
		Record record3 = new Record();
		record3.addField(new StringValue("<name>Dubois</name>"));
		RecordList nestedListRecords3 = new RecordList();
		Record nestedRecord1List3 = new Record();
		nestedRecord1List3.addField(new StringValue("\0"));
		nestedRecord1List3.addField(new StringValue("\0"));
		nestedListRecords3.add(nestedRecord1List3);
		record3.addField(nestedListRecords3);
		//
		this.recordList.add(record1);
		this.recordList.add(record2);
		this.recordList.add(record3);
		
		//Init records signature
		MetadataTypes[] nestedTypes = new MetadataTypes[] {
			MetadataTypes.STRING_TYPE,
			MetadataTypes.STRING_TYPE
		};
		NestedMetadata nestedSignature = new NestedMetadata(2, nestedTypes);
		NestedMetadata[] newNestedMetadata = new NestedMetadata[] { nestedSignature };
		MetadataTypes[] types = new MetadataTypes[] {
			MetadataTypes.STRING_TYPE,
			MetadataTypes.TUPLE_TYPE
		};
 		this.signature = new NestedMetadata(types, newNestedMetadata);
		
		//Init null results
		this.nullResults = new AtomicBoolean[] {new AtomicBoolean()};
	}
	
	@Test
	public void testCtp1() throws Exception {
		ConstructionTreePatternNode root = new ConstructionTreePatternNode(ContentType.ELEMENT, "person", false);
		ConstructionTreePattern ctp1 = new ConstructionTreePattern(root);
		List<Integer> child1Path = new ArrayList<Integer>();
		child1Path.add(0);
		ConstructionTreePatternNode child1 = new ConstructionTreePatternNode(ctp1, ContentType.VARIABLE_PATH, child1Path, false);
		ctp1.addChild(root, child1);
		List<Integer> child2Path = new ArrayList<Integer>();
		child2Path.add(1);
		child2Path.add(0);
		ConstructionTreePatternNode child2 = new ConstructionTreePatternNode(ctp1, ContentType.VARIABLE_PATH, child2Path, false);
		ctp1.addChild(root, child2);
		List<Integer> child3Path = new ArrayList<Integer>();
		child3Path.add(1);
		child3Path.add(1);
		ConstructionTreePatternNode child3 = new ConstructionTreePatternNode(ctp1, ContentType.VARIABLE_PATH, child3Path, false);
		ctp1.addChild(root, child3);	
				
		String result1 = this.getConstructionResult(
				this.recordList,
				this.signature, 
				new ConstructionTreePattern[] {ctp1}, 
				this.nullResults);
		
		System.out.println(result1);
	}

	private String getConstructionResult(RecordList recordList, NestedMetadata signature, ConstructionTreePattern[] ctps, AtomicBoolean[] nullResults) throws Exception {
		Method method = XmlConsTreePatternOutputFormat.class.getDeclaredMethod(
				"writeRecord",
				RecordList.class,
				NestedMetadata.class,
				ConstructionTreePattern[].class,
				AtomicBoolean[].class);
		method.setAccessible(true);
		StringBuilder[] results = (StringBuilder[]) method.invoke(
				XmlConsTreePatternOutputFormat.class.newInstance(),
				recordList,
				signature,
				ctps,
				nullResults);
		return results[0].toString();
	}

}
