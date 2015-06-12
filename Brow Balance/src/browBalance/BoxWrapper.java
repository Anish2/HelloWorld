package browBalance;

import fisica.FBox;

public class BoxWrapper extends FBox {	

	public BoxWrapper(float arg0, float arg1, String name) {
		super(arg0, arg1);
		this.setName(name);
	}

	public boolean equals(Object o) {
		if (o instanceof BoxWrapper) {
			BoxWrapper f = (BoxWrapper)o;
			return f.getName().equals(this.getName());
		}
		else
			return false;
	}

	public int hashCode() {
		return this.getName().hashCode();	
	}



}
