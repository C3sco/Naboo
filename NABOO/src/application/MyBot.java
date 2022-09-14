package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyBot extends TelegramLongPollingBot {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	Commento[] commentiJson;
	Notizia[] notizieJson;
	Voto[] votiJson;
	ArrayList<Utente> listaUsers = new ArrayList<Utente>();
	ArrayList<Voto> listaVoti = new ArrayList<Voto>();
	int register = 0;
	int login = 0;
	int notizia = 0;
	int voto = 0;
	Notizia currentNotizia;

	@Override
	public String getBotUsername() {
		return "Naboo2022_Bot";
	}

	@Override
	public String getBotToken() {
		return "5535947581:AAGnbDVP6w83v_8lUHg2LIrOYgyuf6L8ReY";
	}



	@Override
	public void onUpdateReceived(Update update) {
		Utente[] utentiJson = null;
		listaUsers.clear();
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage sendMessage = new SendMessage();
			String chatId = update.getMessage().getChatId().toString();
			String message = update.getMessage().getText();
			String username = update.getMessage().getChat().getUserName();
			String risposta = null;
			String[] split = message.split(" ");
			String nome = update.getMessage().getChat().getFirstName();
			String cognome = update.getMessage().getChat().getLastName();



			switch(register) {
			case 1:
				String password = message;
				Utente utente = new Utente(nome,cognome,username,password);
				register=0;

				try {
					JsonReader jr = new JsonReader(new FileReader("users.json"));
					utentiJson = gson.fromJson(jr,Utente[].class);
					for(int i=0; i<utentiJson.length;i++) {
						Utente user = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername(),utentiJson[i].getPassword());
						listaUsers.add(user);
					}

				}catch(FileNotFoundException e) {
					e.getMessage();
				}


				listaUsers.add(utente);
				Utente[] userFinal = listaUsers.toArray(new Utente[0]);

				FileWriter fw;
				try {
					fw = new FileWriter("users.json");
					gson.toJson(userFinal,fw);
					fw.flush();
					fw.close();
					sendMessage.setText("Utente registrato con successo!" + nome + " " + cognome + " " + username);
				} catch (IOException e ) {
					e.printStackTrace();
				}
				break;
			}

			switch(voto) {
			case 1: 
				listaVoti.clear();
				int vote = Integer.parseInt(message);
				int duplicate = 0;
				if(vote<=10 && vote>=1) {
					Voto votoInsert = new Voto(vote,currentNotizia.getLink(),username);
					try {
						JsonReader jr = new JsonReader(new FileReader("voti.json"));
						votiJson = gson.fromJson(jr,Voto[].class);
						for(int i=0;i<votiJson.length;i++) {
							listaVoti.add(votiJson[i]);
							if(votiJson[i].getUsernameUtente().equals(username) & votiJson[i].getLinkNotizia().equals(currentNotizia.getLink())) { //controllo che non ci sia già un voto dell'utente nella notizia
								duplicate = 1;
							}
						}
						if(duplicate==0) {
							listaVoti.add(votoInsert);
						}
						Voto[] votiFinal = listaVoti.toArray(new Voto[0]);
						FileWriter fw = new FileWriter("voti.json");
						gson.toJson(votiFinal,fw);
						fw.flush();
						fw.close();
					}catch(IOException e) {
						e.getMessage();
					}

				}
			}
			/*
			switch(notizia) {
			case 1:
				switch(message) {
				case "random":
					Random random = new Random();
					ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
					Notizia[] notizieJson;
				    Gson gson = new GsonBuilder().setPrettyPrinting().create();
			    	try {
			 	    	JsonReader jr = new JsonReader(new FileReader("notizie.json"));
			 	    	notizieJson = gson.fromJson(jr,Notizia[].class);
			 	    	for(int i=0; i<notizieJson.length;i++) {
			 	    		Notizia notizia = new Notizia(notizieJson[i].getTitolo(),notizieJson[i].getTimestamp(),
			 	    				notizieJson[i].getDescrizione(),notizieJson[i].getAutore(),notizieJson[i].getFonte(),notizieJson[i].getLink(),notizieJson[i].getCategoria());
			 	    		if(notizia.getCategoria()=="random"){
			 	    			listaNotizie.add(notizia);
			 	    		}
			 	    	}
			 	    }catch(FileNotFoundException e) {
			 	    	e.getMessage();
			 	    }
			    	notizia=0;
			    	sendMessage.setText(listaNotizie.get(random.nextInt(listaNotizie.size())).getLink());

					break;
				}
			}
			 */
			switch(login) {
			case 1:
				sendMessage.setText("Utente registrato con successo!");
				break;
			}

			switch(message) {

			case "/registrazione" :
				register = 1;
				sendMessage.setText("Per registrarti inserisci la tua password senza usare spazi. Ricorda che il tuo nome, cognome e username saranno quelli"
						+ " utilizzati su telegram!");

				break;

			case "/test":
				Random random = new Random();
				ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
				Notizia[] notizieJson;
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				try {
					JsonReader jr = new JsonReader(new FileReader("notizie.json"));
					notizieJson = gson.fromJson(jr,Notizia[].class);
					for(int i=0; i<notizieJson.length;i++) {
						Notizia notizia = new Notizia(notizieJson[i].getTitolo(),notizieJson[i].getTimestamp(),
								notizieJson[i].getDescrizione(),notizieJson[i].getAutore(),notizieJson[i].getFonte(),notizieJson[i].getLink(),notizieJson[i].getCategoria());
						if(notizia.getCategoria()=="random"){
							listaNotizie.add(notizia);
						}
					}
				}catch(FileNotFoundException e) {
					e.getMessage();
				}
				notizia=0;
				//salvo la notizia
				currentNotizia = listaNotizie.get(random.nextInt(listaNotizie.size()));
				sendMessage.setText(listaNotizie.get(random.nextInt(listaNotizie.size())).getLink());
				break;

			case "/notizia":
				notizia = 1;
				sendMessage.setText("Che notizie vuoi avere? Scegli uno dei seguenti campi: random | tecnologia | politica | scienza");
				break;

			case "/voto":
				voto=1;
				sendMessage.setText("Inserisci un voto da 1 a 10 per la notizia");

				break;

			case "/commento":

				break;

			case "/logout" :
				if(login==1) {
					sendMessage.setText("Logout effettuato!");
					login = 0;
				}else {
					sendMessage.setText("Non hai effettuato l'accesso a Naboo!");
				}
				break;

			}

			sendMessage.setChatId(chatId);

			try {
				execute(sendMessage);
			}catch(TelegramApiException e){

			}

			/*
			if(message.equalsIgnoreCase("/registrazione")) {
				sendMessage.setText("Per registrarti inserisci il tuo nome e cognome separati da uno spazio. "
						+ "Esempio:  Marco Rossi ");
				sendMessage.setChatId(chatId);
				register = 1;	

			try {
				execute(sendMessage);
			}catch(TelegramApiException e){

			}
			/*
			 * else if(register==1) {

				String nome = split[0];
				String cognome = split[1];
				Utente utente = new Utente(nome,cognome,username);
				register=0;
				for(int i=0; i<utentiJson.length;i++) {
	 	    		Utente user = new Utente(utentiJson[i].getNome(),utentiJson[i].getCognome(),utentiJson[i].getUsername());
	 	    		listaUsers.add(user);
				}
				listaUsers.add(utente);

				try {
					JsonReader jr = new JsonReader(new FileReader("users.json"));
					utentiJson = gson.fromJson(jr,Utente[].class);

				}catch(FileNotFoundException e) {
		 	    	e.getMessage();
		 	    }
				Utente[] userFinal = listaUsers.toArray(new Utente[0]);

		    	FileWriter fw;
				try {
					fw = new FileWriter("users.json");
					gson.toJson(userFinal,fw);
					fw.flush();
					fw.close();
					sendMessage.setText("Utente registrato con successo!");
					execute(sendMessage);
				} catch (IOException | TelegramApiException e ) {
					e.printStackTrace();
				}
			}


/*
			switch(message) {

			case "/registrazione" :
				sendMessage.setText("Per registrarti inserisci il tuo nome e cognome separati da uno spazio. "
						+ "Esempio:  Marco Rossi ");
				register = 1;

				try {
					execute(sendMessage);
				}catch(TelegramApiException e){

				}
				break;
			}*/


		}


	}
}
