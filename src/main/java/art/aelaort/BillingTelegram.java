package art.aelaort;

import art.aelaort.billing.BillingService;
import art.aelaort.telegram.Command;
import art.aelaort.telegram.SimpleAdminBot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

import static art.aelaort.telegram.client.TelegramClientHelpers.execute;

@Component
@RequiredArgsConstructor
public class BillingTelegram implements SimpleAdminBot {
	private final BillingService billingService;

	private enum Commands implements Command {
		START,
		SHOW,
		TIMEWEB_SHOW,
	}

	@Getter
	@Value("${billing.telegram.bot.token}")
	private String botToken;
	@Getter
	@Value("${telegram.admin.id}")
	private long adminId;
	private final TelegramClient billingTelegramClient;
	private final Map<String, Commands> commandsMap = Command.buildMap(Commands.class);

	@Override
	public void consumeAdmin(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String messageText = update.getMessage().getText();

			if (commandsMap.containsKey(messageText)) {
				handleCommand(messageText);
			} else {
				handleText(messageText);
			}
		}
	}

	private void handleText(String messageText) {
		send("команды:\n" + String.join("\n", commandsMap.keySet()));
	}

	private void handleCommand(String messageText) {
		switch (commandsMap.get(messageText)) {
			case START -> send("команды:\n" + String.join("\n", commandsMap.keySet()));
			case SHOW -> send(billingService.getAllString());
			case TIMEWEB_SHOW -> send(billingService.getByService("timeweb"));
		}
	}

	private void send(String text) {
		execute(SendMessage.builder()
				.chatId(adminId)
				.text(text)
				.parseMode("markdown")
				.build(), billingTelegramClient);
	}
}
