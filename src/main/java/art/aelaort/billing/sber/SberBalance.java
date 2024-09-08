package art.aelaort.billing.sber;

import art.aelaort.billing.BalanceResponse;
import art.aelaort.billing.BillingProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SberBalance implements BalanceResponse {
	private final BillingProperties billingProperties;

	@Override
	public String balanceString() {
		return billingProperties.getSberStaticString();
	}

	@Override
	public String name() {
		return "sber";
	}
}
