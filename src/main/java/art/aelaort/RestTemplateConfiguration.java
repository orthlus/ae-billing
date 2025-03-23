package art.aelaort;

import art.aelaort.billing.BillingProperties;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Setter
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {
	private final BillingProperties properties;

	@Bean
	public RestTemplate timewebRestTemplate(RestTemplateBuilder builder) {
		return builder
				.rootUri(properties.getTimewebUrl())
				.defaultHeader(AUTHORIZATION, "Bearer " + properties.getTimewebToken().trim())
				.connectTimeout(Duration.ofMinutes(2))
				.build();
	}

	@Bean
	public RestTemplate instagramRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.rootUri(properties.getInstagramUrl())
				.connectTimeout(Duration.ofMinutes(5))
				.readTimeout(Duration.ofMinutes(5))
				.defaultHeader("x-access-key", properties.getInstagramToken())
				.build();
	}

	@Bean
	public RestTemplate tiktokRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.rootUri(properties.getTiktokUrl())
				.connectTimeout(Duration.ofMinutes(5))
				.readTimeout(Duration.ofMinutes(5))
				.defaultHeader(AUTHORIZATION.toLowerCase(), "Bearer " + properties.getTiktokToken())
				.build();
	}

	@Bean
	public RestTemplate yandexRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.rootUri(properties.getYandexUrl())
				.connectTimeout(Duration.ofMinutes(5))
				.readTimeout(Duration.ofMinutes(5))
				.build();
	}

	@Bean
	public RestTemplate iamRestTemplate(RestTemplateBuilder restTemplateBuilder,
										@Value("${iam.url}") String url) {
		return restTemplateBuilder.rootUri(url).build();
	}
}
