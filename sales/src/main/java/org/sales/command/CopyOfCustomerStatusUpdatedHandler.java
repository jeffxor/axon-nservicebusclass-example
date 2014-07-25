package org.sales.command;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.crm.events.CustomerStatusUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CopyOfCustomerStatusUpdatedHandler 
{
	private static final Logger LOG = LoggerFactory.getLogger(CopyOfCustomerStatusUpdatedHandler.class);	
	
	@EventHandler
    public void Handle(CustomerStatusUpdated message)
    {
        LOG.info("Received Customer Status Updated: {}", message.getCustomerId());
    }
}
