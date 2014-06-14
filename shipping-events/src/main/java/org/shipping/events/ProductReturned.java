package org.shipping.events;

public class ProductReturned 
{
	private String orderId;

	public ProductReturned(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "ProductReturned [orderId=" + orderId + "]";
	}
	
}
