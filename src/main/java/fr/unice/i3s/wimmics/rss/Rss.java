package fr.unice.i3s.wimmics.rss;

public class Rss {

	public final String version = "2.0";
	public RssChannel channel;
	
	public RssChannel getChannel() {
		return channel;
	}
	public void setChannel(RssChannel channel) {
		this.channel = channel;
	}
	public String getVersion() {
		return version;
	}
	
}
