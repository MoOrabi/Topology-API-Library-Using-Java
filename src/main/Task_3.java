package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XMLParserConfiguration;

import Topologies.Netlist;
import Topologies.Specifications;
import Topologies.Topology;
import Topologies.TopologyList;
import work.Component;
import work.Components;
import work.Result;
public class Task_3 {

	public static void main(String[] args) {
		Result rs=new Result();
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
		Topology top1=new Topology("top1", comps);
		File f=new File("C:\\GitRepos\\Task3API\\first.json");
		rs.write(top1, f);
//		TopologyList tl=rs.queryTopologies();
//		rs.deleteTopology(top1);
//		Components cs=rs.queryDevices(top1);
//		ArrayList<String> dnn=rs.queryDevicesWithNetListNode(top1, "t1");
		try {
			
			Topology top=rs.read(f);
			System.out.println(top.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
