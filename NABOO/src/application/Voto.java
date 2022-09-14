package application;

public class Voto {
	private int numero;
	private String linkNotizia;
	private String usernameUtente;

	public Voto(int numero, String linkNotizia, String usernameUtente) {
		this.numero=numero;
		this.linkNotizia=linkNotizia;
		this.usernameUtente=usernameUtente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLinkNotizia() {
		return linkNotizia;
	}

	public void setLinkNotizia(String linkNotizia) {
		this.linkNotizia = linkNotizia;
	}

	public String getUsernameUtente() {
		return usernameUtente;
	}

	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

}
