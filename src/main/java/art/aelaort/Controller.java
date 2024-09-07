package art.aelaort;

import art.aelaort.billing.BillingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	private final BillingService billingService;

	public Controller(BillingService billingService) {
		this.billingService = billingService;
	}

	@GetMapping("/billing/{serviceName}")
	public String billing(@PathVariable(required = false) String serviceName) {
		if (serviceName == null) {
			return billingService.getAllString();
		} else {
			return billingService.getByService(serviceName);
		}
	}
}
