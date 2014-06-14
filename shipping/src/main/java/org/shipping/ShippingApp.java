package org.shipping;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.shipping.commands.ReturnProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShippingApp 
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
            commandBus.dispatch(new GenericCommandMessage<ReturnProduct>(new ReturnProduct(customerId)));            
        }
    }
}
