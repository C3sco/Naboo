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

public class FeedPanel {
	@FXML private TableView<Url> tableFeed;
    @FXML private TableColumn<Url, String> Url;
    @FXML private TableColumn<Url, String> Categoria;
    @FXML private TableColumn<Url,String> Fonte;
    @FXML private TextField AddUrl;
    @FXML private TextField AddCategoria;
    @FXML private TextField AddFonte;
    
    public ObservableList<Url> listaUrl = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws IOException {
    	popolaLista();
    	setTable();
    }
    public void popolaLista() {
    	Url[] urlJson;
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	try {
 	    	JsonReader jr = new JsonReader(new FileReader("feedRSS.json"));
 	    	urlJson = gson.fromJson(jr,Url[].class);
 	    	for(int i=0; i<urlJson.length;i++) {
 	    		Url url = new Url(urlJson[i].getLink(),urlJson[i].getCategoria(),urlJson[i].getFonte());
 	    		listaUrl.add(url);
 	    	}
 	    }catch(FileNotFoundException e) {
 	    	e.getMessage();
 	    }
    }
    
    public void setTable() {
    	Url.setCellValueFactory(new PropertyValueFactory<>("link"));
    	Categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    	Fonte.setCellValueFactory(new PropertyValueFactory<>("fonte"));
    	tableFeed.setItems(listaUrl);
    }
    
    public void deleteFeed() throws IOException {
    	Url[] urlJson;
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    ArrayList<Url> lista = new ArrayList<Url>();
    	JsonReader jr = new JsonReader(new FileReader("feedRSS.json"));
    	urlJson = gson.fromJson(jr,Url[].class);
    	for(int i=0; i<urlJson.length;i++) {
    		if(!urlJson[i].getLink().equalsIgnoreCase(tableFeed.getSelectionModel().getSelectedItem().getLink())) {
    			lista.add(urlJson[i]);
    		}
    	}
    	Url[] urlFinal = lista.toArray(new Url[0]);
    	FileWriter fw = new FileWriter("feedRSS.json");
		gson.toJson(urlFinal,fw);
		fw.flush();
		fw.close();
    	
    }
    
    public void addFeed() throws IOException {
    	Url[] urlJson;
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    ArrayList<Url> lista = new ArrayList<Url>();
    	JsonReader jr = new JsonReader(new FileReader("feedRSS.json"));
    	urlJson = gson.fromJson(jr,Url[].class);
    	for(int i=0; i<urlJson.length;i++) {
    			lista.add(urlJson[i]);
    	}
    	String link = AddUrl.getText();
    	String categoria = AddCategoria.getText();
    	String fonte = AddFonte.getText();

    	Url url = new Url(link,categoria,fonte);
    	
    	lista.add(url);
    	
    	Url[] urlFinal = lista.toArray(new Url[0]);
    	FileWriter fw = new FileWriter("feedRSS.json");
		gson.toJson(urlFinal,fw);
		fw.flush();
		fw.close();
    	
    }
    
    public void updateFeed() throws IOException {
    	tableFeed.getItems().clear();
    	popolaLista();
    	setTable();
    }
    
    public void goBack() throws IOException {
    	Main pagina = new Main();
		pagina.cambiaPagina("AdminPanel.fxml");
		
    }

}
