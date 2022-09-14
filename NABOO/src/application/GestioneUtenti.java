package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestioneUtenti{

	@FXML private TableView<Utente> tableView;
	@FXML private TableColumn<Utente, String> Nome;
	@FXML private TableColumn<Utente, String> Cognome;
	@FXML private TableColumn<Utente, String> Username;
	@FXML private TableColumn<Utente,String> Password;
	@FXML private TextField ModificaNome;
	@FXML private TextField ModificaCognome;
	@FXML private TextField ModificaUsername;
	@FXML private TextField ModificaPassword;

	//  public static Utente utenteModifica = new Utente("","","","");

	public ObservableList<Utente> listaUsers = FXCollections.observableArrayList();
	// ArrayList<Utente> jsonUsers = new ArrayList<Utente>();

	@FXML
	public void initialize() throws IOException {
		popolaLista();
		setTable();
	}

	public void setTable() {
		Nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		Cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		Username.setCellValueFactory(new PropertyValueFactory<>("username"));
		Password.setCellValueFactory(new PropertyValueFactory<>("password"));
		tableView.setItems(listaUsers);

	}
	public void popolaLista() {
		Utente[] utentiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			JsonReader jr = new JsonReader(new FileReader("users.json"));

			utentiJson = gson.fromJson(jr,Utente[].class);
			for(int i=0; i<utentiJson.length;i++) {
				Utente user = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername(),utentiJson[i].getPassword());
				listaUsers.add(user);
			}

		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	public void goBack() throws IOException {
		Main pagina = new Main();
		pagina.cambiaPagina("AdminPanel.fxml");

	}

	//metodo per prendere i dati dell'utente e portarli nei campi di testo, pronti per essere modificati
	public void modifyUser() throws IOException {
		ModificaNome.clear();
		ModificaCognome.clear();
		ModificaUsername.clear();
		ModificaPassword.clear();
		Utente user = tableView.getSelectionModel().getSelectedItem();
		/*
	    	utenteModifica.setNome(user.getNome());
	    	utenteModifica.setCognome(user.getCognome());
	    	utenteModifica.setUsername(user.getUsername());
	    	utenteModifica.setPassword(user.getPassword());
		 */
		ModificaNome.replaceText(0, 0, user.getNome());
		ModificaCognome.replaceText(0, 0, user.getCognome());
		ModificaUsername.replaceText(0, 0, user.getUsername());
		ModificaPassword.replaceText(0, 0, user.getPassword());
	}


	public void saveUser() throws IOException {
		Utente[] utentiJson;
		Commento[] commentiJson;
		Voto[] votiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String nome = ModificaNome.getText();
		String cognome = ModificaCognome.getText();
		String username = ModificaUsername.getText();
		String password = ModificaPassword.getText();
		//Utente user = new Utente(nome,cognome,username,password);
		Utente userSelected = tableView.getSelectionModel().getSelectedItem();

		try {
			JsonReader jr = new JsonReader(new FileReader("users.json"));
			utentiJson = gson.fromJson(jr,Utente[].class);
			ArrayList<Utente> lista = new ArrayList<Utente>();
			for(int i=0; i<utentiJson.length;i++) {
				if(utentiJson[i].getPassword().equals(userSelected.getPassword())) {
					utentiJson[i].setNome(nome);
					utentiJson[i].setCognome(cognome);
					utentiJson[i].setUsername(username);
					utentiJson[i].setPassword(password);
				}
				Utente tmpUser = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername(),utentiJson[i].getPassword());
				lista.add(tmpUser);
			}
			Utente[] userFinal = lista.toArray(new Utente[0]);
			FileWriter fw = new FileWriter("users.json");
			gson.toJson(userFinal,fw);
			fw.flush();
			fw.close();
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		//modifico l'username nei relativi commenti dell'utente
		try {
			JsonReader jr = new JsonReader(new FileReader("commenti.json"));
			commentiJson = gson.fromJson(jr,Commento[].class);
			ArrayList<Commento> lista = new ArrayList<Commento>();
			for(int i=0;i<commentiJson.length;i++) {
				if(commentiJson[i].getUsernameUtente().equals(userSelected.getUsername())) {
					commentiJson[i].setUsernameUtente(username);
				}
				lista.add(commentiJson[i]);
			}
			Commento[] commentiFinal = lista.toArray(new Commento[0]);
			FileWriter fw = new FileWriter("commenti.json");
			gson.toJson(commentiFinal,fw);
			fw.flush();
			fw.close();

		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		//modifico l'username nei relativi voti dell'utente
		try {
			JsonReader jr = new JsonReader(new FileReader("voti.json"));
			votiJson = gson.fromJson(jr,Voto[].class);
			ArrayList<Voto> lista = new ArrayList<Voto>();
			for(int i=0;i<votiJson.length;i++) {
				if(votiJson[i].getUsernameUtente().equals(userSelected.getUsername())) {
					votiJson[i].setUsernameUtente(username);
				}
				lista.add(votiJson[i]);
			}
			Voto[] votiFinal = lista.toArray(new Voto[0]);
			FileWriter fw = new FileWriter("voti.json");
			gson.toJson(votiFinal,fw);
			fw.flush();
			fw.close();

		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	/*
			try {
			}*/

	//metodo per eliminare un utente
	public void deleteUser() throws IOException {
		Utente[] utentiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ArrayList<Utente> lista = new ArrayList<>();
		JsonReader jr = new JsonReader(new FileReader("users.json"));
		utentiJson = gson.fromJson(jr,Utente[].class);
		for(int i=0; i<utentiJson.length;i++) {
			if(!utentiJson[i].getPassword().equalsIgnoreCase(tableView.getSelectionModel().getSelectedItem().getPassword())) {
				lista.add(utentiJson[i]);
			}
		}
		Utente[] userFinal = lista.toArray(new Utente[0]);
		FileWriter fw = new FileWriter("users.json");
		gson.toJson(userFinal,fw);
		fw.flush();
		fw.close();

	}

	//metodo per aggiungere un utente
	public void addUser() throws IOException {
		Utente[] utentiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ArrayList<Utente> lista = new ArrayList<>();
		JsonReader jr = new JsonReader(new FileReader("users.json"));
		utentiJson = gson.fromJson(jr,Utente[].class);
		for(int i=0; i<utentiJson.length;i++) {
			lista.add(utentiJson[i]);
		}
		String nome = ModificaNome.getText();
		String cognome = ModificaCognome.getText();
		String username = ModificaUsername.getText();
		String password = ModificaPassword.getText();
		Utente user = new Utente(nome,cognome,username,password);

		lista.add(user);

		Utente[] userFinal = lista.toArray(new Utente[0]);
		FileWriter fw = new FileWriter("users.json");
		gson.toJson(userFinal,fw);
		fw.flush();
		fw.close();

	}

	public void updateUsers() throws IOException {
		tableView.getItems().clear();
		popolaLista();
		setTable();
	}
}
