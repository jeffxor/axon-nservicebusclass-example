package org.crm;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.crm.events.CustomerStatusUpdated;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CrmApp 
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		    new ClassPathXmlApplicationContext(new String[] {"billing.xml"});
    	CommandBus commandBus = context.getBean(CommandBus.class);
        while (true)
        {
            System.out.println("Enter customer ID:");
            String orderId = System.console().readLine();
            commandBus.dispatch(new GenericCommandMessage<CustomerStatusUpdated>(new CustomerStatusUpdated(orderId)));            
        }
    }
}
