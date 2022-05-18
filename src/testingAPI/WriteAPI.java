package testingAPI;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;

import topologies.Netlist;
import topologies.Specifications;
import topologies.Topology;
import work.Component;
import work.Components;
import work.Result;

class WriteAPI {
	
	Result rs=new Result();
	
	File f;
	@TempDir
	Path tempDir;
	static Topology top1=null;

	static Topology top2;
	@BeforeAll
	static void Before_All() throws IOException{
		Components comps=new Components();
		Specifications specr=new Specifications("res1", 100, 10, 1000);
		Specifications specm=new Specifications("m1", 1.5, 1, 2);
		Map <String,String> rMap=new LinkedHashMap<String, String>();
		rMap.put("t1", "vdd");
		rMap.put("t2", "n1");
		Map <String,String> mMap=new LinkedHashMap<String, String>();
		mMap.put("drain", "n1");
		mMap.put("gate", "vin");
		mMap.put("source", "vss");
		Netlist netlr=new Netlist(rMap);
		Netlist netlm=new Netlist(mMap);
		Component res1=new Component("res1", "resistor", specr, netlr);
		Component m1=new Component("m1", "nmos", specm, netlm);
		comps.put(res1);
		comps.put(m1);
		top1=new Topology("top1", comps);
		top2=new Topology("top2", comps);
	}
	@Test
	void testWrite1top() throws IOException {
		Path tempFile = Files.createFile(tempDir.resolve("myfile.json"));
		
	    // Write something to it.
	    rs.write(top1);
	    rs.getFileWriter().close();
	    tempFile=rs.getF().toPath();
	    // Read it.
	    final String s = Files.readString(tempFile);
	 
	    assertEquals(top1.toString(), s);
     
        
	}
	@Test
	void testWrite2tops() throws IOException {
		Path tempFile = Files.createFile(tempDir.resolve("myfile.json"));
		
	    // Write something to it.
	    rs.write(top1);
	    rs.write(top2);
	    rs.getFileWriter().close();
	    tempFile=rs.getF().toPath();
		final String s = Files.readString(tempFile);
	 
	    assertEquals(top1.toString()+top2.toString(), s);
     
        
	}
	
	

}
