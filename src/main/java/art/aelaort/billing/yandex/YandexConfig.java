package art.aelaort.billing.yandex;

import art.aelaort.YandexIAMConfig;
import art.aelaort.YandexIAMSupplier;
import art.aelaort.billing.BillingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class YandexConfig {
	@Bean
	public YandexIAMSupplier yaIAMSupplier(RestTemplate yandexRestTemplate,
										   BillingProperties billingProperties) {
		return new YandexIAMSupplier(yandexRestTemplate,
				new YandexIAMConfig(
						billingProperties.getYandexIAMS3().getId(),
						billingProperties.getYandexIAMS3().getKey(),
						billingProperties.getYandexIAMS3().getUrl(),
						billingProperties.getYandexIAMS3().getRegion(),
						billingProperties.getYandexIAMS3().getFile(),
						billingProperties.getYandexIAMS3().getBucket(),
						billingProperties.getYandexSecrets().getSecret(),
						billingProperties.getYandexSecrets().getUrl()
				));
	}
}
