/**
 * 
 */
package fr.unice.i3s.wimmics;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import fr.unice.i3s.wimmics.rss.RssItem;
import fr.unice.i3s.wimmics.rss.Parser.SimpleRssParser;

/**
 * @author eamosse
 *
 */
public class SimpleRssParserTest {

	@Test
	public void test() {
		try {
			LinkedList<RssItem> items = SimpleRssParser.getItems("http://localhost:8080/RadioSociale-web/Embouteillage.rss");
			for(RssItem item : items){
				System.out.println("*************************************");
				System.out.println("Title : " + item.title);
				System.out.println("Description : " + item.description);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
