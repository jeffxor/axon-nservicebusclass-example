package org.sales.commands;


public class PlaceOrder 
{
	private String orderId;

	public PlaceOrder(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "PlaceOrder [orderId=" + orderId + "]";
	}
	
}
