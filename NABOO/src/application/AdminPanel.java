package application;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPanel {
	
	@FXML private Button logout;
	
	public void aggiungiUtente() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Utente[] usersJson;
		String db = "users.json";
		LinkedList<Utente> utenti = new LinkedList<>();
		
		try {
			JsonReader read = new JsonReader(new FileReader(db));
			usersJson = gson.fromJson(read, Utente[].class);
			/*
			for(int i=0; i<usersJson.length;i++) {
				Utente user = new Utente(usersJson[i].getNome(), );
			}*/
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}
	
	public void logoutAdmin() throws IOException{
		
	}
	
	public void gestisciUtenti() throws IOException{
		Main pagina = new Main();
		pagina.cambiaPagina("Utenti.fxml");
	}

}
