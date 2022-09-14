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
import javafx.scene.control.cell.PropertyValueFactory;

public class VotiPanel {
	public class CommentiPanel {
		@FXML private TableView<Voto> tableVoti;
	    @FXML private TableColumn<Voto, String> Voto;
	    @FXML private TableColumn<Voto, String> Link;
	    @FXML private TableColumn<Voto, String> Username;
	    
	    public ObservableList<Voto> listaVoti = FXCollections.observableArrayList();
	    
	    @FXML
	    public void initialize() throws IOException {
	    	popolaLista();
	    	setTable();
	    }
	    public void popolaLista() {
	    	Voto[] votiJson;
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    	try {
	 	    	JsonReader jr = new JsonReader(new FileReader("voti.json"));
	 	    	votiJson = gson.fromJson(jr,Voto[].class);
	 	    	for(int i=0; i<votiJson.length;i++) {
	 	    		Voto voto = new Voto(votiJson[i].getNumero(),votiJson[i].getLinkNotizia(),votiJson[i].getUsernameUtente());
	 	    		listaVoti.add(voto);
	 	    	}
	 	    }catch(FileNotFoundException e) {
	 	    	e.getMessage();
	 	    }
	    }
	    
	    public void setTable() {
	    	Voto.setCellValueFactory(new PropertyValueFactory<>("numero"));
	    	Link.setCellValueFactory(new PropertyValueFactory<>("linkNotizia"));
	    	Username.setCellValueFactory(new PropertyValueFactory<>("usernameUtente"));
	    	tableVoti.setItems(listaVoti);
	    }
	    /*
	    public void deleteCommento() throws IOException {
	    	Commento[] commentiJson;
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    
		    ArrayList<Commento> lista = new ArrayList<>();
	    	JsonReader jr = new JsonReader(new FileReader("users.json"));
	    	commentiJson = gson.fromJson(jr,Commento[].class);
	    	for(int i=0; i<commentiJson.length;i++) {
	    		if(!commentiJson[i].getCommento().equals(tableVoti.getSelectionModel().getSelectedItem().getCommento())) {
	    			lista.add(commentiJson[i]);
	    		}
	    	}
	    	Commento[] commentiFinal = lista.toArray(new Commento[0]);
	    	FileWriter fw = new FileWriter("commenti.json");
			gson.toJson(commentiFinal,fw);
			fw.flush();
			fw.close();
	    }*/
	    public void updateTable() throws IOException {
	    	tableVoti.getItems().clear();
	    	popolaLista();
	    	setTable();
	    }
	    
	    public void goBack() throws IOException {
	    	Main pagina = new Main();
			pagina.cambiaPagina("AdminPanel.fxml");
			
	    }
	}

}
