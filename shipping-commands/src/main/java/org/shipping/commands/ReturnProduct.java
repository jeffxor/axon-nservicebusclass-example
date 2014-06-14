package org.shipping.commands;

public class ReturnProduct 
{
	private String orderId;

	public ReturnProduct(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "ReturnProduct [orderId=" + orderId + "]";
	}
	
}
