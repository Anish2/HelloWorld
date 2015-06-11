package browBalance;

import fisica.FBox;

public class BoxWrapper extends FBox {	

	public BoxWrapper(float arg0, float arg1) {
		super(arg0, arg1);
	}

	public boolean equals(Object o) {
		if (o instanceof FBox) {
			FBox f = (FBox)o;
			return o == this;
		}
		else
			return false;
	}

	public int hashCode() {
		int hashCode = 1;
		hashCode = 31 * hashCode + (int) this.getX();
		hashCode = 31 * hashCode + (int) this.getY();
		return hashCode;
	}



}
