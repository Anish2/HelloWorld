package ourVersion;

import java.util.Arrays;

public class Message {

	private int type;
	private String[] data;

	public Message(int type, String[] data) {
		this.type = type;
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public String[] getData() {
		return data;
	}

	public boolean equals(Object other) {
		if (! (other instanceof Message)) {
			return false;
		}
		return equals((Message)other);
	}

	private boolean equals(Message m) {
		return type == m.getType() && Arrays.equals(m.getData(), data);
	}

	public int hashCode() {
		final int PRIME = 19;

		int h1 = (new Integer(type)).hashCode();
		int h2 = data.hashCode();

		int h = 0;

		h = PRIME*h1 + h2;
		
		return h;
	}

}
