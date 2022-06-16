/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */
import java.util.Comparator;
import java.util.Map;
public class OrderFrequencyComparator  implements Comparator<Map.Entry<Product, Integer>>
{
    public int compare(Map.Entry<Product, Integer> a, Map.Entry<Product, Integer> b)
	{
    		return (a.getValue()).compareTo(b.getValue());
	} 

}

