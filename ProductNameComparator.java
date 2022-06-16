/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */

import java.util.Comparator;

    public class ProductNameComparator implements Comparator<Product>
    {
    	public int compare(Product p1, Product p2)
    	{
    		return p1.getName().compareTo(p2.getName());
    	}
    }

