package org.billing;

import java.util.Scanner;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.billing.events.OrderBilled;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BillingApp 
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		    new ClassPathXmlApplicationContext(new String[] {"billing.xml", "axon-eventbus.xml", 
    		    		"amqp-config.xml", "persistence-infrastructure-context.xml"});
    	EventBus eventBus = context.getBean(EventBus.class);
    	
    	Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter customer ID:");
            String customerId = scanner.nextLine();
            eventBus.publish(new GenericEventMessage<OrderBilled>(new OrderBilled(customerId)));
        }
    }
}
