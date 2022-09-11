package application;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
	

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText();
            String user = update.getMessage().getChat().getUserName();
            String risposta = null;
            String[] split = message.split(" ");
            String command = split[0].substring(1);
            if(message.equalsIgnoreCase("ciao")) {
            	sendMessage.setText("ciao");
            	sendMessage.setChatId(update.getMessage().getChatId());
            	try {
            		execute(sendMessage);
            	}catch(TelegramApiException e) {
            		
            	}
            }  /*          
            switch(command) {
            
            case "/registrazione" :
            	sendMessage.setText("Per registrarti inserisci il tuo username e la tua password separati da uno spazio. "
            			+ "Esempio:    Marco Password123 ");
            	try {
            		execute(sendMessage);
            	}catch(TelegramApiException e){
            		
            	}     	
            }

            sendMessage.setChatId(chatId);
            
            try {
                execute(sendMessage); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }*/
        }
        
    }
}
