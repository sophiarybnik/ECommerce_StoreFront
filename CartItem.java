/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
public class CartItem {
	/*
	 *  Class CartItem contains a reference to a Product object and a 
reference to a productOptions string. Create appropriate variables and methods for 
these two new classes. 
	 */
	
	
	private Product product;
	  private String productOptions;
	  
	  public CartItem(Product product, String productOptions)
	  {
		  this.product = product;
		  this.productOptions = productOptions;
	  }
	  
	  public Product getProduct()
	  {
		  return this.product;
	  }
	  
	  public String getproductOptions()
	  {
		  return this.productOptions;
	  }
}
