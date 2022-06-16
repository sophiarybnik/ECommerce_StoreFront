/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.ArrayList;

public class Cart
{
private ArrayList<CartItem> cartItems; // Each customer has an ArrayList of CartItems
  public Cart()
	  {
		  this.cartItems = new ArrayList<CartItem>();
	  }
	
 public void addItem(Product product, String productOptions) // Function to add an item a customer's cart
 {
 	CartItem cartItem =  new CartItem(product, productOptions);
	cartItems.add(cartItem);
	}
 
 public void removeItem(String productId)  // Function to remove an item from a customer's cart
 {
	 for (CartItem cartItem:cartItems)
	 {
		if ((cartItem.getProduct()).getId().equals(productId))
		{
			cartItems.remove(cartItem);
			break;
		}
	 }
	}
 
 public boolean contains(String productId)   // Function to check if the customer's cart contains a product Id before removing it from cart
 {
	 	return this.cartItems.contains(productId);
}
 
 public ArrayList<String> order(String customerId)
 {
	 ArrayList<String> orders = new ArrayList<String>(); 

	 for (CartItem cartItem:cartItems)
	 {
		 orders.add(cartItem.getProduct().getId());
		 orders.add(customerId);
		 orders.add(cartItem.getproductOptions()); 
	 }
	  this.cartItems = new ArrayList<CartItem>(); //Clears the cart once the products have been ordered
	  return orders;
	}
 
 public void print()
 {
	 for (CartItem cartItem:cartItems)
	 {
		(cartItem.getProduct()).print();
		System.out.print("\t\tFormat: " + cartItem.getproductOptions()); 
	 }
 }
}
