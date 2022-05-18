package work;

import topologies.Netlist;
import topologies.Specifications;

public class Component {
	String id, type;
	Object specifications, netlist;

	public Component(String id, String type, Specifications spec, Netlist nl) {
		this.id = id;
		this.type = type;
		this.specifications = spec;
		this.netlist = nl;
	}

	@Override
	public String toString() {
		String s = "\n  {\n   \"type\": \"" + this.type + "\",\n" + "   \"id\": \"" + this.id + "\",\n"
				+ this.specifications.toString() + this.netlist.toString() + "\n  }";
		return s;
	}

	public Netlist getNetlist() {
		return (Netlist) netlist;
	}

	public void setNetlist(Object netlist) {
		this.netlist = netlist;
	}

}
