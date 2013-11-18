package fr.unice.i3s.wimmics.rss;

public class Guid {

	/**
	 * textValue
	 */
	public String guid;
	
	/**
	 * attribute
	 */
	public boolean isPermaLink = true;
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isPermaLink() {
		return isPermaLink;
	}

	public void setPermaLink(boolean isPermaLink) {
		this.isPermaLink = isPermaLink;
	}
}
