package application;

import java.time.LocalDateTime;
import java.util.Date;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndFeed;

public class Notizia {
	
	public String titolo;
	public String timestamp;
	public String descrizione;
	public String autore;
	public String fonte;
	public String link;
	public String categoria;
	
	
	public Notizia(String titolo,String timestamp,String descrizione,String autore, String fonte,String link,String categoria) {
		this.titolo=titolo;
		this.timestamp=timestamp;
		this.descrizione=descrizione;
		this.autore=autore;
		this.fonte=fonte;
		this.link=link;
		this.categoria=categoria;

		
	}
	public String getTitolo() {
		return titolo;
	}
	
	public String getTimestamp() {
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
	}
	public String getLink() {
		return link;
	}
	public String getCategoria() {
		return categoria;
	}
	@Override
	public String toString() {
		return "Notizia [titolo=" + titolo + ", timestamp=" + timestamp + ", descrizione=" + descrizione + ", autore="
				+ autore + ", fonte=" + fonte + ", link=" + link + "]";
	}

}
