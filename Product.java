/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.Comparator;

/*
 * class Product defines a product for sale by the system. 
 * 
 * A product belongs to one of the 5 categories below. 
 * 
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods  
 * 
 * Some products
 */
public class Product implements Comparable<Product>
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES};
	
	private String name;
	private String id;
	private Category category;
	private String categoryString; 	//category String as temp variable used to determine the product category from the file input
	private double price;
	private int stockCount;
	
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.categoryString = "GENERAL";
		this.category = Category.GENERAL;
		this.stockCount = 0;
	}
	
	public Product(String id)
	{
		this("Product", id, 0, 0, "GENERAL");
	}
	

	public Product(String name, String id, double price, int stock, String categoryString)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		//Assigning the category by the input from the products file
		if (categoryString.equals("GENERAL"))
		{
			this.category = Category.GENERAL;
		}
		else if (categoryString.equals("CLOTHING"))
		{
			this.category = Category.CLOTHING;
		}
		if (categoryString.equals("BOOKS"))
		{
			this.category = Category.BOOKS;
		}
		if (categoryString.equals("FURNITURE"))
		{
			this.category = Category.FURNITURE;
		}
		if (categoryString.equals("COMPUTERS"))
		{
			this.category = Category.COMPUTERS;
		}
		if (categoryString.equals("SHOES"))
		{
			this.category = Category.SHOES;
		}
	}
	/*
	 * This method always returns true in class Product. In subclasses, this method will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions)
	{
		return true;
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions)
	{
		return stockCount;
	}
	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		this.stockCount = stockCount;
	}
	/*
	 * Reduce the number of items currently in stock for this product by 1 (called when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOIptions)
	{
		stockCount--;
	}
	
	public void print()
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}
	
	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other)
	{
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}
	
    // Implement Comparable interface to compare product prices
    public int compareTo(Product other)
    {
       if (price > other.price)  return 1;
       if (price < other.price) return -1;
       return 0; 
    }
    
}
