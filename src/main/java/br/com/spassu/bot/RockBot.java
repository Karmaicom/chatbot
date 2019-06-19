package br.com.spassu.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RockBot extends TelegramLongPollingBot {

	SendMessage message = new SendMessage();
	static List<Long> chatIds;

	@Override
	public void onUpdateReceived(Update update) {
		tratarTextoUsuarioTelegram(update.getMessage().getText(), message, update);	
	}

	@Override
	public String getBotUsername() {
		return "rockbot2019_bot";
	}

	@Override
	public String getBotToken() {
		return "865506190:AAEFhfmOCFCn3su-ZVn2zVRbwmKs4sTCBc4";
	}
	
	
	/**
	 * Classe para tratar mensagem recebida pelo Telegram
	 * 
	 * @param textoUsuarioTelegram
	 * @param message
	 * @param update
	 */	
	public void tratarTextoUsuarioTelegram(String textoUsuarioTelegram, SendMessage message, Update update) {
		String textUsuario = textoUsuarioTelegram.toLowerCase();
		Boolean mensagemRespondida = false;
		
		// Command Start
		if (textUsuario.equals("/start")) {
			enviarRespostaTelegram("Bem vindo(a) " + update.getMessage().getFrom().getFirstName() + "!", message, update, null, null);
			mensagemRespondida = true;
		}
		
		// Intent welcome
		List<String> welcome = Arrays.asList("oi", "olá", "hello", "ola", "hi");
		if (welcome.contains(textUsuario) && mensagemRespondida == false) {
			List<String> welcomeList = Arrays.asList(
					"Olá, sou o ChatBot do Rock!",
					"Olá, tudo bem? Bem vindo ao mundo de notícias do Rock!"
			);
			Random random = new Random();
			enviarRespostaTelegram(welcomeList.get(random.nextInt(welcomeList.size())), message, update, null, null);
			mensagemRespondida = true;
		} 
					
		// Intent bye
		List<String> bye = Arrays.asList("adeus", "tchau", "bye", "txau");
		if (bye.contains(textUsuario) && mensagemRespondida == false) {
			List<String> byeList = Arrays.asList(
				"Nos vemos em breve!",
				"Obrigado pela conversa!",
				"Foi bom conversar com você!"
			);
			Random random = new Random();
			enviarRespostaTelegram(byeList.get(random.nextInt(byeList.size())), message, update, null, null);
			mensagemRespondida = true;
		} 
		
		// Intent Segurança
		List<String> seguranca = Arrays.asList("epi", "segurança");
		if (seguranca.contains(textUsuario) && mensagemRespondida == false) {
			List<String> segurancaList = Arrays.asList(
					"Os equipamentos de segurança devem ser utilizados em locais apropriados.",
					"Garanta sua segurança utilizando os equipamentos indicados."
			);
			Random random = new Random();
			enviarRespostaTelegram(segurancaList.get(random.nextInt(segurancaList.size())), message, update, null, null);
			enviarRespostaAutomaticaFoiUtil(message, update);
			mensagemRespondida = true;
		} 
		
		if(mensagemRespondida == false) {
			enviarRespostaAutomatica(message, update);
			//enviarRespostaTelegram("Ainda não conheço sobre esse assunto. Favor pesquisar em " + "http:\\\\www.google.com.br", message, update);	
			mensagemRespondida = true;
		}
		
		
		
	}
	
	private void enviarRespostaAutomaticaFoiUtil(SendMessage message, Update update) {
		
		List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("1").setUrl("http://www.google.com.br"));
        buttons1.add(new InlineKeyboardButton().setText("2").setUrl("http://www.google.com.br"));
        buttons1.add(new InlineKeyboardButton().setText("3").setUrl("http://www.google.com.br"));
        buttons1.add(new InlineKeyboardButton().setText("4").setUrl("http://www.google.com.br"));
        buttons1.add(new InlineKeyboardButton().setText("5").setUrl("http://www.google.com.br"));
        buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        
        enviarRespostaTelegram("A resposta foi útil? \nAvalie solecionando uma opção abaixo: ", message, update, markupKeyboard, null);
	}
	
	
	private synchronized void enviarRespostaAutomatica(SendMessage message, Update update) {
		
		// Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboard1 = new KeyboardRow();
        keyboard1.add(new KeyboardButton("Recursos Humanos"));

        KeyboardRow keyboard2 = new KeyboardRow();
        keyboard2.add(new KeyboardButton("Segurança"));
        
        KeyboardRow keyboard3 = new KeyboardRow();
        keyboard3.add(new KeyboardButton("Embarque em Plataforma"));
        
        KeyboardRow keyboard4 = new KeyboardRow();
        keyboard3.add(new KeyboardButton("Nenhuma das opções"));

        keyboard.add(keyboard1);
        keyboard.add(keyboard2);
        keyboard.add(keyboard3);
        keyboard.add(keyboard4);
        replyKeyboardMarkup.setKeyboard(keyboard);	
        
        enviarRespostaTelegram("Ainda não conheço sobre esse assunto. \nSobre o que quer falar? \nSelecione uma opção abaixo: ", message, update, null, replyKeyboardMarkup);
	}
	

	public void enviarRespostaTelegram(String texto, SendMessage message, Update update, InlineKeyboardMarkup markupKeyboard,  ReplyKeyboardMarkup replyKeyboardMarkup) {
		message.setChatId(update.getMessage().getChatId());
		message.setText(texto);
		
		if(markupKeyboard != null) {
			message.setReplyMarkup(markupKeyboard);			
		}
		if(replyKeyboardMarkup != null) {
			message.setReplyMarkup(replyKeyboardMarkup);
		}
		
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
