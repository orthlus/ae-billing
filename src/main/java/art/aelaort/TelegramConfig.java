package art.aelaort;

import art.aelaort.telegram.SimpleLongPollingBot;
import art.aelaort.telegram.TelegramInit;
import art.aelaort.telegram.client.TelegramClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

import static art.aelaort.telegram.TelegramBots.createTelegramInit;

@Configuration
public class TelegramConfig {
	@Bean
	public TelegramClient billingTelegramClient(@Value("${billing.telegram.bot.token}") String token) {
		return TelegramClientBuilder.builder()
				.token(token)
				.build();
	}

	@Bean
	public TelegramInit telegramInit(List<SimpleLongPollingBot> bots) {
		return createTelegramInit(bots);
	}
}
