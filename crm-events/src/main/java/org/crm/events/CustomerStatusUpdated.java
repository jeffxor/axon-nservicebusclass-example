package org.crm.events;

public class CustomerStatusUpdated 
{
	private String customerId;

	public CustomerStatusUpdated(String customerId) {
		super();
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	@Override
	public String toString() {
		return "CustomerStatusUpdated [customerId=" + customerId + "]";
	}
	
}
