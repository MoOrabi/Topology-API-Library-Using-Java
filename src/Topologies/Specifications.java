package topologies;


public class Specifications {
	double defult,min,max;
	String id;
	public Specifications(String id,double defult,double min,double max) {
		this.id=id;
		this.defult=defult;
		this.min=min;
		this.max=max;
	}
	@Override
	public String toString() {
		
		String s ="   \""+this.id+"\": {\n"+
					"    \"default\": "+this.defult+
					",\n    \"min\": "+this.min+
					",\n    \"max\": "+this.max+"\n   },\n";
		
		return s;
	}
}
