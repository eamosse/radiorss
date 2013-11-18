package fr.unice.i3s.wimmics.rss;

public class Enclosure {

	/**
	 * textValue
	 */
	public String enclosure;
	/**
	 * attribute
	 */
	public String url;
	/**
	 * attribute
	 */
	public int length;
	/**
	 * attribute
	 */
	public String type;
	
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
