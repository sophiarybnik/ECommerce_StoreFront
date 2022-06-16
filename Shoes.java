/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.ArrayList;

/* Shoes ARE A product that have additional information - e.g. shoe size, shoe colour
 	 
*/


public class Shoes extends Product 
{
  private String options = "6 brown 6 black 7 brown 7 black 8 brown 8 black 9 brown 9 black 10 brown 10 black";
  int brown_size6_stock;
  int black_size6_stock;
  int brown_size7_stock;
  int black_size7_stock;
  int brown_size8_stock;
  int black_size8_stock;
  int brown_size9_stock;
  int black_size9_stock;
  int brown_size10_stock;
  int black_size10_stock;
 

 public Shoes(String name, String id, double price, int brown_size6_stock, int black_size6_stock, int brown_size7_stock, int black_size7_stock, int brown_size8_stock, int black_size8_stock, int brown_size9_stock, int black_size9_stock, int brown_size10_stock, int black_size10_stock)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Shoes instance variables. 
  	 // Set category to SHOES
	  super(name, id, price, 100000, "SHOES");

	  this.brown_size6_stock= brown_size6_stock;
	  this.black_size6_stock= black_size6_stock;
	  this.brown_size7_stock= brown_size7_stock;
	  this.black_size7_stock= black_size7_stock;
	  this.brown_size8_stock= brown_size8_stock;
	  this.black_size8_stock= black_size8_stock;
	  this.brown_size9_stock= brown_size9_stock;
	  this.black_size9_stock= black_size9_stock;
	  this.brown_size10_stock= brown_size10_stock;
	  this.black_size10_stock= black_size10_stock;

  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for sizes 6, 7, 8, 9 or 10 and colours black or brown
  	// if it is one of these, return true, else return false
	boolean contains = this.options.contains(productOptions);
	if (contains == true)
  	{
		return true;
  	}
  	
	else
  {
		return false;
  }
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for the correct size and colour, ex. size 6 brown

	switch(productOptions)  
	{
	case "6 brown": return this.brown_size6_stock;
	case "6 black": return this.black_size6_stock;
	case "7 brown": return this.brown_size6_stock;
	case "7 black": return this.black_size7_stock;
	case "8 brown": return this.brown_size8_stock;
	case "8 black": return this.black_size8_stock;
	case "9 brown": return this.brown_size9_stock;
	case "9 black": return this.black_size9_stock;
	case "10 brown": return this.brown_size10_stock;
	case "10 black": return this.black_size10_stock;
	}
	return 0;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
	  
    // Use the productOptions to check for (and set) the number of stock for the correct size and colour, ex. size 6 brown
   	// Use the stock variables at the top. 
			switch(productOptions)  
			{
			case "6 brown": brown_size6_stock = stockCount; break;
			case "6 black": black_size6_stock = stockCount; break;
			case "7 brown":  brown_size7_stock = stockCount; break;
			case "7 black": black_size7_stock = stockCount; break;
			case "8 brown":  brown_size8_stock = stockCount; break;
			case "8 black": black_size8_stock = stockCount; break;
			case "9 brown":  brown_size9_stock = stockCount; break;
			case "9 black": black_size9_stock = stockCount; break;
			case "10 brown":  brown_size10_stock = stockCount; break;
			case "10 black": black_size10_stock = stockCount; break;
			}
	}
  
  
  /*
   * When a pair of shoes is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for the correct size and colour, ex. size 6 brown
   	// Use the stock variables at the top. 
			switch(productOptions)  
			{
		
			case "6 brown": brown_size6_stock--; break;
			case "6 black": black_size6_stock--; break;
			case "7 brown": brown_size7_stock--; break;
			case "7 black": black_size7_stock--; break;
			case "8 brown": brown_size8_stock--; break;
			case "8 black": black_size8_stock--; break;
			case "9 brown": brown_size9_stock--; break;
			case "9 black": black_size9_stock--; break;
			case "10 brown": brown_size10_stock--; break;
			case "10 black": black_size10_stock--; break;
			
			}
	}
  /*
   * Print product information in super class
   */
  public void print()
  {
	  super.print();

  }
}

