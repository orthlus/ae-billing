package art.aelaort.billing.pq;

import art.aelaort.billing.BalanceResponse;
import art.aelaort.billing.BillingProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class PqBalance implements BalanceResponse {
	private final BillingProperties billingProperties;

	@Override
	public String balanceString() {
		return billingProperties.getPqStaticString();
	}

	@Override
	public String name() {
		return "pq";
	}
}
