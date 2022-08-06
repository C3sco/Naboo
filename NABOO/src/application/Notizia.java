package application;

import java.time.LocalDateTime;
import java.util.Date;

public class Notizia {
	
	String titolo;
	Date timestamp;
	String descrizione;
	String autore;
	String fonte;
	String link;
	
	public Notizia() {
		
	}
	
	public Notizia(String titolo,Date timestamp,String descrizione,String autore,String fonte,String link) {
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
	}
	public String getLink() {
		return link;
	}
	public String getImmagine() {
		return immagine;
	}
	@Override
	public String toString() {
		return "Notizia [titolo=" + titolo + ", timestamp=" + timestamp + ", descrizione=" + descrizione + ", autore="
				+ autore + ", fonte=" + fonte + ", link=" + link + ", immagine=" + immagine + "]";
	}

}
