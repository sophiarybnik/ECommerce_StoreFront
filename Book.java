/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */

/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product
{
  private String author;
  private String title;
  private int year;
  
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year, String categoryString)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS
	  super(name, id, price, 100000, categoryString);
	  this.title = title;
	  this.author = author;
	  this.year = year;
	  this.paperbackStock = paperbackStock;
	  this.hardcoverStock = hardcoverStock;
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
	if ((productOptions.toLowerCase()).equals("paperback") || (productOptions.toLowerCase()).equals("hardcover") || (productOptions.toLowerCase()).equals("ebook"))
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
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.

	if ((productOptions.toLowerCase()).equals("paperback"))
			 return this.paperbackStock;
	 else if ((productOptions.toLowerCase()).equals("hardcover"))
			 return this.hardcoverStock;
			 
	 else
		 return super.getStockCount(productOptions);
			//return 1;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
	  
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
	  
		 if ((productOptions.toLowerCase()).equals("paperback")) 
				 paperbackStock = stockCount;
		 
		 else if ((productOptions.toLowerCase()).equals("hardcover")) 
				 hardcoverStock = stockCount;
				 
		 else
			 super.setStockCount(stockCount, productOptions);
	}
  
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
		 if ((productOptions.toLowerCase()).equals("paperback"))
				 paperbackStock--;
		 
		 else if ((productOptions.toLowerCase()).equals("hardcover")) 
				 hardcoverStock--;
				 
		 else
			 super.reduceStockCount(productOptions);
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video
	  //
	  super.print();


	  System.out.printf("       Book Title: %-40s Author: %-30s Year: %-5s", this.title, this.author, this.year);

  }
  
	public String getAuthor()
	{
		return this.author;
	}
	
	public int getYear()
	{
		return this.year;
	}
}
