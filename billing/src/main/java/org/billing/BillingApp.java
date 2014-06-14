package org.billing;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.billing.events.OrderBilled;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BillingApp 
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		    new ClassPathXmlApplicationContext(new String[] {"crm.xml"});
    	CommandBus commandBus = context.getBean(CommandBus.class);
        while (true)
        {
            System.out.println("Enter customer ID:");
            String customerId = System.console().readLine();
            commandBus.dispatch(new GenericCommandMessage<OrderBilled>(new OrderBilled(customerId)));            
        }
    }
}
