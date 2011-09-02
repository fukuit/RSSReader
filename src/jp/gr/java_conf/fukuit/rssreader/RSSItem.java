package jp.gr.java_conf.fukuit.rssreader;

public class RSSItem {
	private String title;
	private String link;
	private String description;
	private String date;

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public RSSItem() {
		title = "";
		link =  "";
		description = "";
		date =  "";	
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title).append(":").append(description);
		return sb.toString();
	}
}
