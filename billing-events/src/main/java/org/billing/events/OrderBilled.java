package org.billing.events;

public class OrderBilled 
{
	private String orderId;

	public OrderBilled(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "OrderBilled [orderId=" + orderId + "]";
	}
	
}
