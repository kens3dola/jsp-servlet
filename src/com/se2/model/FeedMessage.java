package com.se2.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Represents one RSS message
 */
public class FeedMessage  implements Comparable<FeedMessage> {

    String title;
    String description;
    String link;
    String author;
    String pubDate;

    public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + pubDate
                + "]";
    }

	@Override
	public int compareTo(FeedMessage o) {
		SimpleDateFormat fomat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = fomat.parse(this.getPubDate());
			d2 = fomat.parse(o.getPubDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int x = d1.compareTo(d2);
		if(x==0)return 0;
		else if(x>0) return -1;
		else return 1;
	}

}