package br.com.spassu.main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.spassu.bot.RockBot;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Chatbot iniciado...");
		
		ApiContextInitializer.init();
		
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		
		try {
			telegramBotsApi.registerBot(new RockBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
