package fr.unice.i3s.wimmics;

import java.io.IOException;
import java.util.*;

import org.junit.*;

import fr.unice.i3s.wimmics.rss.*;
import fr.unice.i3s.wimmics.rss.Generator.SimpleRssGenerator;

public class SimpleRssGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private RssItem createRssItem()
	{
		RssItem item = new RssItem();
		
		EmailAddress haoji = new EmailAddress();
		haoji.email = "hao.ji@ericsson.com";
		item.author = haoji;
		
		Category category = new Category();
		category.category = "CategoryOne";
		category.domain = "domain String";
		item.category = category;
		
		item.comments = "This is comments for this item";
		item.description = "This is the description description";
		
		Enclosure en = new Enclosure();
		en.enclosure = "enClosure";
		en.length = 10;
		en.type = "Type of enclosure";
		en.url = "http://test.123.com";
		item.enclosure = en;
		
		Guid guid = new Guid();
		guid.guid = "GUID-STRING-TEST-HAOJI";
		guid.isPermaLink = true;
		item.guid = guid;

		item.link = "http://test.123.com";
		item.pubDate = new Date();
		item.title = "This is title for the item sdsdsdsdsdsdsdsdsd";
		
		return item;
	}
	
	private RssChannel createRssChannel()
	{
		RssChannel rssChannel = new RssChannel();

		rssChannel.setTitle("The title");
		rssChannel.setTtl(5);
		rssChannel.setCopyright("copyright");
		rssChannel.setDescription("description");
//		Cloud c = new Cloud();
//		c.domain = "domain";
//		c.path = "path";
//		c.port = "port";
//		c.protocol = CloudProtocol.soap;
//		c.registerProcedure = "registerProcedure";

//		rssChannel.setCloud(c);
//		rssChannel.skipDays = new LinkedList<SkipDay>();
//		rssChannel.skipDays.add(SkipDay.Friday);
//		rssChannel.skipDays.add(SkipDay.Saturday);

//		rssChannel.skipHours = new LinkedList<SkipHour>();
//		SkipHour sh = new SkipHour();
//		sh.hour = 5;
//		SkipHour sh2 = new SkipHour();
//		sh2.hour = 23;
//		rssChannel.skipHours.add(sh);
//		rssChannel.skipHours.add(sh2);
		
		rssChannel.itemList = new LinkedList<RssItem>();
		rssChannel.itemList.add(createRssItem());
		
		return rssChannel;
	}
	private Rss createRss()
	{
		Rss rss = new Rss();
		rss.channel = createRssChannel();
		return rss;
	}
	@Test
	public void testGenerate() {
		
		// Basicly this is a demo to show how to use this simple API to create an rss xml file 
		Rss rss = createRss();
		try {
			//SimpleRssGenerator.generate(rss, new FileOutputStream("rss.xml"));
			
			SimpleRssGenerator.generate(rss, "rss4.xml");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
