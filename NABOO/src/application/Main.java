package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;



public class Main extends Application {
	private static Stage pagina;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);

		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("LoginXML.fxml"));

			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			RSSRead();
				
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void RSSRead() throws Exception{
		
		URL url = new URL("https://www.ansa.it/sito/ansait_rss.xml");
		XmlReader reader = null;
		ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			reader = new XmlReader(url);
			SyndFeedInput inp = new SyndFeedInput();
			SyndFeed feed = inp.build(reader);
			
			for(Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				Notizia notizia = new Notizia(entry.getTitle(),entry.getUpdatedDate(),entry.getDescription(),entry.getAuthor()
						,entry.getSource(),entry.getLink());
				listaNotizie.add(notizia);
			}
			
			FileWriter write = new FileWriter("notizie.json");
			gson.toJson(listaNotizie,write);
			write.flush();
			write.close();
			
			
		} finally {
			if(reader!=null) {
				reader.close();
			}
		}
	}
	
	public void cambiaPagina(String fxml) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource(fxml));
		pagina.getScene().setRoot(p);
	}
	
	public static void main(String[] args) {
		launch(args);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String file = "admin.json";
		Admin admin = new Admin("admin","admin");
		
		try {
			FileWriter fw = new FileWriter(file);
			gson.toJson(admin,fw);
			fw.flush();
			fw.close();
		}catch(JsonIOException | IOException e) {
			((Throwable) e).getMessage();
		}

	}
}
