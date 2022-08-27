package application;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
/*    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        
    }*/
	

    @Override
    public String getBotUsername() {
        return "Naboo Bot";
    }

    @Override
    public String getBotToken() {
        return "1727092725:AAH4pEDopSWnf35Yfv2g9CH0dqjiWSs6l4I";
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
            }
        }
        
    }
}
