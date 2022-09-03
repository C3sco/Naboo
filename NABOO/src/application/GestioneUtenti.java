package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestioneUtenti{
	
	 	@FXML private TableView<Utente> tableView;
	    @FXML private TableColumn<Utente, String> Nome;
	    @FXML private TableColumn<Utente, String> Cognome;
	    @FXML private TableColumn<Utente, String> Username;
	    
	    Utente[] utentiJson;
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	   // public static List<Utente> listaUsers = new ArrayList<Utente>();
	    public ObservableList<Utente> listaUsers = FXCollections.observableArrayList();
	    /*
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	 
	    	Nome.setCellValueFactory(new PropertyValueFactory<Utente, String>("nome"));
	        Cognome.setCellValueFactory(new PropertyValueFactory<Utente, String>("cognome"));
	        Username.setCellValueFactory(new PropertyValueFactory<Utente, String>("username"));

	        tableView.getItems().setAll(listaUtenti());
	    }*/
	    @FXML
	    public void initialize() throws IOException {
	    	popolaLista();
	    	setTable();
	    }
	    
	    public void setTable() {
	    	Nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	Cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
	    	Username.setCellValueFactory(new PropertyValueFactory<>("username"));
	    	tableView.setItems(listaUsers);
	    	
	    }
	    public void popolaLista() {
	    	try {
	 	    	JsonReader jr = new JsonReader(new FileReader("users.json"));
	 	    	utentiJson = gson.fromJson(jr,Utente[].class);
	 	    	for(int i=0; i<utentiJson.length;i++) {
	 	    		Utente user = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername());
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
	    
	    public void modifyUser() throws IOException {
	    	
	    }
	    
	    public void deleteUser() throws IOException {
	    	
	    }
	    
	    public void updateUsers() throws IOException {
	    	tableView.getItems().clear();
	    	popolaLista();
	    	setTable();
	    }
	    /*
	    private List<Utente> listaUtenti(){
	    	try {
	 	    	JsonReader jr = new JsonReader(new FileReader("utenti.json"));
	 	    	utentiJson = gson.fromJson(jr,Utente[].class);
	 	    	for(int i=0; i<utentiJson.length;i++) {
	 	    		Utente user = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername());
	 	    		listaUsers.add(user);
	 	    	}
	 	    	
	 	    }catch(FileNotFoundException e) {
	 	    	e.getMessage();
	 	    }
	    	return listaUsers;
	        // parse and construct User datamodel list by looping your ResultSet rs
	        // and return the list   
	    }*/
}
