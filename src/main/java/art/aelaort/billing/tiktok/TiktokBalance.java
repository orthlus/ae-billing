package art.aelaort.billing.tiktok;

import art.aelaort.billing.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TiktokBalance implements BalanceResponse {
	private final RestTemplate tiktokRestTemplate;

	@Override
	public String balanceString() {
		UserInfo userInfo = tiktokRestTemplate.getForObject("/api/v1/tikhub/user/get_user_info", UserInfo.class);
		if (userInfo.getCode() == 200) {
			return "Баланс: %.3f USD".formatted(userInfo.getUserData().getBalance());
		}
		throw new RuntimeException();
	}

	@Override
	public String name() {
		return "tiktok";
	}
}
