package fr.unice.i3s.wimmics.rss.Generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import fr.unice.i3s.wimmics.rss.Category;
import fr.unice.i3s.wimmics.rss.Cloud;
import fr.unice.i3s.wimmics.rss.EmailAddress;
import fr.unice.i3s.wimmics.rss.Enclosure;
import fr.unice.i3s.wimmics.rss.Guid;
import fr.unice.i3s.wimmics.rss.Image;
import fr.unice.i3s.wimmics.rss.Rss;
import fr.unice.i3s.wimmics.rss.RssChannel;
import fr.unice.i3s.wimmics.rss.RssItem;
import fr.unice.i3s.wimmics.rss.SkipDay;
import fr.unice.i3s.wimmics.rss.SkipHour;
import fr.unice.i3s.wimmics.rss.Source;
import fr.unice.i3s.wimmics.rss.TextInput;

public class SimpleRssGenerator implements ErrorHandler {
	static Element rootElement ;
	//Document document; 
	static String fileName; 
	public static void generate(Rss rss, String out) throws IllegalArgumentException, IllegalAccessException, IOException
	{
		fileName = out; 
		processRss(rss);
		processItem(rss.channel);
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		
		Document doc = rootElement.getDocument()==null ? new Document(rootElement) : rootElement.getDocument();
		xmlOutputter.output(doc, new FileOutputStream(out));
		
	}
	
	//On parse le fichier et on initialise la racine de notre arborescence
	static void lireFichier(String fichier) throws Exception
	{
	SAXBuilder sxb = new SAXBuilder();
	Document document = sxb.build(new File(fichier));
	Element racine = document.getRootElement();
	System.out.println(racine.toString());
	}
	
	static Document getDocument(File file){
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try {
			document = sxb.build(file);
			return document; 
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	private static void processRss(Rss rss) throws IllegalArgumentException, IllegalAccessException, IOException
	{
		File f = new File(fileName);
		if(f.createNewFile()){
		rootElement = new Element("rss");
		rootElement.setAttribute("version", rss.version);
		processRssChannel(rss.channel);
		}else{
			System.out.println("field already exist");
			Document doc = getDocument(f); 
			if(doc!=null)
			rootElement= doc.getRootElement(); 
		}
	}
	
	private static void processRssChannel(RssChannel rssChannel)
			throws IllegalArgumentException, IllegalAccessException {
		Element channelElement = new Element("channel");
		rootElement.addContent(channelElement);
		//System.out.println(rssChannel.getClass());
		Field[] fileds = rssChannel.getClass().getDeclaredFields();
		for (Field field : fileds) {

			Object o = field.get(rssChannel);
			Element e = new Element(field.getName());
			if (o != null) {

				if (field.getType() == EmailAddress.class) {

					String email = ((EmailAddress) o).getEmail();

					e.setText(email);

				} else if (field.getType() == Category.class) {

					String category = ((Category) o).category;

					e.setText(category);
					e.setAttribute("domain", ((Category) o).domain);

				} else if (field.getType() == Cloud.class) {

					Cloud c = ((Cloud) o);

					e.setAttribute("domain", c.domain);
					e.setAttribute("path", c.path);
					e.setAttribute("port", c.port);
					e.setAttribute("registerProcedure", c.registerProcedure);

				} else if (field.getType() == Image.class) {

					Image i = (Image) o;
					Field[] subFields = i.getClass().getDeclaredFields();
					for (Field field2 : subFields) {
						if (field2.get(i) != null) {
							Element ee = new Element(field2.getName());
							ee.setText(field2.get(i).toString());
							e.addContent(ee);
						}
					}

				} else if (field.getType() == TextInput.class) {

					TextInput i = (TextInput) o;

					Field[] subFields = i.getClass().getDeclaredFields();
					for (Field field2 : subFields) {
						if (field2.get(i) != null) {
							Element ee = new Element(field2.getName());
							ee.setText(field2.get(i).toString());
							e.addContent(ee);
						}
					}

				} else if (field.getType() == LinkedList.class) {

					LinkedList<?> list = ((LinkedList<?>) o);
					if (!list.isEmpty()) {
						if (list.get(0).getClass() == SkipHour.class) {

							for (Object object : list) {
								SkipHour skipHour = (SkipHour) object;
								Element ee = new Element("hour");
								ee.setText(String.valueOf(skipHour.hour));
								e.addContent(ee);
							}

						} else if (list.get(0).getClass() == SkipDay.class) {
							
							for (Object object : list) {
								SkipDay skipDay = (SkipDay) object;
								Element ee = new Element("day");
								ee.setText(skipDay.toString());
								e.addContent(ee);
							}

						} else if (list.get(0).getClass() == RssItem.class) {
							// do nothing here in order to keep the order
							break;
						}
					}

				} 
				else if(field.getType() ==  Date.class){
					  System.out.format("Task scheduled.%n");
				        SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss z");
				        formater.setTimeZone(TimeZone.getTimeZone("GMT"));
				        System.out.println(formater.format((Date)o));
				}
				else // String.class Date.class ...
				{
					e.setText(o.toString());
					
					//System.out.println("\t" + field.getName() + " : " + o.toString());
				}

				channelElement.addContent(e);
			}
		}



		//return rootElement;
	}
	
	private static void processItem(RssChannel rssChannel) throws IllegalArgumentException, IllegalAccessException{
//		// here we handle RssItem in order to keep the order
		Element channel = rootElement.getChild("channel");
		Field[] fileds = rssChannel.getClass().getDeclaredFields();
		for (Field field : fileds) {
			if (field.getType() == LinkedList.class) {
				Object o = field.get(rssChannel);
				//Element e = new Element(field.getName());
				
				if (o != null) {
					LinkedList<?> list = ((LinkedList<?>) o);
					if (list.size() > 0) {
						if (list.get(0).getClass() == RssItem.class) {
							while (!list.isEmpty()) {
								Element ee = processRssItem((RssItem) list
										.removeFirst());
								//e.addContent(ee);
								channel.addContent(ee);
							}
							
						}
					}
				}
			}
		}
	}

	private static Element processRssItem(RssItem rssItem)
			throws IllegalArgumentException, IllegalAccessException {
		Element rssItemElement = new Element("item");
		Field[] fields = rssItem.getClass().getDeclaredFields();

		for (Field field : fields) {
			Object o = field.get(rssItem);
			Element e = new Element(field.getName());
			if (o != null) {

				if (field.getType() == EmailAddress.class) {
					
					e.setText(((EmailAddress) o).getEmail());
					
				} else if (field.getType() == Category.class) {
					
					Category c = (Category) o;
					e.setText(c.category);
					e.setAttribute("domain", c.domain);
					
				} else if (field.getType() == Enclosure.class) {

					Enclosure en = (Enclosure) o;
					e.setText(en.enclosure);
					e.setAttribute("url", en.url);
					e.setAttribute("length", String.valueOf(en.length));
					e.setAttribute("type", en.type);
					
				} else if (field.getType() == Guid.class) {

					Guid guid = (Guid) o;
					e.setText(guid.guid);
					e.setAttribute("isPermaLink", String.valueOf(guid.isPermaLink));

				} else if (field.getType() == Source.class) {
					
					Source s = (Source) o;
					e.setText(s.source);
					e.setAttribute("url", s.url);
					
				} 
				else if(field.getType() ==  Date.class){
				        SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss z");
				        formater.setTimeZone(TimeZone.getTimeZone("GMT"));
				        System.out.println(formater.format((Date)o));
				        e.setText(formater.format((Date)o));
				}
				else // String.class Date.class ...
				{
					e.setText(o.toString());
				}

				rssItemElement.addContent(e);
			}
		}
		return rssItemElement;
	}

	public void warning(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void error(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
