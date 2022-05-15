package APITesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import Topologies.Netlist;
import Topologies.Specifications;
import Topologies.Topology;
import work.Component;
import work.Components;
import work.Result;

class QueryDevicesWithNetlistNode {
	static Result rs=new Result();
	File f;
	@TempDir
	Path tempDir;
	static Topology top1;
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
		rs.write(top1);
		rs.write(top2);
	}
	@Test
	void testQueryDevicesWithNetListNode() {
		Map<String, String>map1=top1.getComps().getComponents()[0].getNetlist().getNetList();
		ArrayList<String> outA=new ArrayList<String>();
		
		for (Map.Entry<String,String> entry : map1.entrySet()) {
			
			if(entry.getKey().equals("t1")) {
				outA.add(entry.getValue());
			}
		}
		
		assertIterableEquals(outA, rs.queryDevicesWithNetListNode(top1, "t1"));
	}
	@Test
	void testQueryDevicesWithNetListNode2() {
		Map<String, String>map2=top1.getComps().getComponents()[1].getNetlist().getNetList();
		ArrayList<String> outB=new ArrayList<String>();
		for (Map.Entry<String,String> entry : map2.entrySet()) {
			
			if(entry.getKey().equals("drain")) {
				outB.add(entry.getValue());
			}
		}
		assertIterableEquals(outB, rs.queryDevicesWithNetListNode(top1, "drain"));
	}
}