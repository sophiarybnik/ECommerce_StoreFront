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
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
	private HashMap<String, Product> products = new HashMap<String, Product>(); // Hashmap created to store the Product objects from the "products" text file
	private HashMap<Product, Integer> stats = new HashMap<Product, Integer>(); // Hashmap created to store "stats"- Product IDs mapped to the number of times the product was ordered
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
	private ArrayList<Book> books = new ArrayList<Book>(); // Arraylist to store books by the author, for sorting
    private ArrayList<Product> temp = new ArrayList<Product>(); // Arraylist to store products, for sorting

    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// Read the products file with a scanner and initialize the products HashMap using the CreateProductList() method, using the scanner as an argument. Close the FileReader once the map has been created.
    	try
    	{
        	FileReader reader = new FileReader("products.txt");
        	Scanner in = new Scanner(reader);
        	products = CreateProductList(in);
        	reader.close();
    	}
    	
    	// IO Exception is thrown if file is empty or does not exist
    	catch (IOException exception)
    	{
    		exception.printStackTrace();
    		System.exit(1);
    	}
    	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin", new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin", new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine", new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach", new Cart()));
    }
    
    public HashMap<String, Product> CreateProductList(Scanner in)
    {
    	HashMap<String, Product> listOfProducts = new HashMap<String, Product>();
    	String name;
    	String id;
    	String categoryString;
    	double price;
    	String stockCount;
    	String productOptions = null;
    	
    	
    	// File is read line by line, and a new product is created each 5 lines (according to how the information is set up in the text file)
    	while (in.hasNext())
    	{
    		categoryString = in.nextLine(); // Category is read and passed as a string and later converted to enum when new Product object is created
    		name = in.nextLine();
    		price = Double.parseDouble(in.nextLine()); // The price is read as a string and converted to a double
    		stockCount = (in.nextLine()); 
    		productOptions = in.nextLine();
    		id = generateProductId();
    		
    		
    		if (productOptions.isEmpty()) // If the 5th line is empty, it means that the product is a general product (i.e. not a book or pair of shoes)
    		{
    			int stockCountProduct = Integer.parseInt(stockCount); // The stock count is read as a string and converted to an integer
    			listOfProducts.put(id, new Product(name, id, price, stockCountProduct, categoryString)); // Product is created and stored in the HashMap
    		}    
    		else
    		{
    			String [] stockCount_split = stockCount.split(" "); // Stock count line is split into respective categories (paperback and hardcover) and stored in a list
    			String [] productOptions_split = productOptions.split (":");  // Product options line is split into strings Title and Author, and the Publishing Date is converted to an integer. All are stored in a list
    			listOfProducts.put(id, new Book(name, id, price, Integer.parseInt(stockCount_split[0]),Integer.parseInt(stockCount_split[1]), productOptions_split[0],productOptions_split[1], Integer.parseInt(productOptions_split[2]),categoryString)); // Book product is created and stored in HashMap
    		}
    	}

    	return listOfProducts;

    }
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    
    // Prints out all products available
    public void printAllProducts()
    {
        Set<String> keySet = products.keySet();     
        Iterator<String>  iterator = keySet.iterator();
        while (iterator.hasNext()) // Loops through Hashmap keys (product objects) 
        {
      	  String key = iterator.next();      	  
    	  Product p = products.get(key);
    		if ((p.getCategory() == Product.Category.BOOKS))
    		{
    			Book book = (Book) p; // If the product is a Book it needs to be printed using the over-ridden print function
    			p = book;
    		}
    			p.print();
    	}
    }
    
    
    // Function that prints out the number of times a product was ordered, sorted by frequency
    public void printStats()
    {
        // In order to sort the Hashmap by value (which is the frequency), the key/value pairs of the HashMap are put into an array list
        ArrayList<Map.Entry<Product, Integer> > statsAsList = new ArrayList<Map.Entry<Product, Integer>>(stats.entrySet());  
        Collections.sort(statsAsList, new OrderFrequencyComparator()); // The list of key/value pairs is sorted by value (frequency of ordering)
        
        HashMap<Product, Integer> temp = new LinkedHashMap<Product, Integer>(); // Temporary HashMap is created to hold the sorted key/value pairs
        for (Map.Entry<Product, Integer> stat : statsAsList) {
            temp.put(stat.getKey(), stat.getValue());
        }
        
        for (Map.Entry<Product, Integer> stat : temp.entrySet()) // The temporary HashMap is looped through and the product information is printed
        {
            System.out.printf("\nId: %-5s Name: %-20s Number of Orders: %d", stat.getKey().getId(), stat.getKey().getName(), stat.getValue());
        }
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
        Set<String> keySet = products.keySet();     
        Iterator<String>  iterator = keySet.iterator();
        while (iterator.hasNext())
        {
      	  String key = iterator.next();      	  
    	  Product p = products.get(key);
    	
    		if ((p.getCategory() == Product.Category.BOOKS))
    		{
    			Book book = (Book) p;
    			p = book;  // If the product is a Book it needs to be printed using the over-ridden print function
    			p.print();
    		}
    	}
    }
    
    // Print all current orders
    public void printAllOrders()
    {
        for (ProductOrder o:orders)
        {
        	o.print();
        }
    }
    
    // Print all shipped orders
    public void printAllShippedOrders()
    {
        for (ProductOrder o:shippedOrders)
        {
        	o.print();
        }
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer customer:customers)
    	{
    		customer.print();
    	}
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId 	
    	boolean customerExists = false;
    	Customer customer = new Customer(customerId);
    	
    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;
    		}
    	}
    	
    	if ((customerExists == false))
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	// Print `current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	for (int i = 0; i < orders.size(); i++)
    	{
    		customer = orders.get(i).getCustomer();
    		if (customer.getId().equals(customerId))
    		{
    			(orders.get(i)).print();
    		}
    	}
    	
    	// Print shipped orders of this customer 
    	System.out.println("\n\nShipped Orders of Customer " + customerId);
    	for (int i = 0; i < shippedOrders.size(); i++)
    	{
    		customer = shippedOrders.get(i).getCustomer();
    		if (customer.getId().equals(customerId))
    		{
    			(shippedOrders.get(i)).print();
    		}
    	}  
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)
    {
    	// First check to see if customer object with customerId exists in array list customers	
    	// Check to see if product object with productId exists in HashMap products
    	
    	boolean customerExists = false;
    	boolean productExists = products.containsKey(productId);

    	Product product = products.get(productId);

    	Customer customer = new Customer(customerId);
    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;	
    		}
    	}

        if ((productExists == false))
    	{
    	    throw new UnknownProductException("Product "+ productId + " Not Found");
    	}
    	
    	if (customerExists == false)
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	if (product.validOptions(productOptions)==false)
    	{
    		throw new InvalidProductOptionsException("Product " + product.getName() + " ProductId " + productId + " Invalid options: " + productOptions);
    	}
    	
    	if ((product.getCategory() == Product.Category.BOOKS))
		{
				product = (Book) product;
				if (productOptions.equals(""))
				{
				    throw new ProductIsBookException("Product "+ productId + " is a book. Re-enter command as \"orderbook\".");
				}
		}
		else if ((product.getCategory() == Product.Category.SHOES))
		{
				product = (Shoes) product;
				if (productOptions.equals(""))
				{
				    throw new ProductIsShoeException("Product "+ productId + " is a shoe. Re-enter command as \"ordershoes\"."); 
				}
			}
			
			if (!productOptions.equals(""))
			{
			    throw new JustProductException("To purchase Product "+ productId + ", re-enter command as \"order\".");
			}


    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	if (product.getStockCount(productOptions) <= 0)
    	{
    		throw new OutofStockException("Product "+ productId + " Out of Stock");
    	}
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
    	String ordernumber = this.generateOrderNumber();
    	product.reduceStockCount(productOptions);
    	orders.add(new ProductOrder(ordernumber, product, customer, productOptions));
    	
    	// If the product has already been ordered before, the value of the frequency is increased by 1 in the stats HashMap. If it is a first order, the product is added as a new element of the HashMap with order frequency = 1
    	if(!stats.containsKey(product)) {
    		 stats.put(product,1);
    		}
    		else {
    		 stats.put(product, stats.get(product)+1);
    		}
    	
    	return("Order #" + ordernumber); 
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address)
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	if (name == null || name.isEmpty())
    	{
    		throw new InvalidNameException("Invalid Customer Name");
    	}
    	
    	if (address == null || address.isEmpty())
    	{
    		throw new InvalidAddressException("Invalid Customer Address");
    	}
    	
    	// Create a Customer object and add to array list
    	customers.add(new Customer(generateCustomerId(),name, address, new Cart()));
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      // Check if order number exists first.
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order

    	for (int i = 0; i < orders.size(); i++)
   	{
    		ProductOrder order = orders.get(i);
    		if ((order.getOrderNumber()).equals(orderNumber))
    		{
    			orders.remove(i);
    			shippedOrders.add(order);
    			return(order);
    		}
    	}
		throw new InvalidOrderNumberException("Order " + orderNumber +" Not Found");
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
      // Check if order number exists first. 
    	for (int i = 0; i < orders.size(); i++)
   	{
    		ProductOrder order = orders.get(i);
    		if ((order.getOrderNumber()).equals(orderNumber))
    		{
    			orders.remove(i);
    			break;
    		}
    	}

		throw new InvalidOrderNumberException("Order " + orderNumber +" Not Found");
    }
 
    public void addProductToCart(String productId, String customerId, String productOptions)
    {
    	boolean customerExists = false;
    	boolean productExists = products.containsKey(productId);
    	Product product = products.get(productId);
    	Customer customer = new Customer(customerId);

    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;	
    		}
    	}
    	
        if ((productExists == false))
    	{
    	    throw new UnknownProductException("Product "+ productId + " Not Found");
    	}
    	
    	if (customerExists == false)
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	if (product.validOptions(productOptions)==false)
    	{
    		throw new InvalidProductOptionsException("Product " + product.getName() + " ProductId " + productId + " Invalid options: " + productOptions);
    	}
    	
    	if ((product.getCategory() == Product.Category.BOOKS))
		{
			product = (Book) product;
		}
		else if ((product.getCategory() == Product.Category.SHOES))
		{
			product = (Shoes) product;
		}
    	
    	
    	customer.addToCart(product, productOptions);
    }
   
    
    public void printCart(String customerId)
    {
    	boolean customerExists = false;
    	Customer customer = new Customer(customerId);
    	
    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;	
    		}
    	}
    	if (customerExists == false)
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	customer.printCart();

    }
    
    public void remCartItem(String customerId, String productId)
    {
    	boolean customerExists = false;
    	boolean productExists = false;

    	Customer customer = new Customer(customerId);
    	
    	//Customer customer;
    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;	
    		}
    	}

    	if (customerExists == false)
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	if (customer.containsItem(productId)) // Check to see if customer's cart contains item before removing it from cart
    	{
    		productExists = true;
    	}
    	

        if ((productExists == false))
    	{
    	    throw new UnknownProductException("Product "+ productId + " Not Found");
    	}
    	
    	
    	customer.removeCartItem(productId);

    }
 
    public ArrayList<String> orderItems(String customerId)
    {
    	boolean customerExists = false;
    	Customer customer = new Customer(customerId);
    	
    	for (int i = 0;i<customers.size();i++)
    	{
    		if ((customers.get(i)).getId().equals(customerId))
    		{
    			customer = customers.get(i);
    			customerExists = true;
    			break;	
    		}
    	}
    	
    	if (customerExists == false)
    	{
    	    throw new UnknownCustomerException("Customer "+ customerId + " Not Found");
    	}
    	
    	return customer.orderCartItems(customerId);
    	
    }

    

    public void printBooksByAuthor(String author)
    {
        // Make sure author exists - check using author name
      	// If author does not exist, set errMsg String and return false
    	
   	boolean authorExists = false;    	
        Set<String> keySet = products.keySet();     
        Iterator<String>  iterator = keySet.iterator();
        while (iterator.hasNext())
        {
      	  String key = iterator.next();      	  
    	  Product p = products.get(key);
    		if (p.getCategory()== Product.Category.BOOKS)
    		{
    			Book book = (Book) p;
    			if ((book.getAuthor()).equals(author))
    			{
    				authorExists = true;
        			books.add(book);
    			}	
    		}
    	}
    	
    	if ((authorExists == false))
    	{
    		throw new InvalidAuthorException("Author "+ author + " Not Found");
    	}
    	// Sort the books arraylist in reverse order, by year of publishing
    	Collections.sort(books, Collections.reverseOrder(new BookYearComparator()));
    	
    	// Print each book product written by the given Author (using the overridden method in class Book)
    	for (int i = 0;i<books.size();i++)
    	{
			Book book = books.get(i);
    		book.print();
    	}
    	
    	// Reset the 'books' arraylist back to empty
    	books = new ArrayList<Book>();
    }
    
    // Sort products by increasing price
    public void printByPrice()
    {
    	Set<String> keySet = products.keySet();     
        Iterator<String>  iterator = keySet.iterator();
        while (iterator.hasNext())
        {
      	  String key = iterator.next();      	  
    	  Product p = products.get(key);
    	  temp.add(p);		
        }
        Collections.sort(temp);
        
        for (Product p:temp)
        {
        	p.print();
        }
        temp = new ArrayList<Product>(); // clear arraylist
    }

    // Sort products alphabetically by product name
    // Please refer to ProductNameComparator.java
    public void printByName()
    {
    	Set<String> keySet = products.keySet();     
        Iterator<String>  iterator = keySet.iterator();
        while (iterator.hasNext())
        {
      	  String key = iterator.next();      	  
    	  Product p = products.get(key);
    	  temp.add(p);		
        }
        Collections.sort(temp, new ProductNameComparator());
        for (Product p:temp)
        {
        	p.print();
        }
        temp = new ArrayList<Product>(); // clear arraylist
    }
    
        
    // Sort customers alphabetically by product name
    // Please refer to CustomerNameComparator.java

    public void sortCustomersByName()
    {
        Collections.sort(customers, new CustomerNameComparator());
    }
    
    // Sort products by increasing price
    // Please refer to BookYearComparator.java

    public void sortByYear()
    {
        Collections.sort(books, new BookYearComparator());
    }
}


// Custom Error messages; caught in "ECommerceUserInterface"
class UnknownCustomerException extends RuntimeException
{ 
    public UnknownCustomerException () {}
    public UnknownCustomerException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class UnknownProductException extends RuntimeException
{ 
    public UnknownProductException () {}
    public UnknownProductException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class InvalidProductOptionsException extends RuntimeException
{ 
    public InvalidProductOptionsException () {}
    public InvalidProductOptionsException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class OutofStockException extends RuntimeException
{ 
    public OutofStockException () {}
    public OutofStockException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class InvalidNameException extends RuntimeException
{ 
    public InvalidNameException () {}
    public InvalidNameException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class InvalidAddressException extends RuntimeException
{ 
    public InvalidAddressException () {}
    public InvalidAddressException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class InvalidOrderNumberException extends RuntimeException
{ 
    public InvalidOrderNumberException () {}
    public InvalidOrderNumberException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class ProductIsBookException extends RuntimeException
{ 
    public ProductIsBookException () {}
    public ProductIsBookException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class ProductIsShoeException extends RuntimeException
{ 
    public ProductIsShoeException () {}
    public ProductIsShoeException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class JustProductException extends RuntimeException
{ 
    public JustProductException () {}
    public JustProductException (String errorMessage) 
    {
        super(errorMessage);
    }
}

class InvalidAuthorException extends RuntimeException
{ 
    public InvalidAuthorException () {}
    public InvalidAuthorException (String errorMessage) 
    {
        super(errorMessage);
    }
}



