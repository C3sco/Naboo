package application;

public class Url {
	private String link;
	private String categoria;
	private String fonte;

	public Url(String link, String categoria, String fonte) {
		this.link=link;
		this.categoria=categoria;
		this.fonte=fonte;


	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}


}
