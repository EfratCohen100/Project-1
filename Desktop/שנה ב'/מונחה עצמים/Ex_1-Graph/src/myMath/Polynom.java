package myMath;
import javmos2.GraphGUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Efrat and Odelia
 *
 */


public class Polynom implements Polynom_able{

	public static Monom_Comperator MonComp= new Monom_Comperator();
	private ArrayList<Monom> lstMon;

	/**
	 * constructor
	 */
	public Polynom() 
	{
		this.lstMon = new ArrayList<>();


	}
	/**
	 * Validate that string not contains alphabetic chars. 
	 * @param the function get string
	 * @return boolean 
	 */
	boolean IsValidArithmetic(String str)
	{
		char[]dstChars = new char[str.length()];int srcBegin=0;int srcEnd = str.length();int dstBegin = 0;
		str.getChars(srcBegin, srcEnd, dstChars, dstBegin);
		for (char c : dstChars) {
			if(!Character.isDigit(c) || Character.isAlphabetic(c))
			{
				if(c!='x'&&c!='X'&&c!='-'&&c!='+'&&c!='^'&&c!='.')
				{
					System.out.println("Error: expression shouldn't contains Alphabetic characters. ");
					return false;
				}
			}
		}

		return true;

	}

	/**
	 * Validate that string not contains alphabetic chars. 
	 * constructor
	 * @param the function get string
	 * @return polynom- Checking proper polynomial 
	 */
	public Polynom (String strPol) 
	{
		if(IsValidArithmetic(strPol) == true)
			lstMon = GetMonoms(strPol);
		else
			throw new NullPointerException("String Format Error!"); 
	}
	 /**
	 * return the powers in string. 
	 * @param the function get string
	 * @return Integer[]  
	 */
	public Integer[] GetPower(String strPlynom)
	{
		String polynomial= strPlynom;
		String[] parts = polynomial.split("x\\^\\d+\\+?");
		ArrayList<Integer> lstCoff = new ArrayList<>();
		for (String part : parts) {
			//System.out.println(part);
			int intVal = Integer.parseInt(part);
			lstCoff.add( intVal);
		}

		Integer list2[] = new Integer[lstCoff.size()];
		list2 = lstCoff.toArray(list2);
		return list2;

	}
	/**
	 * Get the Monoms in string. 
	 * @param the function get string
	 * @return ArrayList<Monom>
	 */
	public ArrayList<Monom> GetMonoms(String strPlynom)
	{
		 String polynomial= strPlynom;
		//polynomial = polynomial.replaceAll("+-", "-");
		String operations = ""; 
		String[] parts = polynomial.split("[+/-]");
		
		char[] chArr = new char[polynomial.length()];
		polynomial.getChars(0, polynomial.length(), chArr, 0);
		
		if(chArr[0] != '-') 
			operations+='+';
		
		for (char sign :chArr)//
		{
			if(sign == '-')
			{
				operations+='-';
			}	
			else if(sign == '+')
			{
				operations+='+';
			}
		}
		
		ArrayList<Double> lstCoff = new ArrayList<>();
		ArrayList<Integer> lstPow = new ArrayList<>();
		int idxOper =0;
		for (int idx=0;idx<parts.length;idx++)//(String part : parts) 
		{
			String part = parts[idx];
			if(part.isEmpty())
				continue;
			
			int sign = (operations.charAt(idxOper++) == '-')? -1:1;
			
			if(part.indexOf("x")==-1)//if x not exist
			{
				lstPow.add(0);
				lstCoff.add( Double.parseDouble(part)*sign);
				continue;
			}
			
			String[] prts = part.split("x");
			//part = part.replaceAll("+-", "-");

			String partCoff ="";String partPow="";
			if(prts.length == 0)
			{
				lstPow.add(1);
				lstCoff.add(1.0*sign);
			}
			else
			if(prts.length==1)
			{
				if(prts[0].equals("-"))
				{
					lstPow.add(1);
					lstCoff.add(1.0*sign);
				}
				else
				if(part.indexOf("\\^")==-1)//if ^ exist 
				{
					partCoff = prts[0];

					lstCoff.add(Double.parseDouble(partCoff)*sign);
					lstPow.add(1);
				}
				else
				{
					partPow = prts[0];
					lstPow.add( Integer.parseInt(partPow));
					lstCoff.add(1.0*sign);
				}
			}
			else
			{
				if(prts[0].isEmpty())
					partCoff = "1";
				else
					if(prts[0].equals("-"))
						partCoff = "-1";
				partCoff = prts[0];

				if(prts[1].isEmpty())
					partPow = "1";
				else
					partPow = prts[1];

				if(partCoff.isEmpty()) 
					partCoff = "1";
				lstCoff.add( Double.parseDouble(partCoff)*sign);
				partPow = prts[1].replace('^', ' ').trim();
				lstPow.add( Integer.parseInt(partPow));
			}

		}
		ArrayList<Monom> lstMonom = new ArrayList<>();
		for(int i =0; i< lstCoff.size();i++)
		{
			lstMonom.add(new Monom(lstCoff.get(i),lstPow.get(i)));
		}

		return lstMonom;
	}

	/**
	 * This function computes the value of this polynom at f(x), as a sum of monoms
	 * @param the function get x and puts it in a polynom
	 * @return Result after placing the x in the polynom
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double function=0;
		Iterator<Monom> itr=this.iteretor();
		while(itr.hasNext())
		{
			Monom m=itr.next();
			function+=m.f(x);
		}
		return function;
	}

	/**
	 * the function add p1 to this Polynom
	 * @param p1 polynom
	 * We have mapped the polynomials according to their holdings
	 * the map get <Key,Value>=<Power,coefficient>
	 */
	@Override
	public void add(Polynom_able p1) {

		Polynom p=new Polynom();
		Map<Integer,Double> map= new HashMap<>();
		// add p1 to polynom 
		Iterator<Monom> itr1= this.iteretor();
		Iterator<Monom> itr2= p1.iteretor();
		ArrayList<Monom> lstTotal= new ArrayList<>();
		while(itr1.hasNext()) // check whether there are other organs in the array
		{			
			lstTotal.add(itr1.next());
		}
		while(itr2.hasNext()) // check whether there are other organs in the array
		{			
			lstTotal.add(itr2.next());
		}
		Iterator<Monom> itrTotal = lstTotal.iterator();
		while(itrTotal.hasNext()) 
		{
			Monom m=itrTotal.next();
			int KeyPow =m.get_power();
			if(map.containsKey(KeyPow))//exist in map,means equal powers 
			{
				Double sum = map.get(KeyPow) + m.get_coefficient();
				map.put(KeyPow, sum);
			}
			else//not exist in map,means not equal powers 
			{
				map.put(KeyPow,m.get_coefficient());
			}			
		}
		// Set<Integer> keys = map.keySet();
		Set<Entry<Integer, Double>> entry  = map.entrySet();
		Iterator<Entry<Integer, Double>> itr = entry.iterator();
		lstMon.clear();
		while(itr.hasNext()) 
		{
			Entry<Integer, Double> en = itr.next();
			Double coff = en.getValue();
			if(coff!=0)
			{
				Monom m = new Monom(coff,en.getKey());
				p.removeZero();
				lstMon.add(m);
			}
		} 
	}

	/**
	 * the function add m1 to this Polynom
	 * @param m1 Monom
	 * We get a monom and perform a connection operation with another monom 
	 */
	@Override
	public void add(Monom m1)
	{
		boolean foundPow=false;
		Iterator<Monom> itr= this.iteretor(); 
		while(itr.hasNext()) // check whether there are other organs in the array
		{	
			Monom m2=itr.next();
			if(m1.get_power()== m2.get_power() && m1.get_coefficient()!=0)
			{
				m2.add(m1);
				foundPow=true;
				if(!m2.validValues())
				{
					itr.remove();
				}
				break;
			}
		}

		if(foundPow==false && m1.get_coefficient()!=0 )
		{
			lstMon.add(m1);
		}

		lstMon.sort(MonComp);
	}

	/**
	 * the function subtract p1 from this Polynom
	 * @param p1 polynom
	 * We get monom and perform subtraction with another monom
	 */	
	@Override
	public void substract(Polynom_able p1) 
	{
		ArrayList<Monom> lst2Sub = new ArrayList<>();
		Iterator<Monom> itr1 = iteretor();
		Iterator<Monom> itr2 = p1.iteretor();
		while (itr2.hasNext()) //invert sign for second polynom.
		{
			Monom m = itr2.next();
			m.InvSign();
		}
		this.add(p1);
		

	}

	/**
	 * the function multiply this Polynom by p1
	 * @param p1 polynom
	 * We accept a polynom and perform a multiplication operation with another polynom
	 */
	@Override
	public void multiply(Polynom_able p1) 
	{
		ArrayList<Monom> alResult = new ArrayList<Monom>();
		Iterator<Monom> itr2 = this.iteretor();
		Iterator<Monom> itr1 = p1.iteretor();

		while (itr2.hasNext()) 
		{
			Monom m = itr2.next();
			while (itr1.hasNext()) 
			{
				Monom m1= itr1.next();
				Monom res = Monom.multiply(m,m1);
				alResult.add(res);
			}
			itr1 = p1.iteretor();
		}
		this.lstMon = alResult;
		CombineEqualPow();

	}
	
	/**
	 * the function CombineEqualPow author equal powers by mapping
	 * We Mapping operators on powers and after this we connection is made to equal powers
	 */
	private void CombineEqualPow()
	{
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		Iterator<Monom> itr = this.iteretor();
		while (itr.hasNext()) 
		{ 
			Monom m = itr.next(); 
			int keyPow = m.get_power();

			if(!map.containsKey(keyPow))
				map.put(m.get_power(), m.get_coefficient());
			else//if power exist
			{
				double coff = map.get(keyPow);
				coff+=m.get_coefficient();
				map.put(m.get_power(),coff);
			} 
		}
		Set<Integer> keys = map.keySet();//collection of all powers in map
		Iterator<Integer> itrKeys = keys.iterator();
		ArrayList<Monom> lstCombine = new ArrayList<>();
		while (itrKeys.hasNext())//iterate of all powers in map
		{ 
			Integer keyPow = itrKeys.next();//get power
			Double valCoff = map.get(keyPow);//get corresponding coff to given power 
			lstCombine.add(new Monom(valCoff,keyPow));//create new monom from pair (coff,power)
			//add created monom to lstCombine
		}
		lstCombine.sort(MonComp);
		this.lstMon = lstCombine;
	}

	/**
	 * @return int - size of monoms array
	 */
	public int Size() 
	{
		return lstMon.size();
	}

	/**
	 * the function test if this Polynom is logically equals to p1.
	 * @param p1 polynom
	 * @return true if this polynom represents the same function ans p1
	 */
	@Override
	public boolean equals(Polynom_able p1)
	{
		Iterator<Monom> itrP= this.iteretor();
		Iterator<Monom> itrP1= p1.iteretor();
		if(this.Size()!=((Polynom)p1).Size())//not the same length
			return false;
		while(itrP.hasNext()) 
		{
			Monom mP=itrP.next();
			while(itrP1.hasNext())
			{
				Monom mP1=itrP1.next();

				if(!mP.isEqual(mP1)) 
				{
					return false;
				}

			}
		}
		return true;
	}

	/** 
	 * @return size of Monom arrayList
	 */
	@Override
	public boolean isZero() 
	{

		if(this.Size()==0) 
		{
			return true;
		}

		//boolean ans= true;
		Iterator<Monom> itr=this.iteretor();
		while(itr.hasNext()) 
		{
			Monom m= itr.next();
			if(m.get_coefficient()!=0) 
			{
				return false;
			}

		}

		return true;

	}
	/**
	 * The function check if exist zero on polynom
	 * if exist zero - the function remove the zero from the polynom
	 */
	public void removeZero() 
	{
		for(int i=0 ; i<this.Size(); i++)
		{
			if(this.lstMon.get(i).get_coefficient()==0) 
			{
				this.lstMon.remove(i);
			}
		}

	}
	/**
	 * the function compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return
	 */
	@Override
	public double root(double x0, double x1, double eps) 
	{
		double y0 = this.f(x0);
		double y1 = this.f(x1);
		
		try {
		double center = (x0 + x1) / 2;//middle
		if(y0*y1 > 0)
		{	
			throw new Exception("Error y0 and y1 cannot be both positive!");
		}
		
		if(y0==0) 
		{
			return x0;
		}
		
		if(y1==0) 
		{
			return x1;
		}
	
		if(Math.abs(x0 - x1) < eps)
		{
			return x0;
		}
		
		 if (f(center) * y0 < 0)
		{
			return root(x0, center, eps);
		}
		else
		{
			return root(center, x1, eps);
		}
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		return x0;
	}
	

	/**
	 * the function create a deep copy of this Polynum
	 * @return new polynom after deep copy
	 */
	@Override
	public Polynom_able copy() 
	{
		Polynom p = new Polynom();
		Iterator<Monom> itr= this.iteretor();
		while(itr.hasNext())
		{
			Monom m1= itr.next();
			p.add(new Monom(m1));
		}
		return p;
	}

	/**
	 * the function create a deep copy of this Polynum
	 * @param the function get Monom ArrayList
	 * @return new polynom after add equal power
	 */

	public Polynom(ArrayList<Monom> list) 
	{
		this.lstMon = new ArrayList<>();
		Iterator<Monom> itr= list.iterator();

		while(itr.hasNext()) 
		{
			this.lstMon.add(itr.next()) ;
		}
	}

	/**
	 * The function makes string to polynom
	 */
	public String toString() 
	{
		String polynom="";
		Iterator<Monom> itr= this.iteretor();

		if(this.isZero()) 
		{
			return "0";
		}

		while(itr.hasNext()) 
		{
			Monom m1=itr.next();
	
			polynom+=m1.toString()+"+";
		}

		if(polynom.endsWith("+"))
		{
			polynom = polynom.substring(0, polynom.length()-1);
		}
		polynom = polynom.replaceAll("\\+-", "-");

		return polynom;
	}


	/**
	 * The function compute a new polynom which is the derivative of this polynom
	 * @return  a new polynomial after we have derived it
	 */
	@Override
	public Polynom_able derivative() {

		ArrayList<Monom>lstDrv= new ArrayList<>();

		Iterator<Monom> itr= this.iteretor();

		while(itr.hasNext()) 
		{
			Monom m= itr.next();
			Monom drvMon = m.derivative();
			if(drvMon.get_coefficient()!=0)
			{
				lstDrv.add(drvMon);
			}
		}
		return new Polynom(lstDrv);
	}


	/**
	 * The function compute Riemann's Integral over this polynom starting from x0, till x1 using eps size steps,
	 * @param the function get two points x0,x1 and eps
	 * @return the approximated area above the x-axis below this polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps)
	{
		double AreaRectangle=0;
		double xNum=x0;
		double Area= Math.abs((x1-x0)/eps);
		if(x0>x1) 
		{
			return 0;
		}
		
		for(int i=0;i<Area;i++)
		{
			if(this.f(xNum)>0)
			{
			AreaRectangle+=eps*f(xNum);
			}
			xNum+=eps;
			
		}
		//double ARround=round(AreaRectangle);
		double ARround=Math.round(AreaRectangle* 100.0)/100.0;
		return ARround;
	}
	
	public void gui() 
	{
		GraphGUI jG=new GraphGUI(this.toString());
	}
	

	/**
	 * the function create a deep copy of this Polynum
	 * @param the function get two point x0,x1 and eps
	 * @return area of function under x exist 
	 */
	   public double AreaGraphUnderX(int x0, int x1, double eps) 
       {

		    double AreaRectangle=0;
			double xCurr=x0;
			double yCurr = 0;
			double nAreas= Math.abs((x1-x0)/eps);
			if(x0>x1) 
			{
				return 0;
			}
			
			for(int i=0;i<nAreas;i++)
			{
				yCurr = this.f(xCurr);
				if(yCurr<0)
				{
				  AreaRectangle+=eps*yCurr;
				}
				xCurr+=eps;
			}
			return AreaRectangle;
       }

		/**
		 * the function create a deep copy of this Polynum
		 * @param the function get two point x0,x1 and eps
		 * @return area of function up x exist 
		 */
   		 public double AreaGraphUpX(int x0, int x1, double eps) 
         {

   			double AreaRectangle=0;
 			double xCurr=x0;
 			double yCurr = 0;
 			double nAreas= Math.abs((x1-x0)/eps);
 			if(x0>x1) 
 			{
 				return 0;
 			}
 			
 			for(int i=0;i<nAreas;i++)
 			{
 				yCurr = this.f(xCurr);
 				if(yCurr>0)
 				{
 				  AreaRectangle+=eps*yCurr;
 				}
 				xCurr+=eps;
 			}
 			return AreaRectangle;
         }

	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	@Override
	public Iterator<Monom> iteretor() 
	{  
		Iterator<Monom> itr= lstMon.iterator();
		return itr;
	}


}
