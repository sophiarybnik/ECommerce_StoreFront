/*
 * Name: Sophia Rybnik
 * Student Number: 501015789
 */

import java.util.Comparator;
public class BookYearComparator implements Comparator<Book>
{
	public int compare(Book b1, Book b2)
	{
		return b1.getYear()-b2.getYear();
	}
}




