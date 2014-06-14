package org.shipping.events;

public class ShippingCancelled 
{
	private String orderId;

	public ShippingCancelled(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "ShippingCancelled [orderId=" + orderId + "]";
	}
	
}
