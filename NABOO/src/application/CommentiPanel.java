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

public class CommentiPanel {
	@FXML private TableView<Commento> tableCommenti;
	@FXML private TableColumn<Commento, String> Notizia;
	@FXML private TableColumn<Commento, String> Username;
	@FXML private TableColumn<Commento, String> Commento;

	public ObservableList<Commento> listaCommenti = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws IOException {
		popolaLista();
		setTable();
	}
	public void popolaLista() {
		Commento[] commentiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			JsonReader jr = new JsonReader(new FileReader("commenti.json"));
			commentiJson = gson.fromJson(jr,Commento[].class);
			for(int i=0; i<commentiJson.length;i++) {
				Commento commento = new Commento(commentiJson[i].getTitoloNotizia(),commentiJson[i].getLinkNotizia(),commentiJson[i].getUsernameUtente(),commentiJson[i].getCommento());
				listaCommenti.add(commento);
			}
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	public void setTable() {
		Notizia.setCellValueFactory(new PropertyValueFactory<>("titoloNotizia"));
		Username.setCellValueFactory(new PropertyValueFactory<>("usernameUtente"));
		Commento.setCellValueFactory(new PropertyValueFactory<>("commento"));
		tableCommenti.setItems(listaCommenti);
	}

	public void deleteCommento() throws IOException {
		Commento[] commentiJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ArrayList<Commento> lista = new ArrayList<>();
		JsonReader jr = new JsonReader(new FileReader("commenti.json"));
		commentiJson = gson.fromJson(jr,Commento[].class);
		for(int i=0; i<commentiJson.length;i++) {
			if(!commentiJson[i].equals(tableCommenti.getSelectionModel().getSelectedItem())){
				lista.add(commentiJson[i]);
			}
		}
		Commento[] commentiFinal = lista.toArray(new Commento[0]);
		FileWriter fw = new FileWriter("commenti.json");
		gson.toJson(commentiFinal,fw);
		fw.flush();
		fw.close();
	}
	public void updateTable() throws IOException {
		tableCommenti.getItems().clear();
		popolaLista();
		setTable();
	}

	public void goBack() throws IOException {
		Main pagina = new Main();
		pagina.cambiaPagina("AdminPanel.fxml");

	}

}
