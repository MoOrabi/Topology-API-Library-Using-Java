package Topologies;


import work.Components;

public class Topology {
	
	String id="";
	private Components comps=null;
	public Topology(String id, Components comps) {
		this.id=id;
		this.setComps(comps);
	}
	@Override
	public String toString() {
		String s="{\n \"id\": \""+this.id+"\",\n "+this.getComps()+"\n}";
		return s;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Components getComps() {
		return comps;
	}
	public void setComps(Components comps) {
		this.comps = comps;
	}
}
