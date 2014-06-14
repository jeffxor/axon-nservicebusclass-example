package org.sales.commands;


public class CancelOrder 
{
	private String orderId;

	public CancelOrder(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "CancelOrder [orderId=" + orderId + "]";
	}
	
}
