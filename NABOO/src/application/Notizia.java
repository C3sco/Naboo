package application;

import java.time.LocalDateTime;
import java.util.Date;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndFeed;

public class Notizia {
	
	public String titolo;
	public Date timestamp;
	public SyndContent descrizione;
	public String autore;
	public SyndFeed fonte;
	public String link;
	
	
	public Notizia(String titolo,Date timestamp,SyndContent descrizione,String autore,SyndFeed fonte,String link) {
		this.titolo=titolo;
		this.timestamp=timestamp;
		this.descrizione=descrizione;
		this.autore=autore;
		this.fonte=fonte;
		this.link=link;

		
	}
	public String getTitolo() {
		return titolo;
	}
	/*
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getAutore() {
		return autore;
	}
	public String getFonte() {
		return fonte;
	}*/
	public String getLink() {
		return link;
	}
	@Override
	public String toString() {
		return "Notizia [titolo=" + titolo + ", timestamp=" + timestamp + ", descrizione=" + descrizione + ", autore="
				+ autore + ", fonte=" + fonte + ", link=" + link + "]";
	}

}
