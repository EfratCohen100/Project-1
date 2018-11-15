package myMath;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
      			        System.out.println("testF:");
						testF();
			            System.out.println("testHello");
			            testHello();			
			            System.out.println("testAddPolynom:");
			            testAddPolynom();
						System.out.println("testAddMonom:");
					    testAddMonom();
						System.out.println("testSubstract:");
						testSubstract();
						System.out.println("testMultiply:");
						testMultiply();
						System.out.println("testDerivative:");
					    testDerivative();
						System.out.println("testArea:");
						testArea();
						System.out.println("testRoot:");
					    testRoot();
						System.out.println("testZero:");
						testZero();
						System.out.println("polynomCopy");
					    polynomCopy();
			         
					    
		} 
		catch(NullPointerException e) 
		{ 
			System.out.println("Caught in main."); 
		} 


	}
	

	

	public static void testHello() 
	{
		Polynom p= new Polynom("Hello");
		System.out.println(p);
		
	}

	public static void testF() 
	{
		Polynom p = new Polynom("1-2x+4x^2+5x^3");
		Polynom p1= new Polynom("2x+3x^2");
		System.out.println("get 166:" +p.f(3));
		System.out.println("get 33:" + p1.f(3));

	}
	public static void testAddPolynom()
	{

		Polynom p1 = new Polynom("8+2x+4x^2+9x^5");
		System.out.println(p1);
		Polynom p2= new Polynom("2+5x^7-5x^7");
		System.out.println(p2);
		p1.add(p2);
		System.out.println("get :10x^0+2x^1+4x^2+9x^5 ");
		System.out.println("The result we got:"+""+p1);

	}

	public static void testAddMonom() {
		Polynom_able p = new Polynom();
		p.add(new Monom(0.9,0));
		p.add(new Monom(-0.3,0));
		p.add(new Monom(-0.6,0));
		System.out.println( "get 0:"+""+"The result we got:"+""+p.toString());

		Polynom_able p1 = new Polynom();
		p1=p.copy();
		System.out.println("get 0:"+""+"The result we got:"+""+p1.toString());
		System.out.println("get true:"+""+p1.equals(p));

		Polynom_able p2 = new Polynom();
		p2.add(new Monom(8,0));
		p2.add(new Monom(-3,0));
		p2.add(new Monom(-5,0));
		System.out.println("get true:"+""+p1.equals(p2));
		System.out.println("get true:"+""+p1.toString().equals(p2.toString()));

		Polynom_able p3 = new Polynom();
		p3.add(new Monom(5,0));
		p3.add(new Monom(0.4,3));
		p3.add(new Monom(-2,0));
		p3.add(new Monom(-6,1));
		p3.add(new Monom(0,6));
		p3.add(new Monom(3,2));
		System.out.println("get polynom: 3-6x+3x^2+0.4x^3");
		System.out.println("The result we got:"+""+p3);

	}

	public static void testSubstract()
	{
		Polynom p = new Polynom("1+2x+4x^2+5x^7");
		Polynom p1= new Polynom("2-3x^2+5x^7");
		p.substract(p1);
		System.out.println("get:-1+2x+7x^2");
		System.out.println("The result we got:"+""+p);
		Polynom p2 = new Polynom("0x^2");
		Polynom p3= new Polynom("3x^2");
		p2.substract(p3);
		System.out.println(p2);
		Monom m=new Monom(0,2);
		Monom m1=new Monom(5,2);
		m.sub(m1);
		System.out.println("Substract Monoms m-m1:"+m);//***-***
		
	}

	public static void testMultiply()
	{
		Polynom p = new Polynom("-2x+4x^2+5x^7");
		Polynom p1= new Polynom("2x+3x^2");
		p.multiply(p1);
		System.out.println("get :-4x^2+2x^3+12x^4+10x^8+15x^9");
		System.out.println("The polynom we got:"+""+p);

	}

	public static void testDerivative() 
	{

		Polynom p = new Polynom("2+x-3x^5+4x^8");
		Polynom p1= new Polynom("x-5x^2+6x^9");
		System.out.println("need to get from p: 2-15x^4+32x^7");
		System.out.println("The result we got:"+""+p.derivative());
		System.out.println("need to get from p1:-10x+54x^8");
	    System.out.println("The result we got:"+""+p1.derivative());
	}

	public static void polynomCopy() 
	{
		Polynom p = new Polynom("2x-3x^5+4x^8");
		Polynom p1= new Polynom("-5x^2+6x^9");
		p1 = new Polynom(p.toString());
		System.out.println("get same polynom:"+p);
		System.out.println("polynom is: "+p1);

	}


	//root 
	public static void testRoot()
	{
		Polynom p1 = new Polynom("1+3x+x^2");
		System.out.println("Estimated root: -2.618033  ,  Actual root:" + p1.root(-4, -1, 0.01));
		p1 = new Polynom("x^2+3");
		System.out.println("Estimated root: The function root must have one positive value and one negative value.  ,  actual root:" + p1.root(0.5, 1.5, 0.01));

	}
	public static void testArea()
	{	
		Polynom_able p1 = new Polynom("2x-3x^2+x^3");
		System.out.println(p1);
		double eps = 0.0001;
		
		//area
		System.out.println(" close to 1600:" + p1.area(0,10,eps));
		System.out.println(" close to 0.25:" + p1.area(0, 1, eps));
		System.out.println(" close to 0:" + p1.area(0.5, 1.5, eps));
		System.out.println(" close to 0.10:" + p1.area(0.5, 1, eps));	

	}
	public static void testZero() 
	{
		//zero test
		Polynom_able p1 = new Polynom();
		p1.add(new Monom(0.2,0));
		p1.add(new Monom(-0.1,0));
		p1.add(new Monom(-0.1,0));

		System.out.println("get zero:"+p1.toString());
	}

}