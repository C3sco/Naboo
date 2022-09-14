package application;

public class Commento {
	public String titoloNotizia;
	public String linkNotizia;
	public String usernameUtente;
	public String commento;

	public Commento (String titoloNotizia, String linkNotizia, String usernameUtente, String commento){
		this.titoloNotizia = titoloNotizia;
		this.linkNotizia = linkNotizia;
		this.usernameUtente = usernameUtente;
		this.commento = commento;
	}
	public String getTitoloNotizia() {
		return titoloNotizia;
	}
	public void setTitoloNotizia(String titoloNotizia) {
		this.titoloNotizia = titoloNotizia;
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
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}

}
