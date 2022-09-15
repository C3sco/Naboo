package application;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login {

	@FXML private TextField userAdmin;
	@FXML private TextField pswAdmin;

	public void loginAdmin() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String user = userAdmin.getText();
		String psw = pswAdmin.getText();
		Admin[] adminJson;

		Main pagina = new Main();

		try {
			JsonReader jr = new JsonReader(new FileReader("admin.json"));
			adminJson = gson.fromJson(jr, Admin[].class);
			for(int i=0;i<adminJson.length;i++) {
				if(adminJson[i].getUsername().equals(user) && 
						adminJson[i].getPassword().equals(psw)) {
					pagina.cambiaPagina("AdminPanel.fxml");
				}
			}
		}catch(FileNotFoundException e) {
			e.getMessage();
		}

	}

}
