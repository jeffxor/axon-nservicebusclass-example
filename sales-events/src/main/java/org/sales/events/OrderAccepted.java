package org.sales.events;

public class OrderAccepted 
{
	private String orderId;

	public OrderAccepted(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "OrderAccepted [orderId=" + orderId + "]";
	}
	
}
