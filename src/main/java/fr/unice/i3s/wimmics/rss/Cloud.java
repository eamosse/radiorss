package fr.unice.i3s.wimmics.rss;

public class Cloud {
	public String domain;
	public String port;
	public String path;
	public String registerProcedure;
	public CloudProtocol protocol;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRegisterProcedure() {
		return registerProcedure;
	}
	public void setRegisterProcedure(String registerProcedure) {
		this.registerProcedure = registerProcedure;
	}
	public String getProtocol() {
		if (protocol == CloudProtocol.http_post)
		{
			return "http-post";
		}
		else if (protocol == CloudProtocol.xml_rpc)
		{
			return "xml-rpc";
		}
		else 
		{
			return "soap";
		}		
	}
	public void setProtocol(CloudProtocol protocol) {
		this.protocol = protocol;
	}
	

}
