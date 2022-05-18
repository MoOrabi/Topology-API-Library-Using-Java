package topologies;



import java.util.ArrayList;



public class TopologyList {
	public ArrayList<Topology> topologyList;
	private int size;
	
	
	public TopologyList() {
		topologyList = new ArrayList<Topology>();
		size=0;
	}
	public void add (Topology top) {
		topologyList.add(top);
		size++;
	}
	public boolean delete (Topology top) {
		if(topologyList.remove(top)) {
			size--;
			return true;
		}
		return false;
	}
	public int size() {
		return size;
	}
}
