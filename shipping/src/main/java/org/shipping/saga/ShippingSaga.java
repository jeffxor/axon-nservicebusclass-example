package org.shipping.saga;

import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.billing.events.OrderBilled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class ShippingSaga extends AbstractAnnotatedSaga {
	private static final Logger LOG = LoggerFactory.getLogger(ShippingSaga.class);
	
	private boolean orderCancelled = false;
	private boolean orderBilled = false;
	private boolean orderAccepted = false;
	private boolean orderShipped = false;
	private boolean productReturned = false;
	
	
	public ShippingSaga() {
	}


	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(OrderBilled orderBilled)
    {
        LOG.info("Received Order Billed: {}", orderBilled.getOrderId());
    }
	

    private void CheckIfComplete()
    {
        if (orderCancelled)
        {
            if (orderShipped && productReturned)
            {
//                Bus.Publish<ProductReturned>(msg => msg.OrderId = Data.OrderId);
            }
            else if (!orderShipped)
            {
//                Bus.Publish<ShippingCancelled>(msg => msg.OrderId = Data.OrderId);
            }
            return;
        }

        if (orderBilled && orderAccepted)
        {
            LOG.info("Shipping Order: " + getSagaIdentifier());
            orderShipped = true;
        }
    }	
}
