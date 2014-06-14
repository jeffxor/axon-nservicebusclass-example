package org.sales.events;

public class OrderCancelled 
{
	private String orderId;

	public OrderCancelled(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "OrderCancelled [orderId=" + orderId + "]";
	}
	
}
