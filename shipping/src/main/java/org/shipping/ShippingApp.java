package org.shipping;

import java.util.Scanner;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.shipping.commands.ReturnProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShippingApp 
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		    new ClassPathXmlApplicationContext(new String[] {"shipping.xml", "axon-eventbus.xml", 
    		    		"amqp-config.xml", "persistence-infrastructure-context.xml"});
    	EventBus eventBus = context.getBean(EventBus.class);
    	
    	Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter order ID:");
            String orderId = scanner.nextLine();
            eventBus.publish(new GenericEventMessage<ReturnProduct>(new ReturnProduct(orderId)));
        }
    }    
}
