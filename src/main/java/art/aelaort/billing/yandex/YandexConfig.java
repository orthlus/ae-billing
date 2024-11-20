package art.aelaort.billing.yandex;

import art.aelaort.YandexIAMConfig;
import art.aelaort.YandexIAMSupplier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(YandexIAMConfig.class)
@Configuration
public class YandexConfig {
	@Bean
	public YandexIAMSupplier yaIAMSupplier(RestTemplate yandexRestTemplate, YandexIAMConfig yandexIAMConfig) {
		return new YandexIAMSupplier(yandexRestTemplate, yandexIAMConfig);
	}
}
