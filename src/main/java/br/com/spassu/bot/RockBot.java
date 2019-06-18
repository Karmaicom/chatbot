package br.com.spassu.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RockBot extends TelegramLongPollingBot {

	SendMessage message = new SendMessage();

	@Override
	public void onUpdateReceived(Update update) {

		//System.out.println(update.getMessage().getText());
		//System.out.println(update.getMessage().getFrom().getFirstName());

		List<String> palavras = Arrays.asList("Oi", "Olá", "Hello", "hello", "oi", "olá");
		List<Long> chatIds = null;
		
		String textoUsuarioTelegram = update.getMessage().getText();

		message.setChatId(update.getMessage().getChatId());
		
		
		if (textoUsuarioTelegram.equals("/cep")) {
			chatIds = new ArrayList<Long>();
						
			if (!chatIds.contains(update.getMessage().getChatId())){
				Long chatIdCep = update.getMessage().getChatId();			
				enviarRespostaTelegram("Informe o Cep: ", chatIdCep);	
				waiting();
			} else {
				enviarRespostaTelegram("http://apps.widenet.com.br/busca-cep/api/cep/"+textoUsuarioTelegram);
				waiting();
			}
			
		}

		if (palavras.contains(textoUsuarioTelegram)) {
			enviarRespostaTelegram("Olá, sou o Chatbot do Rock! ");
			waiting();
		} else {
			enviarRespostaTelegram("Ainda não conheço sobre esse assunto. Favor pesquisar em " + "http:\\\\www.google.com.br");
			waiting();
		}
	}

	@Override
	public String getBotUsername() {
		return "rockbot2019_bot";
	}

	@Override
	public String getBotToken() {
		return "865506190:AAEFhfmOCFCn3su-ZVn2zVRbwmKs4sTCBc4";
	}
	
	public void enviarRespostaTelegram(String texto) {
		message.setText(texto);
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarRespostaTelegram(String texto, Long chatId) {
		message.setChatId(chatId);
		message.setText(texto);
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void waiting() {
		try {
			Update update = new Update();
			update.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
