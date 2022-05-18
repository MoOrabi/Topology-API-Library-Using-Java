package main;


import topologies.Netlist;
import topologies.Specifications;
import topologies.Topology;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import work.Component;
import work.Components;
import work.Result;

//The main class to test our API manually
public class Task3Main {
	public static void main(String[] args) throws IOException {
		Components comps = new Components();
		Specifications specr = new Specifications("res1", 100, 10, 1000);
		Specifications specm = new Specifications("m1", 1.5, 1, 2);
		Map <String,String> rMap = new LinkedHashMap<String, String>();
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
		Topology top2=new Topology("top2", comps);
		File f=new File("C:\\GitRepos\\Task3API\\first.json");
		Result rs = new Result();
		rs.write(top1);
		rs.write(top2);
		rs.getFileWriter().close();
//		TopologyList tl=rs.queryTopologies();
//		rs.deleteTopology(top1);
//		Components cs=rs.queryDevices(top1);
//		ArrayList<String> qdn=rs.queryDevicesWithNetListNode(top1, "t1");
		try {
			
			Topology top=rs.read(f);
			System.out.println(top.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
