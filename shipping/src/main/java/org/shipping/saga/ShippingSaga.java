
package org.shipping.saga;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.billing.events.OrderBilled;
import org.sales.events.OrderAccepted;
import org.sales.events.OrderCancelled;
import org.shipping.commands.ReturnProduct;
import org.shipping.events.ShippingCancelled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private transient EventBus eventBus;
	
	public ShippingSaga() {
	}

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(OrderBilled orderBilled)
    {
        LOG.info("Received Order Billed: {}", orderBilled.getOrderId());
    }
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(OrderAccepted message)
    {
		orderAccepted = true;
		
		LOG.info("Received Order Accepted: " + message.getOrderId());
        CheckIfComplete();
    }

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(OrderCancelled message)
    {
        orderCancelled = true;

        LOG.info("Shipping cancelled. " + message.getOrderId());
        CheckIfComplete();
    }

	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(ReturnProduct message)
    {
        productReturned = true;
        
        LOG.info("Product Returned: " + message.getOrderId());        
        CheckIfComplete();
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
            	eventBus.publish(new GenericEventMessage<ShippingCancelled>(new ShippingCancelled(getSagaIdentifier())));
            }
            return;
        }

        if (orderBilled && orderAccepted)
        {
            LOG.info("Shipping Order: " + getSagaIdentifier());
            orderShipped = true;
        }
    }

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
    
}
