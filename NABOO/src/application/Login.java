package application;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class Login {
	@FXML private TextField usernameText;
	@FXML private TextField passwordText;
	
	public void login() throws IOException{
		String inputF = "admin.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String user = usernameText.getText();
		String psw = passwordText.getText();
		Admin admin;
		
		try {
			JsonReader read = new JsonReader(new FileReader(inputF));
			admin = gson.fromJson(read, Admin.class);
			if(admin.getUsername().equalsIgnoreCase(user) && 
					admin.getPassword().equalsIgnoreCase(psw)) {
				/*
				 * inserire pagina pannello admin
				 * 
				 */
			}
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		
	}

}
