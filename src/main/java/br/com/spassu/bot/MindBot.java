package br.com.spassu.bot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MindBot extends TelegramLongPollingBot {

	SendMessage message = new SendMessage();
	static List<Long> chatIds;

	@Override
	public void onUpdateReceived(Update update) {
		tratarTextoUsuarioTelegram(update.getMessage().getText(), message, update);
	}

	@Override
	public String getBotUsername() {
		// return "rockbot2019_bot";
		return "mindbr_bot";
	}

	@Override
	public String getBotToken() {
		// return "865506190:AAEFhfmOCFCn3su-ZVn2zVRbwmKs4sTCBc4";
		return "828048268:AAEt5WU1i_s-wm1tmhSf55OAMalCCw8Yd-w";
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
			enviarRespostaTelegram("Bem vindo(a) " + update.getMessage().getFrom().getFirstName() + "!", message,
					update, null, null);
			mensagemRespondida = true;
		}

		// Intent horário
		// SimpleDateFormat spf = new SimpleDateFormat("hh:mm:ss");
		// Date agora = new Date();
		// String horas = spf.format(agora);

		Calendar data = Calendar.getInstance();
		int hora = data.get(Calendar.HOUR_OF_DAY);
		int minuto = data.get(Calendar.MINUTE);
		int segundo = data.get(Calendar.SECOND);
		List<String> horario = Arrays.asList("horas", "hora", "que horas são?", "que horas tem?", "que horas são", "que horas tem"
				,"que horas são ?", "que horas tem ?");
		if (horario.contains(textUsuario) && mensagemRespondida == false) {
			List<String> horarioList = Arrays.asList(
					"São " + hora + ":" + (Integer.toString(minuto).length() < 2 ? "0" + minuto : minuto) + "h");
			Random random = new Random();
			enviarRespostaTelegram(horarioList.get(random.nextInt(horarioList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent agradecer
		List<String> agradecer = Arrays.asList("ok", "desculpe", "agradecida", "grata", "grato", "agradecido", "show",
				"beleza", "blz", "vlw", "valeu", "thanks", "tks", "muito obrigada", "obrigada", "obrigado");
		if (agradecer.contains(textUsuario) && mensagemRespondida == false) {
			List<String> agradecerList = Arrays.asList("De nada!", "Disponha.", "Até a próxima.", "Até mais.",
					"Volte sempre que precisar.", "Foi um prazer ajudar.");
			Random random = new Random();
			enviarRespostaTelegram(agradecerList.get(random.nextInt(agradecerList.size())), message, update, null,
					null);
			mensagemRespondida = true;
		}

		// Intent chamar
		List<String> chamar = Arrays.asList("perguntar", "fazer uma pergunta", "fazer pergunta", "assistente", "e aí",
				"e ae", "eae", "fala aí", "há", " quanto tempo", "opa", "oi tudo bem", "olá", "fala", "boa noite",
				"bom dia", "boa tarde", "oiê", "fala ae", "oi", "pergunta", "quero fazer uma pergunta",
				"gostaria de fazer uma pergunta", "?");
		if (chamar.contains(textUsuario) && mensagemRespondida == false) {
			List<String> chamarList = Arrays.asList("Olá! Sou a Mind! Em quê posso lhe ajudar?",
					"Olá! Sou a Mind! Como posso ajudar?",
					"Olá! Sou a MIND, a Memória e Inteligência Digital. Como posso te ajudar a aprender hoje?",
					"Olá! É um prazer te atender. Sou a Mind! Como posso ajudar?");
			Random random = new Random();
			enviarRespostaTelegram(chamarList.get(random.nextInt(chamarList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent emoji
		List<String> emoji = Arrays.asList("envia um emoji pra mim", "emoji", "me manda um emoji", "Quero um emoji");
		if (emoji.contains(textUsuario) && mensagemRespondida == false) {
			List<String> emojiList = Arrays.asList(":grinning:", ":joy:");
			Random random = new Random();
			enviarRespostaTelegram(emojiList.get(random.nextInt(emojiList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent ofender
		List<String> ofender = Arrays.asList("sua retardada", "retardada mental", "retardada", "idiota", "sua imbecil",
				"imbecil", "muito burra", "burra", "vaca", "sua vaca", "deixa de ser estúpida");
		if (ofender.contains(textUsuario) && mensagemRespondida == false) {
			List<String> ofenderList = Arrays.asList("Ah, que isso, não precisa ofender. Estou aqui para ajudar.",
					"Poxa, que desagradável! Não imaginava passar por isso hoje.",
					"Calma, não precisa ficar agressivo(a).  Estou aqui apenas para lhe ajudar.");
			Random random = new Random();
			enviarRespostaTelegram(ofenderList.get(random.nextInt(ofenderList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent prazo
		List<String> prazo = Arrays.asList("quando terei um retorno?", "essa resposta demora?",
				"em quanto tempo terei resposta?", "quanto tempo vou esperar?", "isso vai demorar muito?");
		if (prazo.contains(textUsuario) && mensagemRespondida == false) {
			List<String> prazoList = Arrays.asList("Só um momento, por favor.",
					"Só um instante, estou tetando encontrar a melhor resposta.", "Só um instante, por favor.",
					"Só um instante, estou consultando e já te respondo.",
					"Aguarde um instante, por favor. Estou tentando encontrar a melhor resposta.",
					"Aguarde um momento, por favor. Estou tentando encontrar e melhor resposta.");
			Random random = new Random();
			enviarRespostaTelegram(prazoList.get(random.nextInt(prazoList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent previsão
		List<String> previsao = Arrays.asList("previsão do tempo", "previsão chuva", "qual a previsão do tempo?",
				"teremos chuva amanhã?", "Amanhã vai fazer sol?", "Choverá hoje à noite?", "Teremos sol hoje?",
				"Vai chover hoje?");
		if (previsao.contains(textUsuario) && mensagemRespondida == false) {
			List<String> previsaoList = Arrays.asList(
					"Humm... Sobre isso não sei dizer, mas você pode dar uma olhada no Clima Tempo",
					"Não sei, não. Já tentou consultar algum canal sobre o tempo?",
					"Poxa, não sei informar sobre o tempo, mas você pode dar uma conferida em www.climatempo.com.br");
			Random random = new Random();
			enviarRespostaTelegram(previsaoList.get(random.nextInt(previsaoList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		// Intent não
		List<String> nao = Arrays.asList("não quero isso", "não faça isso", "acho que não", "não tenho interesse",
				"definitivamente não", "não obrigado", "não", "discordo", "não mesmo");
		if (nao.contains(textUsuario) && mensagemRespondida == false) {
			List<String> naoList = Arrays.asList("Lamento, só tenho informações sobre o EDISE.",
					"Que pena, não posso ajudar. No momento só tenho informações sobre o EDISE.",
					"Desculpa, mas não tenho como ajudar.  Só me passaram informações sobre o EDISE.");
			Random random = new Random();
			enviarRespostaTelegram(naoList.get(random.nextInt(naoList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		/*
		 * // Intent sim List<String> sim = Arrays.asList("tambem", "também", "correto",
		 * "é", "faça isso", "isso mesmo", "sim", "confirmar", "parece bom", "concordo",
		 * "exatamente", "claro", "com certeza"); if (sim.contains(textUsuario) &&
		 * mensagemRespondida == false) { List<String> simList =
		 * Arrays.asList("Então, o EDISE fica na Avenida Chile, 65",
		 * "Certo, o EDISE fica no Centro, na Avenida Chile, 65",
		 * "O EDISE fica na Avenida Chile, 65. Bem em frente ao prédio do BNDES",
		 * "A sede fica na Avenida Chile, 65, aproximadamente duzentos metros antes da Catedral."
		 * ); Random random = new Random();
		 * enviarRespostaTelegram(simList.get(random.nextInt(simList.size())), message,
		 * update, null, null); mensagemRespondida = true; }
		 */

		// Intent perguntar
		List<String> perguntar = Arrays.asList("pode me ajudar? quero saber onde está a petrobras.",
				"sabe onde fica a petrobras?", "em que rua fica a petrobras?", "onde posso encontrar a Petrobras?");
		if (perguntar.contains(textUsuario) && mensagemRespondida == false) {
			List<String> perguntarList = Arrays.asList(
					"Entendi, mas, de qual imóvel Petrobras você gostaria de saber a localidade? É do EDISE?",
					"Tudo bem, vou te ajudar com isso. Você está falando do edifício sede da Petrobras?");
			Random random = new Random();
			enviarRespostaTelegram(perguntarList.get(random.nextInt(perguntarList.size())), message, update, null,
					null);
			mensagemRespondida = true;
		}

		// Intent sorrir
		List<String> sorrir = Arrays.asList(":smile:");
		if (sorrir.contains(textUsuario) && mensagemRespondida == false) {
			List<String> sorrirList = Arrays.asList("Que lindo sorriso!");
			Random random = new Random();
			enviarRespostaTelegram(sorrirList.get(random.nextInt(sorrirList.size())), message, update, null, null);
			mensagemRespondida = true;
		}

		if (mensagemRespondida == false) {
			enviarRespostaAPI(message, update);
			// enviarRespostaTelegram("Ainda não conheço sobre esse assunto. Favor pesquisar
			// em " + "http:\\\\www.google.com.br", message, update);
			mensagemRespondida = true;
		}

	}

	/*
	 * private void enviarRespostaAutomaticaFoiUtil(SendMessage message, Update
	 * update) {
	 * 
	 * List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
	 * List<InlineKeyboardButton> buttons1 = new ArrayList<>(); buttons1.add(new
	 * InlineKeyboardButton().setText("1").setUrl("http://www.google.com.br"));
	 * buttons1.add(new
	 * InlineKeyboardButton().setText("2").setUrl("http://www.google.com.br"));
	 * buttons1.add(new
	 * InlineKeyboardButton().setText("3").setUrl("http://www.google.com.br"));
	 * buttons1.add(new
	 * InlineKeyboardButton().setText("4").setUrl("http://www.google.com.br"));
	 * buttons1.add(new
	 * InlineKeyboardButton().setText("5").setUrl("http://www.google.com.br"));
	 * buttons.add(buttons1);
	 * 
	 * InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
	 * markupKeyboard.setKeyboard(buttons);
	 * 
	 * enviarRespostaTelegram("A resposta foi útil? \nAvalie solecionando uma opção abaixo: "
	 * , message, update, markupKeyboard, null); }
	 */

	int contadorTentativas = 0;

	private synchronized void enviarRespostaAPI(SendMessage message, Update update) {

		/*
		 * // Create a keyboard ReplyKeyboardMarkup replyKeyboardMarkup = new
		 * ReplyKeyboardMarkup(); message.setReplyMarkup(replyKeyboardMarkup);
		 * replyKeyboardMarkup.setSelective(true);
		 * replyKeyboardMarkup.setResizeKeyboard(true);
		 * replyKeyboardMarkup.setOneTimeKeyboard(false);
		 * 
		 * // Create a list of keyboard rows List<KeyboardRow> keyboard = new
		 * ArrayList<>();
		 * 
		 * KeyboardRow keyboard1 = new KeyboardRow(); keyboard1.add(new
		 * KeyboardButton("Recursos Humanos"));
		 * 
		 * KeyboardRow keyboard2 = new KeyboardRow(); keyboard2.add(new
		 * KeyboardButton("Segurança"));
		 * 
		 * KeyboardRow keyboard3 = new KeyboardRow(); keyboard3.add(new
		 * KeyboardButton("Embarque em Plataforma"));
		 * 
		 * KeyboardRow keyboard4 = new KeyboardRow(); keyboard3.add(new
		 * KeyboardButton("Nenhuma das opções"));
		 * 
		 * keyboard.add(keyboard1); keyboard.add(keyboard2); keyboard.add(keyboard3);
		 * keyboard.add(keyboard4); replyKeyboardMarkup.setKeyboard(keyboard);
		 * 
		 * enviarRespostaTelegram("Ainda não conheço sobre esse assunto. \nSobre o que quer falar? \nSelecione uma opção abaixo: "
		 * , message, update, null, replyKeyboardMarkup);
		 */

		// https://zfrdmofcdk.execute-api.us-east-1.amazonaws.com/default/HelloFunction
		// https://bv29vu8il0.execute-api.sa-east-1.amazonaws.com/DEV/chamador

		// String username = "rararipe";

		String textMsgRespostaFormatada = "";

		Calendar data = Calendar.getInstance(); int hora = data.get(Calendar.HOUR_OF_DAY); int minuto = data.get(Calendar.MINUTE); int segundo = data.get(Calendar.SECOND);
		System.out.println("----------------------");
		System.out.println("ChatId: " + update.getMessage().getChatId());
		System.out.println("Username: " + update.getMessage().getChat().getUserName());
		System.out.println("Texto: " + update.getMessage().getText());
		System.out.println("Data: " + LocalDate.now() + " Hora: " + hora + ":" + (Integer.toString(minuto).length() < 2 ? "0" + minuto : minuto) + "h");
		System.out.println("----------------------");
		
		String jsonInputString = "{'responseId': 'b9de7dd5-d645-44f5-bec7-f84b690a1549-64cfa233',"
				+ "'queryResult': {'action': 'acionar'},"
				+ "'originalDetectIntentRequest': {'payload': {'data': {'message': {'from': {'username': '"
				+ update.getMessage().getChat().getUserName() + "', 'id':" + update.getMessage().getChatId()
				+ "}, 'chat': {'id': " + update.getMessage().getChatId() + ", 'username': '"
				+ update.getMessage().getChat().getUserName() + "'" + "}, 'text': '" + replaceCaracteres(update.getMessage().getText())
				+ "'" + "}" + "}" + "}" + "}" + "}";

		try {
			String url = "https://bv29vu8il0.execute-api.sa-east-1.amazonaws.com/DEV/chamador";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("accept-encoding", "gzip, deflate");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("User-Agent", "PostmanRuntime/7.15.0");

			// con.setRequestProperty("Accept-Charset", "utf-8");
			// con.setRequestProperty("Charset", "utf-8");
			// con.setRequestProperty("Accept", "application/json");

			// Charset.forName("UTF-8").encode(jsonInputString);

			// Send post request
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(jsonInputString);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) con.getContent()));
			JsonObject jsonobj = root.getAsJsonObject();
			JsonElement textoMsgResposta = jsonobj.get("fulfillmentText");

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			textMsgRespostaFormatada = textoMsgResposta.toString().replace("\"", "");

			// print result
			// System.out.println(response.toString());
			enviarRespostaTelegram(textMsgRespostaFormatada.toString(), message, update, null, null);

		} catch (MalformedURLException e) {
			// enviarRespostaTelegram("Desculpa, mas não entendi. Estou tentando
			// novamente.", message, update, null, null);
			e.printStackTrace();
			if (contadorTentativas < 3) {
				contadorTentativas++;
				enviarRespostaAPI(message, update);
			} else {
				enviarRespostaTelegram("Desculpa, mas realmente não entendi. Pergunte novamente.", message, update,
						null, null);
			}
		} catch (ProtocolException e) {
			// enviarRespostaTelegram("Desculpa, mas não entendi. Estou tentando
			// novamente.", message, update, null, null);
			e.printStackTrace();
			if (contadorTentativas < 3) {
				contadorTentativas++;
				enviarRespostaAPI(message, update);
			} else {
				enviarRespostaTelegram("Desculpa, mas realmente não entendi. Pergunte novamente.", message, update,
						null, null);
			}
		} catch (IOException e) {
			// enviarRespostaTelegram("Desculpa, mas não entendi. Estou tentando
			// novamente.", message, update, null, null);
			e.printStackTrace();
			System.out.println(contadorTentativas);
			if (contadorTentativas < 3) {
				contadorTentativas++;
				enviarRespostaAPI(message, update);
			} else {
				enviarRespostaTelegram("Desculpa, mas realmente não entendi. Pergunte novamente.", message, update,
						null, null);
			}
		}

	}

	public void enviarRespostaTelegram(String texto, SendMessage message, Update update,
			InlineKeyboardMarkup markupKeyboard, ReplyKeyboardMarkup replyKeyboardMarkup) {

		message.setChatId(update.getMessage().getChatId());
		message.setText(texto);

		if (markupKeyboard != null) {
			message.setReplyMarkup(markupKeyboard);
		}
		if (replyKeyboardMarkup != null) {
			message.setReplyMarkup(replyKeyboardMarkup);
		}

		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public String replaceCaracteres(String texto) {
		String myMsgTratada = texto;
		myMsgTratada = myMsgTratada.replace("ç", "&ccedil;");
		myMsgTratada = myMsgTratada.replace("Ç", "&Ccedil;");

		myMsgTratada = myMsgTratada.replace("ä", "&auml;");
		myMsgTratada = myMsgTratada.replace("Ä", "&Auml;");
		myMsgTratada = myMsgTratada.replace("ã", "&atilde;");
		myMsgTratada = myMsgTratada.replace("Ã", "&Atilde;");
		myMsgTratada = myMsgTratada.replace("á", "&aacute;");
		myMsgTratada = myMsgTratada.replace("Á", "&Aacute;");
		myMsgTratada = myMsgTratada.replace("à", "&agrave;");
		myMsgTratada = myMsgTratada.replace("À", "&Agrave;");
		myMsgTratada = myMsgTratada.replace("â", "&acirc;");
		myMsgTratada = myMsgTratada.replace("Â", "&Acirc;");

		myMsgTratada = myMsgTratada.replace("ë", "&euml;");
		myMsgTratada = myMsgTratada.replace("Ë", "&Euml;");
		myMsgTratada = myMsgTratada.replace("é", "&eacute;");
		myMsgTratada = myMsgTratada.replace("É", "&Eacute;");
		myMsgTratada = myMsgTratada.replace("è", "&egrave;");
		myMsgTratada = myMsgTratada.replace("È", "&Egrave;");
		myMsgTratada = myMsgTratada.replace("ê", "&ecirc;");
		myMsgTratada = myMsgTratada.replace("Ê", "&Ecirc;");

		myMsgTratada = myMsgTratada.replace("ï", "&iuml;");
		myMsgTratada = myMsgTratada.replace("Ï", "&Iuml;");
		myMsgTratada = myMsgTratada.replace("í", "&iacute;");
		myMsgTratada = myMsgTratada.replace("Í", "&Iacute;");
		myMsgTratada = myMsgTratada.replace("ì", "&igrave;");
		myMsgTratada = myMsgTratada.replace("Ì", "&Igrave;");
		myMsgTratada = myMsgTratada.replace("î", "&icirc;");
		myMsgTratada = myMsgTratada.replace("Î", "&Icirc;");

		myMsgTratada = myMsgTratada.replace("ö", "&ouml;");
		myMsgTratada = myMsgTratada.replace("Ö", "&Ouml;");
		myMsgTratada = myMsgTratada.replace("õ", "&otilde;");
		myMsgTratada = myMsgTratada.replace("Õ", "&Otilde;");
		myMsgTratada = myMsgTratada.replace("ó", "&oacute;");
		myMsgTratada = myMsgTratada.replace("Ó", "&Oacute;");
		myMsgTratada = myMsgTratada.replace("ò", "&ograve;");
		myMsgTratada = myMsgTratada.replace("Ò", "&Ograve;");
		myMsgTratada = myMsgTratada.replace("ô", "&ocirc;");
		myMsgTratada = myMsgTratada.replace("Ô", "&Ocirc;");

		myMsgTratada = myMsgTratada.replace("ü", "&uuml;");
		myMsgTratada = myMsgTratada.replace("Ü", "&Uuml;");
		myMsgTratada = myMsgTratada.replace("ú", "&uacute;");
		myMsgTratada = myMsgTratada.replace("Ú", "&Uacute;");
		myMsgTratada = myMsgTratada.replace("ù", "&ugrave;");
		myMsgTratada = myMsgTratada.replace("Ù", "&Ugrave;");
		myMsgTratada = myMsgTratada.replace("û", "&ucirc;");
		myMsgTratada = myMsgTratada.replace("Û", "&Ucirc;");
		
		return myMsgTratada;
	}

}
