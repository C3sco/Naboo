package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	ArrayList<Url> listaUrl = new ArrayList<Url>();
	ArrayList<Admin> listaAdmin = new ArrayList<Admin>();
	

	@Override
	public void start(Stage primaryStage) {
		pagina = primaryStage;
		primaryStage.setResizable(false);

		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("LoginXML.fxml"));

			Scene scene = new Scene(root,1300,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			RSSReader();
			istanziaUrl();
			istanziaAdmin();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//metodo per istanziare un utente admin
	public void istanziaAdmin() throws Exception {
		listaAdmin.clear();
		Admin admin = new Admin("admin","admin");
		Admin[] adminJson;
		int presente = 0;
		try {
			JsonReader jr = new JsonReader(new FileReader("admin.json"));
			adminJson = gson.fromJson(jr,Admin[].class);
			for(int i=0; i<adminJson.length;i++) {
				Admin ad = new Admin(adminJson[i].getUsername(),adminJson[i].getPassword());
				listaAdmin.add(ad);
				if(adminJson[i].getUsername().equals("admin")){
					presente=1;
				}
			}
			if(presente==0) {
				listaAdmin.add(admin);
			}
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		
		Admin[] adminFinal = listaAdmin.toArray(new Admin[0]);
		FileWriter fw = new FileWriter("admin.json");
		gson.toJson(adminFinal,fw);
		fw.flush();
		fw.close();
	}

	//metodo per istanziare degli url di base
	public void istanziaUrl() throws Exception {
		listaUrl.clear();
		Url random = new Url("https://www.ansa.it/sito/ansait_rss.xml","random","ansa");
		Url tecnologia = new Url("http://feeds.feedburner.com/Androidiani","tecnologia","androidiani");
		Url politica = new Url("https://www.ilsole24ore.com/rss/mondo.xml","politica","ilsole24ore");
		Url scienze = new Url("https://www.ilsole24ore.com/rss/salute.xml","scienze","ilsole24ore");
		listaUrl.add(random);
		listaUrl.add(tecnologia);
		listaUrl.add(politica);
		listaUrl.add(scienze);
		
		Url[] urlFinal = listaUrl.toArray(new Url[0]);
		FileWriter fw = new FileWriter("feedRSS.json");
		gson.toJson(urlFinal,fw);
		fw.flush();
		fw.close();
	}
	
	//metodo per caricare le notizie dai feed
	public void RSSReader() throws Exception{
		listaUrl.clear();
		ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
		Url[] urlJson;

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
		for(int i=0;i<listaUrl.size();i++) {
			URL urlLista = new URL(listaUrl.get(i).getLink());
			XmlReader reader = new XmlReader(urlLista);
			try {
				SyndFeedInput inp = new SyndFeedInput();
				SyndFeed feed = inp.build(reader);

				for(Object obj : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) obj;
					Notizia notizia = new Notizia(entry.getTitle(),entry.getPublishedDate().toString(),entry.getDescription().getValue().toString(),
							entry.getAuthor(),listaUrl.get(i).getFonte(),entry.getLink(),listaUrl.get(i).getCategoria());
					listaNotizie.add(notizia);
				}
			}finally {
				if(reader!=null) {
					reader.close();
				}
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
	}
}
