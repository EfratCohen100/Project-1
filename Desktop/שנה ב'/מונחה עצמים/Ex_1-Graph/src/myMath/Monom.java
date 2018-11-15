
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	
	/**
	 * The function set the power and the coefficient
	 * @param a the coefficient of the monom
	 * @param b the power of the monom
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * The function initializes monom by monom ot
	 * @param ot represents monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
   /**
    * @return function returns power
    */
	public int get_power () 
	{
		return this._power;
	}
	/** 
	 * @return function returns coefficient
	 */
	public double get_coefficient () 
	{
		return round(this._coefficient);
	}
	
	/**
	 * This function represents the monom structure
	 * @param x the letter X
	 * @param a coefficient
	 * @param b power
	 * @param monomAns
	 * @return monom form
	 */
	public double f(double x)
	{
		double a = get_coefficient();
		int b = get_power();
		double monomAns= a*(Math.pow(x, b));
		return monomAns;
	}
	/**
	 * The function calculates the derivative of the monom
	 * @param a coefficient
	 * @param b power
	 * @return new monom - derivative
	 */
	public Monom derivative() 
	{
		double a = this.get_coefficient()*this.get_power();
		int b = this.get_power()-1;
		if(b<0) 
		{
			b=0;
		}
		return new Monom(a,b);	
	}
	/**
	 * @return the function returns string monom
	 */
	public String toString() 
	{
		return "" +this.get_coefficient()+"x^"+this.get_power();
	}
	/**
	 * The function checks for equality between monoms
	 * @param m1 monom
	 * @return true or false - if the monoms are equal or not
	 */
	public boolean isEqual(Monom m1) 
	{
		boolean monom=false;
		
		if(m1!=null)
		{
			if(this.get_power() == m1.get_power() && this.get_coefficient() == m1.get_coefficient())
			{
				monom=true;
			}
			else
			{
				monom=false;
			}
		}
		return monom;
		
	}
	/**
	 * The function multiplies two monoms
	 * @param m1 monom
	 */
	public void multiply(Monom m1)
	{
		this.set_coefficient(this.get_coefficient() * m1.get_coefficient());
		this.set_power(this.get_power() + m1.get_power());
	}
	/**
	 * The function multiplies two monoms
	 * @param m1 monom
	 * @param m2 monom
	 * @return A new monom where the monoms multiplication values are present
	 */
	static public Monom multiply(Monom m1,Monom m2)
	{
		Monom res = new Monom(0,0);
		res.set_coefficient(m1.get_coefficient() * m2.get_coefficient());
		res.set_power(m1.get_power() + m2.get_power());
		return res;
	}
	
	/**
	 * The function connects monoms from the same power
	 * @param m1 monom
	 */
	public void add(Monom m1)
	{
		if(this.get_power() != m1.get_power())
		{
			System.out.println("ERROR: It is not possible to add two monomes from a different power");
		}
		else
		{
			this.set_coefficient(this.get_coefficient() + m1.get_coefficient());
		}
	}
	/**
	 * The function subtracts monoms from the same power
	 * @param m1 monom
	 */
	public void sub(Monom m1)
	{
		if(m1.get_power()==this.get_power())
		{
			this.set_coefficient(this.get_coefficient() - m1.get_coefficient());
		}
	}
	
	/**
	 * The function multiplies by (-1) all the monoms we subtract in the subtraction function
	 * @return coefficient after doubling (-1)
	 */
	public Monom InvSign()
	{
		this. set_coefficient(this.get_coefficient()*-1);
		return this;
	}
	
	/**
	 * The function checks whether the power values and the coefficient of the monom are correct
	 * @return true or false
	 */
	public boolean validValues()
	{
		boolean validValues = true;
		if(this.get_power()<0)
		{
			validValues=false;
		}
		if(this.get_coefficient() == 0)
		{
			validValues=false;
		}
		return validValues;
	}
	
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	private void set_power(int p) 
	{
		if(p<0)
		{
			throw new Error("power can not be negative");
		}
		this._power = p;
	}
	public double round(double longValue)
	{
		return Math.round(longValue * 100.0) / 100.0;
	}
	
	private double _coefficient; 
	private int _power; 
}

