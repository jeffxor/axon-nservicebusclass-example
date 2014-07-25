package org.billing.saga;

import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.sales.events.OrderCancelled;
import org.shipping.events.ProductReturned;
import org.shipping.events.ShippingCancelled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class RefundPolicySaga extends AbstractAnnotatedSaga {
	private static final Logger LOG = LoggerFactory
			.getLogger(RefundPolicySaga.class);

	private boolean orderCancelled = false;
	private boolean shippingCancelled = false;
	private boolean productReturned = false;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void Handle(OrderCancelled message) {
		orderCancelled = true;
		CheckRefund();
	}

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void Handle(ShippingCancelled message) {
		shippingCancelled = true;
		CheckRefund();
	}

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void Handle(ProductReturned message) {
		productReturned = true;
		CheckRefund();
	}

	private void CheckRefund() {
		// Can also implement partial refunds here
		if (orderCancelled && (shippingCancelled || productReturned)) {
			LOG.info("Refund issued for order " + getSagaIdentifier());
			end();
		}
	}
}
