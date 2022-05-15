package work;

public class Components {
	private Component []components ;
	public Component[] getComponents() {
		Component []rComponent=new Component[Components.size];
		for (int i = 0; i < Components.size; i++) {
			rComponent[i]=components[i];
		}
		return rComponent;
	}

	public void setComponents(Component[] components) {
		this.components = components;
	}
	static int size;
	public Components() {
		components = new Component[1000];
		size=0;
	}
	
	public void put(Component toAdd) {
		components[size++]=toAdd;
	}
	@Override
	public String toString() {
		String s="\"Components\": [";
		for (int i = 0; i < this.size; i++) {
			s+=("  "+components[i]);
			if(i!=this.size-1) {
				s+=",";
			}
		}
		s+="\n ]";
		return s;
	}
}
