package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class Main extends Application {
	private static Stage pagina;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	ArrayList<URL> listaURL = new ArrayList<URL>();
	

	
	@Override
	public void start(Stage primaryStage) {
		pagina = primaryStage;
		primaryStage.setResizable(false);

		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("LoginXML.fxml"));

			Scene scene = new Scene(root,1400,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			RSSReadAnsa();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void RSSReadAnsa() throws Exception {
		listaURL.clear();
		ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
		URL urlRandom = new URL("https://www.ansa.it/sito/ansait_rss.xml"); //notizie random
		URL urlTecnologia = new URL("http://feeds.feedburner.com/Androidiani"); //notizie sulla tecnologia
		URL urlPolitica = new URL("https://www.ilsole24ore.com/rss/mondo.xml "); //notizie sulla politica
		XmlReader readerRandom = new XmlReader(urlRandom);
		XmlReader readerTecnologia = new XmlReader(urlTecnologia);
		XmlReader readerPolitica = new XmlReader(urlPolitica);
		String[] urlJSON;
		/*
		try {
			JsonReader jr = new JsonReader(new FileReader("feedRSS.json"));
			 urlJSON = gson.fromJson(jr,String[].class);
			 for(int i=0; i<urlJSON.length;i++) {
				 URL url = new URL(urlJSON[i]);
				 listaURL.add(url);
			 }
		}catch(FileNotFoundException e) {
 	    	e.getMessage();
			
		}*/
		//Salvo delle news recenti generali
		try {
			SyndFeedInput inp = new SyndFeedInput();
			SyndFeed feed = inp.build(readerRandom);
			
			for(Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				Notizia notizia = new Notizia(entry.getTitle(),entry.getPublishedDate().toString(),entry.getDescription().getValue().toString(),
						entry.getAuthor(),entry.getSource().toString(),entry.getLink(),entry.getCategories().toString());
				listaNotizie.add(notizia);
			}
		}finally {
			if(readerRandom!=null) {
				readerRandom.close();
			}
		}
		
		//Salvo le news sulla tecnologia
		try {
			SyndFeedInput inp = new SyndFeedInput();
			SyndFeed feed = inp.build(readerTecnologia);
			
			for(Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				Notizia notizia = new Notizia(entry.getTitle(),entry.getPublishedDate().toString(),entry.getDescription().getValue().toString(),
						entry.getAuthor(),entry.getSource().toString(),entry.getLink(),entry.getCategories().toString());
				listaNotizie.add(notizia);
			}
			
		}finally {
			if(readerTecnologia!=null) {
				readerTecnologia.close();
			}
		}
		
		//Salvo le news sulla politica
		try {
			SyndFeedInput inp = new SyndFeedInput();
			SyndFeed feed = inp.build(readerPolitica);
			
			for(Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				Notizia notizia = new Notizia(entry.getTitle(),entry.getPublishedDate().toString(),entry.getDescription().getValue().toString(),
						entry.getAuthor(),entry.getSource().toString(),entry.getLink(),entry.getCategories().toString());
				listaNotizie.add(notizia);
			}	
		}finally {
			if(readerRandom!=null) {
				readerRandom.close();
			}
		}
		
		Notizia[] notizieFinal = listaNotizie.toArray(new Notizia[0]);
		FileWriter fw = new FileWriter("notizie.json");
		gson.toJson(notizieFinal,fw);
		fw.flush();
		fw.close();
	}
	
	
	public void cambiaPagina(String fxml) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource(fxml));
		pagina.getScene().setRoot(p);
	}
	
	public static void main(String[] args) {
		
		TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
           
            e.printStackTrace();
        }

		launch(args);
		/*
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String file = "admin.json";
		Admin admin = new Admin("admin","admin");
		
		*/

	}
}
