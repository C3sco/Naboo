package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestioneUtenti{
	
	 	@FXML private TableView<Utente> tableView;
	    @FXML private TableColumn<Utente, String> Nome;
	    @FXML private TableColumn<Utente, String> Cognome;
	    @FXML private TableColumn<Utente, String> Username;
	    @FXML private TextField ModificaNome;
	    @FXML private TextField ModificaCognome;
	    @FXML private TextField ModificaUsername;
	    
	    public static Utente utenteModifica = new Utente("","","");
	    
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
	    	tableView.setItems(listaUsers);
	    	
	    }
	    public void popolaLista() {
	    	Utente[] utentiJson;
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
	    	ModificaNome.clear();
	    	ModificaCognome.clear();
	    	ModificaUsername.clear();
	    	Utente user = tableView.getSelectionModel().getSelectedItem();
	    	utenteModifica.setNome(user.getNome());
	    	utenteModifica.setCognome(user.getCognome());
	    	utenteModifica.setUsername(user.getUsername());
	    	ModificaNome.replaceText(0, 0, user.getNome());
	    	ModificaCognome.replaceText(0, 0, user.getCognome());
	    	ModificaUsername.replaceText(0, 0, user.getUsername());
	    }
	    
	    public void saveUser() throws IOException {
	    	Utente[] utentiJson;
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    	
	    	String nome = ModificaNome.getText();
	    	String cognome = ModificaCognome.getText();
	    	String username = ModificaUsername.getText();
	    	Utente user = new Utente(nome,cognome,username);
	    	Utente userSelected = tableView.getSelectionModel().getSelectedItem();
	    	
	    	JsonReader jr = new JsonReader(new FileReader("users.json"));
	    	utentiJson = gson.fromJson(jr,Utente[].class);
	    	ArrayList<Utente> lista = new ArrayList<>();
	    	for(int i=0; i<utentiJson.length;i++) {
	    		if(!utentiJson[i].getUsername().equals(userSelected.getUsername())) {
	    		Utente tmpUser = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername());
	    			lista.add(tmpUser);
	    		}
	    	}
	    	/*
	    	lista.remove(userSelected);
	    	for(int i=0;i<lista.size();i++) {
	    		if(lista.get(i).getUsername()==userSelected.getUsername()) {
	    			lista.remove(i);
	    		}
	    	}*/

	    	lista.add(user);
	    	/*
	    	lista.add(user);
	    	lista.remove(utenteModifica);*/
	    	Utente[] userFinal = lista.toArray(new Utente[0]);
	    	
	    	FileWriter fw = new FileWriter("users.json");
			gson.toJson(userFinal,fw);
			fw.flush();
			fw.close();
	    }
	    
	    public void deleteUser() throws IOException {
	    	Utente[] utentiJson;
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    
		    ArrayList<Utente> lista = new ArrayList<>();
	    	JsonReader jr = new JsonReader(new FileReader("users.json"));
	    	utentiJson = gson.fromJson(jr,Utente[].class);
	    	for(int i=0; i<utentiJson.length;i++) {
	    		if(!utentiJson[i].getUsername().equals(tableView.getSelectionModel().getSelectedItem().getUsername())) {
	    			lista.add(utentiJson[i]);
	    		}
	    	}
	    	Utente[] userFinal = lista.toArray(new Utente[0]);
	    	FileWriter fw = new FileWriter("users.json");
			gson.toJson(userFinal,fw);
			fw.flush();
			fw.close();
	    	
	    }
	    
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
	    	Utente user = new Utente(nome,cognome,username);
	    	
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
