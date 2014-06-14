package org.pricing.events;


public class ProductPricingUpdated 
{
	private String productId;

	public ProductPricingUpdated(String productId) {
		super();
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	@Override
	public String toString() {
		return "ProductPricingUpdated [productId=" + productId + "]";
	}

}
