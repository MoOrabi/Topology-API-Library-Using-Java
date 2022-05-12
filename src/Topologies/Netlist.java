package Topologies;

import java.util.Map;

public class Netlist {
	Map<String,String> netList;
	public Map<String, String> getNetList() {
		return netList;
	}
	
	public Netlist(Map<String,String>netlist) {
		this.netList=netlist;
	}
	@Override
	public String toString() {
		int i =0;
		String s ="   \"netlist\": {\n";
		for (Map.Entry<String,String> entry : netList.entrySet()) {
            s+=("    \"" + entry.getKey() +"\": "+
                             "\"" + entry.getValue()+"\"");
            i++;
            if(i<netList.size()) {
            	s+=",\n";
            }
            else {
            	s+="\n";
            }
		}
		s+="   }";
		return s;
	}

}
