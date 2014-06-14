package org.pricing;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.pricing.events.ProductPricingUpdated;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PricingApp 
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		    new ClassPathXmlApplicationContext(new String[] {"billing.xml"});
    	CommandBus commandBus = context.getBean(CommandBus.class);
        while (true)
        {
            System.out.println("Enter customer ID:");
            String productId = System.console().readLine();
            commandBus.dispatch(new GenericCommandMessage<ProductPricingUpdated>(new ProductPricingUpdated(productId)));            
        }
    }
}
