package fr.unice.i3s.wimmics.rss;

import java.util.Date;


public class RssItem {

	public String title;
	public String description;
	public String link;
	public EmailAddress author;
	public Category category;
	public String comments;
	public Enclosure enclosure;
	public Guid guid;
	public Date pubDate;
	public Source source;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public EmailAddress getAuthor() {
		return author;
	}
	public void setAuthor(EmailAddress author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Enclosure getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}
	public Guid getGuid() {
		return guid;
	}
	public void setGuid(Guid guid) {
		this.guid = guid;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
}
