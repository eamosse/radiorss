package fr.unice.i3s.wimmics.rss;

import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * 
 * @author ehaojii
 *
 */
public class RssChannel {

	public  String title;
	public  String link;
	public  String description;
	public  String language;
	public  String copyright;
	public  EmailAddress managingEditor;
	public  EmailAddress webMaster;
	public  Date pubDate;
	public  Date lastBuildDate;
	public  Category category;
	public  String generator;
	public  String docs;
	public  Cloud cloud;
	public  int ttl;
	public  Image image;
	public  String rating;
	public  TextInput textInput;
	public  LinkedList<SkipHour> skipHours ;
	public  LinkedList<SkipDay> skipDays ;
	public  LinkedList<RssItem> itemList ;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public EmailAddress getManagingEditor() {
		return managingEditor;
	}
	public void setManagingEditor(EmailAddress managingEditor) {
		this.managingEditor = managingEditor;
	}
	public EmailAddress getWebMaster() {
		return webMaster;
	}
	public void setWebMaster(EmailAddress webMaster) {
		this.webMaster = webMaster;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Date getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public String getDocs() {
		return docs;
	}
	public void setDocs(String docs) {
		this.docs = docs;
	}
	public Cloud getCloud() {
		return cloud;
	}
	public void setCloud(Cloud cloud) {
		this.cloud = cloud;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public TextInput getTextInput() {
		return textInput;
	}
	public void setTextInput(TextInput textInput) {
		this.textInput = textInput;
	}
	public LinkedList<SkipHour> getSkipHours() {
		return skipHours;
	}
	public void setSkipHours(LinkedList<SkipHour> skipHours) {
		this.skipHours = skipHours;
	}
	public LinkedList<SkipDay> getSkipDays() {
		return skipDays;
	}
	public void setSkipDays(LinkedList<SkipDay> skipDays) {
		this.skipDays = skipDays;
	}
	public LinkedList<RssItem> getItemList() {
		return itemList;
	}
	public void setItemList(LinkedList<RssItem> itemList) {
		this.itemList = itemList;
	}
}
