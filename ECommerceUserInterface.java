/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		
		// Process keyboard actions
		
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			try
			{
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("ADDTOCART")) // Add product to customer cart
			{
		    	//add something about depending on category what the product options are

				String productId = "";
				String customerId = "";
				String productOptions = "";


				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				System.out.print("\nIf product is a book, enter format  of choice (Paperback/Hardcover/EBook). If not, press Enter: ");

				  // Get customer Id from scanner
					if (scanner.hasNextLine())
						productOptions = scanner.nextLine();
					
				amazon.addProductToCart(productId, customerId, productOptions);
			}
			
			else if (action.equalsIgnoreCase("PRINTCART"))
			{
				String customerId = "";
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				amazon.printCart(customerId);
			}
			
			else if (action.equalsIgnoreCase("REMCARTITEM"))
			{
				String customerId = "";
				String productId = "";
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				System.out.print("Product Id: ");
				  // Get product Id from scanner
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
				
				amazon.remCartItem(customerId, productId);
			}
			
			else if (action.equalsIgnoreCase("ORDERITEMS"))
			{
				String customerId = "";
				ArrayList<String> itemsToOrder = new ArrayList<String>();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				itemsToOrder = amazon.orderItems(customerId);
				
				for (int i = 0; i < itemsToOrder.size(); i+=3)
				{	String success = amazon.orderProduct(itemsToOrder.get(i),itemsToOrder.get(i+1),itemsToOrder.get(i+2));
					System.out.println(success);
				}
			}
			
			else if (action.equalsIgnoreCase("STATS"))	// List all products for sale
			{
				amazon.printStats(); 
			}
			
			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				amazon.createCustomer(name, address);
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					
					ProductOrder success = amazon.shipOrder(orderNumber);
					success.print();

			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				amazon.printOrderHistory(customerId);
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";
				String productOptions = "";


				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				
				String success = amazon.orderProduct(productId, customerId, productOptions);
				System.out.println(success);
					
				// Print Order Number string returned from method in ECommerceSystem
				

			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String productOptions = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				if (scanner.hasNextLine())
				// get book format and store in options string
					productOptions = scanner.nextLine();
				
				
				// Order product. Check for error mesage set in ECommerceSystem
				// Print order number string if order number is not null
				String success = amazon.orderProduct(productId, customerId, productOptions);
				System.out.println(success);
				
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String productOptions = "";
				
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if (scanner.hasNextLine())
					productOptions = scanner.nextLine() + " ";
				
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
					productOptions += ((scanner.nextLine()).toLowerCase());
				
				//order shoes
				String success = amazon.orderProduct(productId, customerId, productOptions);
				System.out.println(success);
			}
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				
				
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				
				amazon.cancelOrder(orderNumber);	
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.printByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.printByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
				amazon.printCustomers();

			}
			
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // print books by given author, in order of year published
			{
				String author = "";

				System.out.print("Author Name: ");
				// Get Author Name from scanner
				if (scanner.hasNextLine())
					author = scanner.nextLine();
				// Print all books by this author in order of year published
				amazon.printBooksByAuthor(author);
				amazon.sortByYear();
			
			}
			System.out.print("\n>");
		}
			catch(UnknownCustomerException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}

			catch(UnknownProductException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}
			
			catch(InvalidProductOptionsException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}
			
			catch(OutofStockException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}
			
			catch(InvalidNameException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}
			
			catch(InvalidAddressException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");
			}
			
			catch(InvalidOrderNumberException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");

			}
			
			catch (JustProductException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");

			}
			
			catch (ProductIsShoeException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");

			}
			
			catch (ProductIsBookException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");

			}
			
			catch (InvalidAuthorException exception)
			{
				System.out.println(exception.getMessage());
				System.out.print("\n>");

	
			}
				
		}
	}
	}
	

