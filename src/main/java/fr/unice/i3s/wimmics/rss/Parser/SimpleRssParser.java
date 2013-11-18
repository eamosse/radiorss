package fr.unice.i3s.wimmics.rss.Parser;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHandler.STATE;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;

import fr.unice.i3s.wimmics.rss.RssItem;

public class SimpleRssParser {
	static Element racine;
	static Element channel;
	static String url;

	// On parse le fichier et on initialise la racine de notre arborescence
	static LinkedList<RssItem>  processRss(File file) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(file);
		racine = document.getRootElement();
		channel = racine.getChild("channel");
		List<Element> children = channel.getChildren("item");
		LinkedList<RssItem> items = new LinkedList<RssItem>();
		for(Element element : children){
			Element description = element.getChild("description"); 
			Element title = element.getChild("title"); 
			System.out.println(element.getAttributeValue("description"));
			RssItem item = new RssItem(); 
			item.setDescription(description.getText()); 
			item.setTitle(title.getText()); 
			items.add(item); 
		}
		return items; 
		//System.out.println(racine.toString());
	}

	public static LinkedList<RssItem> getItems(String url) throws Exception {
		LinkedList<RssItem> items = new LinkedList<RssItem>();
		// getChannel(filename);
		AsyncHttpClient c = new AsyncHttpClient();
		Future<String> f = c.prepareGet(url).execute(
				new AsyncHandler<String>() {
					private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

					public STATE onStatusReceived(HttpResponseStatus status)
							throws Exception {
						int statusCode = status.getStatusCode();
						// The Status have been read
						// If you don't want to read the headers,body or stop
						// processing the response
						System.out.println("onStatusReceived " + statusCode);
						if (statusCode >= 500) {
							return STATE.ABORT;
						}

						return STATE.CONTINUE;
					}

					public STATE onHeadersReceived(HttpResponseHeaders h)
							throws Exception {
						FluentCaseInsensitiveStringsMap headers = h
								.getHeaders();
						// The headers have been read
						// If you don't want to read the body, or stop
						// processing the response
						System.out.println("onHeadersReceived "
								+ headers.size());
						return STATE.CONTINUE;
					}

					public STATE onBodyPartReceived(
							HttpResponseBodyPart bodyPart) throws Exception {
						bytes.write(bodyPart.getBodyPartBytes());
						System.out.println("onBodyPartReceived");
						return STATE.CONTINUE;
					}

					public String onCompleted() throws Exception {
						// Will be invoked once the response has been fully read
						// or a ResponseComplete exception
						// has been thrown.
						// NOTE: should probably use Content-Encoding from
						// headers
						String response = bytes.toString("UTF-8");
						System.out.println(response);

						return response;
					}

					public void onThrowable(Throwable t) {
						System.out.println("onThrowable");
					}
				});

		String bodyResponse = f.get();
		System.out.println("Body Response" + bodyResponse);
		File file = processResponse(bodyResponse); 
		items = processRss(file);
		return items;
	}

	private static File processResponse(String response) {
		File temp=null;
		try {
			temp = File.createTempFile("temp_rss", ".tmp");

			FileWriter fstream = new FileWriter(temp);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(response);
			// Close the output stream
			out.close();
			System.out.println("Temp file : " + temp.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp; 
	}

}
