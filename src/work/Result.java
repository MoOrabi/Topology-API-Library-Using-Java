package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Topologies.Netlist;
import Topologies.Specifications;
import Topologies.Topology;
import Topologies.TopologyList;

public class Result {
	TopologyList topL;
	private File f;
	public File getF() {
		return f;
	}
	public void setF(File f) {
		this.f = f;
	}
	FileWriter fileWriter;
	public Result () {
		topL=new TopologyList();
		f=new File("C:\\GitRepos\\Task3API\\first.json");
		try {
			fileWriter=new FileWriter(f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void write(Topology topology) {
		
		try {
			fileWriter.append(topology.toString());
			topL.add(topology);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public TopologyList queryTopologies(){
		System.out.println("Tobologies :");
		for (int i = 0; i < this.topL.size(); i++) {
			System.out.println(this.topL.topologyList.get(i).getId());
		}
		return this.topL;
	}
	public boolean deleteTopology (Topology top) {
		if(this.topL.delete(top)) {
			return true;
		}
		
		return false;
	}
	public Components queryDevices(Topology top) {
		Components comps=top.getComps();
		System.out.println("Devices in Topology "+top.getId());
		for (int i = 0; i < comps.size; i++) {
			System.out.println(comps.getComponents()[i].id);
		}
		return comps;
	}
	public ArrayList<String> queryDevicesWithNetListNode(Topology top,String Node) {
		ArrayList<String> dv=new ArrayList<String>();
		System.out.println("Devices for "+Node+" node in Topology"+top.getId());
		for (int i = 0; i < top.getComps().size; i++) {
			Map <String,String>m=((Netlist) top.getComps().getComponents()[i].netlist).getNetList();
			
			for (Map.Entry<String,String> entry : m.entrySet()) {
				if(entry.getKey().equals(Node)) {
					dv.add(entry.getValue());
					System.out.println(entry.getValue());
				}
			}
			
		}		
		
		return dv;
	}
	public Topology read(File file) throws IOException {
		Topology top=null;
		try {
			
			Components comps=new Components();
			
			top=new Topology(null, comps);
			
			FileReader fileReader=new FileReader(file);
			BufferedReader br=new BufferedReader(fileReader);
			String line;
			String args=null;
			while((line=br.readLine())!=null)  
			{
				if(line.startsWith("{")) {
					if((line=br.readLine())!=null) {
						top.setId(line.substring(line.indexOf(":")+3, line.lastIndexOf("\"")));
					}
					while((line=br.readLine())!=null&&!line.contains("]")) {
						if((line=br.readLine())!=null&&line.contains("{")) {
							Specifications spec=null;
							Netlist nl=null;
							line=br.readLine();
							String type=line.substring(line.indexOf(":")+3, line.lastIndexOf("\""));
							line=br.readLine();
							String id=line.substring(line.indexOf(":")+3, line.lastIndexOf("\""));
							if((line=br.readLine())!=null&&line.contains("{")) {
								String ids=line.substring(line.indexOf("\"")+1, line.lastIndexOf(":")-1);
								
								line=br.readLine();
								double defult=Double.parseDouble(line.substring(line.indexOf(":")+2, line.lastIndexOf(",")));
								line=br.readLine();
								double min=Double.parseDouble(line.substring(line.indexOf(":")+2, line.lastIndexOf(",")));
								line=br.readLine();
								double max=Double.parseDouble(line.substring(line.indexOf(":")+2, line.lastIndexOf("0")+1));
								spec=new Specifications(ids, defult, min, max);
								br.readLine();
							}
							if((line=br.readLine())!=null&&line.contains("{")) {
								
								Map<String,String> map=new HashMap<String, String>();
								while((line=br.readLine())!=null&&!line.contains("}")) {
									String ids=line.substring(line.indexOf("\"")+1, line.lastIndexOf(":")-1);
									String cont=line.substring(line.indexOf(":")+3, line.lastIndexOf("\""));
									map.put(ids, cont);
								}
								nl=new Netlist(map);
								
							}
							comps.put(new Component(type, id, spec, nl));
						
						}
					}
				}
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return top;
	}
}
