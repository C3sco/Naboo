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
		String inputF = "admin.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String user = userAdmin.getText();
		String psw = pswAdmin.getText();
		Admin admin;
		Main pagina = new Main();
		
		try {
			JsonReader read = new JsonReader(new FileReader(inputF));
			admin = gson.fromJson(read, Admin.class);
			if(admin.getUsername().equalsIgnoreCase(user) && 
					admin.getPassword().equalsIgnoreCase(psw)) {
				pagina.cambiaPagina("AdminPanel.fxml");
			}
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		
	}

}
