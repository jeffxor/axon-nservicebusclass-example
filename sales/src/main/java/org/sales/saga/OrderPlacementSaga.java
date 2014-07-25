package org.sales.saga;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.sales.commands.CancelOrder;
import org.sales.commands.PlaceOrder;
import org.sales.events.OrderAccepted;
import org.sales.events.OrderCancelled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class OrderPlacementSaga extends AbstractAnnotatedSaga {
	private static final Logger LOG = LoggerFactory
			.getLogger(OrderPlacementSaga.class);
	public static final Long ORDER_READYTO_BE_PLACED_TIMEOUT = new Long(30L); 

	
	private boolean orderCancelled;
	private boolean orderAccepted;
	@Autowired 
	private transient EventBus eventBus;
	@Autowired 
	private transient EventScheduler eventScheduler;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(PlaceOrder message)
    {
        LOG.info("Place Order request received " + message.getOrderId());
        eventScheduler.schedule(Duration.standardSeconds(ORDER_READYTO_BE_PLACED_TIMEOUT), new OrderReadyToBePlaced());
    }

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
    public void Handle(CancelOrder message)
    {
		LOG.info("Cancel Order request received " + message.getOrderId());
		
        if (orderCancelled)
            return;

        orderCancelled = true;
        if (orderAccepted)
        {
        	eventBus.publish(new GenericEventMessage<OrderCancelled>(new OrderCancelled(getSagaIdentifier())));
        }
        LOG.info("Order Cancelled " + getSagaIdentifier());
    }

    public void Timeout(OrderReadyToBePlaced state)
    {
        if (!orderCancelled)
        {
            orderAccepted = true;
            
            LOG.info("Order Accepted " + getSagaIdentifier());
            eventBus.publish(new GenericEventMessage<OrderAccepted>(new OrderAccepted(getSagaIdentifier())));
        }
    }
}

