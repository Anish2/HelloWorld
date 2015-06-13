package tsunox_code;

import fisica.FBox;

/**
 * BoxWrapper - Extension of FBox
 * 
 * Created because FBox does not provide usable equals and hashCode methods for structures such as HashSet
 * 
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class BoxWrapper extends FBox {	

	/**
	 * Takes same arguments as super class with added name parameter as an identifier.
	 * @param width - width of box
	 * @param height - height of box
	 * @param name - identifier of box
	 */
	public BoxWrapper(float width, float height, String name) {
		super(width, height);
		this.setName(name);
	}

	/**
	 * Compares for equality based on name of boxes.
	 */
	public boolean equals(Object o) {
		if (o instanceof BoxWrapper) {
			BoxWrapper f = (BoxWrapper)o;
			return f.getName().equals(this.getName());
		}
		else
			return false;
	}

	/**
	 * Returns hashCode of string name identifier.
	 */
	public int hashCode() {
		return this.getName().hashCode();	
	}
}
