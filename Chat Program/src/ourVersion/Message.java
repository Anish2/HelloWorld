package ourVersion;

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

}
