/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.Scanner;
import java.io.FileReader;  
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer 
{
	private String id;  
	private String name;
	private String shippingAddress;
	private Cart cart;
	
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
	}
	
	public Customer(String id, String name, String address, Cart cart)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.cart = cart;
	}
	
	public void addToCart(Product product, String productOptions)
	{
		this.cart.addItem(product, productOptions);
	}
	
	public void printCart()
	{
		this.cart.print();
	}
	
	 public boolean containsItem(String productId)  
	 {
		 	return this.cart.contains(productId);
	}
	
	public void removeCartItem(String productId)
	{
		this.cart.removeItem(productId);
	}

	public ArrayList<String> orderCartItems(String customerId)
	{
		return this.cart.order(customerId);
	}

	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
	
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}
	
}
