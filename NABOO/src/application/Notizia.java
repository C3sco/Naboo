package application;

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
		return "Titolo: " + titolo + ",\n" + "Timestamp: " + timestamp + ",\n" +  "Descrizione: " + descrizione + ",\n" + "Autore: "
				+ autore + ",\n" +  "Fonte: " + fonte + ",\n" + "Link: " + link + "]";
	}

}
