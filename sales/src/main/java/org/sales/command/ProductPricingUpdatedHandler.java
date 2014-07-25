package org.sales.command;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.pricing.events.ProductPricingUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductPricingUpdatedHandler 
{
	private static final Logger LOG = LoggerFactory.getLogger(ProductPricingUpdatedHandler.class);	
	
	@EventHandler
    public void Handle(ProductPricingUpdated message)
    {
        LOG.info("Received Product Pricing Updated: {}", message.getProductId());
    }
}
