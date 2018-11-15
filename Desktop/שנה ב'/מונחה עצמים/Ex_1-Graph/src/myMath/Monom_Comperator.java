package myMath;

import java.util.Comparator;
/**
 * Comparison of powers between monoms
 * @author Efrat and Odelia
 *
 */
public class Monom_Comperator implements Comparator<Monom> {
	/**
	 * The function compares the powers of the monoms
	 */
	
	@Override
	public int compare(Monom n1, Monom n2) {
		// TODO Auto-generated method stub
		
		if(n1.get_power()>n2.get_power()) 
		{
			return 1;
		}
		
		if(n1.get_power()<n2.get_power()) 
		{
			return -1;
		}
		
		return 0;
	}

}
