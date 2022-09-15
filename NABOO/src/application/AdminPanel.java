package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPanel {

	@FXML private Button logout;

	public void gestisciCommenti() throws IOException{
		Main pagina = new Main();
		pagina.cambiaPagina("Commenti.fxml");
	}

	public void logoutAdmin() throws IOException{
		Main pagina = new Main();
		pagina.cambiaPagina("LoginXML.fxml");
	}

	public void gestisciUtenti() throws IOException{
		Main pagina = new Main();
		pagina.cambiaPagina("Utenti.fxml");
	}

	public void gestisciNotizie() throws IOException{
		Main pagina = new Main();
		pagina.cambiaPagina("Notizie.fxml");
	}
	public void newsCollector() throws Exception {
		Main m = new Main();
		m.RSSReader();
	}
	public void gestisciFeed() throws Exception{
		Main pagina = new Main();
		pagina.cambiaPagina("FeedRSS.fxml");
	}
	public void gestisciVoti() throws Exception{
		Main pagina = new Main();
		pagina.cambiaPagina("Voti.fxml");
	}


}
