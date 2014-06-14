package org.sales;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.sales.commands.CancelOrder;
import org.sales.commands.PlaceOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SalesApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "billing.xml" });
		CommandBus commandBus = context.getBean(CommandBus.class);
		int i = 0;
		while (true) {
			System.out.println("Enter P to place and C to cancel an order...");
			String option = System.console().readLine();
			switch (option) {
			case "P":
				i++;
				System.out.println("Your order number is: " + i);
				commandBus.dispatch(new GenericCommandMessage<PlaceOrder>(new PlaceOrder(String.valueOf(i))));
				break;
			case "C":
				System.out.println("Enter order to cancel: ");
				String orderId = System.console().readLine();
				commandBus.dispatch(new GenericCommandMessage<CancelOrder>(new CancelOrder(orderId)));
				break;
			}
		}

	}
}
