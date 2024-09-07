package art.aelaort.billing.sber;

import art.aelaort.billing.BalanceResponse;
import art.aelaort.billing.BillingProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SberBalance implements BalanceResponse {
	private final BillingProperties billingProperties;

	@Override
	public String balanceString() {
		BillingProperties.Sber properties = billingProperties.getSber();
		String token = getToken(properties);
		String consumption = getConsumption(properties);
		return "";
	}

	private String getConsumption(BillingProperties.Sber properties) {
		// TODO how calculate this???
		LocalDateTime startDate = LocalDateTime.now();
		// TODO how calculate this???
		LocalDateTime endDate = LocalDateTime.now();

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUri(properties.getBillingUrl())
				.queryParam("project_ids", properties.getProjectId())
				.queryParam("start_date", startDate)
				.queryParam("end_date", endDate);
		BillingResponse response = new RestTemplate().getForObject(
				builder.build().toUri(),
				BillingResponse.class
		);

		throw new RuntimeException();
	}

	private String getToken(BillingProperties.Sber properties) {
		return new RestTemplate().postForObject(
				properties.getTokenUrl(),
				new HttpEntity<>(Map.of(
						"grant_type", "access_key",
						"client_id", properties.getTokenClientId(),
						"client_secret", properties.getTokenClientSecret()
				)),
				TokenResponse.class
		).getAccessToken();
	}

	@Override
	public String name() {
		return "sber";
	}
}
