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

public class NotiziePanel {
	@FXML private TableView<Notizia> tableNotizie;
	@FXML private TableColumn<Notizia, String> Titolo;
	@FXML private TableColumn<Notizia, String> Timestamp;
	@FXML private TableColumn<Notizia, String> Descrizione;
	@FXML private TableColumn<Notizia, String> Autore;
	@FXML private TableColumn<Notizia, String> Fonte;
	@FXML private TableColumn<Notizia, String> Link;
	@FXML private TableColumn<Notizia, String> Categoria;

	public ObservableList<Notizia> listaNotizie = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws IOException {
		popolaLista();
		setTable();
	}

	public void popolaLista() {
		Notizia[] notizieJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			JsonReader jr = new JsonReader(new FileReader("notizie.json"));
			notizieJson = gson.fromJson(jr,Notizia[].class);
			for(int i=0; i<notizieJson.length;i++) {
				Notizia notizia = new Notizia(notizieJson[i].getTitolo(),notizieJson[i].getTimestamp(),notizieJson[i].getDescrizione(),notizieJson[i].getAutore(),
						notizieJson[i].getFonte(),notizieJson[i].getLink(),notizieJson[i].getCategoria());
				listaNotizie.add(notizia);
			}
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	public void setTable() {
		Titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		Timestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
		Descrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
		Autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
		Fonte.setCellValueFactory(new PropertyValueFactory<>("fonte"));
		Link.setCellValueFactory(new PropertyValueFactory<>("link"));
		Categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

		tableNotizie.setItems(listaNotizie);
	}

	public void deleteNotizia() throws IOException{
		Notizia[] notizieJson;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ArrayList<Notizia> lista = new ArrayList<Notizia>();
		JsonReader jr = new JsonReader(new FileReader("notizie.json"));
		notizieJson = gson.fromJson(jr,Notizia[].class);
		for(int i=0; i<notizieJson.length;i++) {
			if(!notizieJson[i].getLink().equalsIgnoreCase(tableNotizie.getSelectionModel().getSelectedItem().getLink())) {
				lista.add(notizieJson[i]);
			}
		}
		Notizia[] userFinal = lista.toArray(new Notizia[0]);
		FileWriter fw = new FileWriter("notizie.json");
		gson.toJson(userFinal,fw);
		fw.flush();
		fw.close();

	}

	public void updateNotizie() throws IOException {
		tableNotizie.getItems().clear();
		popolaLista();
		setTable();
	}

	public void goBack() throws IOException {
		Main pagina = new Main();
		pagina.cambiaPagina("AdminPanel.fxml");

	}

}
