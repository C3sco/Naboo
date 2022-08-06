package application;

import java.time.LocalDateTime;

public class Notizia {
	
	String titolo;
	LocalDateTime timestamp;
	String descrizione;
	String autore;
	String fonte;
	String link;
	String immagine;
	
	public Notizia() {
		
	}
	
	public Notizia(String titolo,LocalDateTime timestamp,String descrizione,String autore,String fonte,String link,String immagine) {
		this.titolo=titolo;
		this.timestamp=timestamp;
		this.descrizione=descrizione;
		this.autore=autore;
		this.fonte=fonte;
		this.link=link;
		this.immagine=immagine;
		
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
