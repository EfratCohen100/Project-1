package Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;

class JUnitTest {

	
//	@Test
//	void testHello()
//	{
//		Polynom p2 = new Polynom("Hello");
//		fail( "expression shouldn't contains Alphabetic characters.");
//	}
//	 

	@Test
	void testF()
	{
		Polynom p  = new Polynom("-2x+5x^3+4x^2+1");
		Polynom p1 = new Polynom("3x^2+2x");
		assertEquals(166, p.f(3));
		assertEquals(33, p1.f(3));
	}

	@Test
	void testAddPolynom()
	{
		Polynom p3 = new Polynom("2x+9x^5+4x^2+8");
		Polynom p4 = new Polynom("5x^7-5x^7+2");
		p3.add(p4);
		assertEquals("10.0x^0+2.0x^1+4.0x^2+9.0x^5", p3.toString());
	}
	@Test
	void testAddMonom()
	{
		Polynom p5 = new Polynom();
		p5.add(new Monom(0.9,0));
		p5.add(new Monom(-0.3,0));
		p5.add(new Monom(-0.6,0));
		assertEquals("0", p5.toString());


		Polynom_able p6 = new Polynom();
		p6=p5.copy();
		assertEquals("0", p6.toString());
		assertTrue(p6.equals(p5));

		Polynom p7 = new Polynom();
		p7.add(new Monom(8,0));
		p7.add(new Monom(-3,0));
		p7.add(new Monom(-5,0));
		assertTrue(p6.equals(p7));
		assertTrue(p6.toString().equals(p7.toString()));

		Polynom p8 = new Polynom();
		p8.add(new Monom(5,0));
		p8.add(new Monom(0.4,3));
		p8.add(new Monom(-2,0));
		p8.add(new Monom(-6,1));
		p8.add(new Monom(0,6));
		p8.add(new Monom(3,2));
		assertEquals("3.0x^0-6.0x^1+3.0x^2+0.4x^3", p8.toString());

	}
	@Test
	void testSubstract()
	{

		Polynom p9 = new Polynom("x+5x^7+4x^2+1");
		Polynom p10= new Polynom("5x^7-3x^2+2");
		p9.substract(p10);
		assertEquals("-1.0x^0+1.0x^1+7.0x^2", p9.toString());



		Polynom p11 = new Polynom("0x^2");
		Polynom p12= new Polynom("3x^2");

		p11.substract(p12);
		assertEquals("-3.0x^2", p11.toString());


		Monom m=new Monom(0,2);
		Monom m1=new Monom(5,2);
		m.sub(m1);
		Monom expected2 = new Monom(-5,2);
		boolean ans2=m.isEqual(expected2);
		assertTrue(ans2);

	}

	@Test
	void testMultiply()
	{
		Polynom_able p13 = new Polynom("-2x+5x^7+4x^2");
		Polynom_able p14= new Polynom("3x^2+2x");
		p13.multiply(p14);
		assertEquals("-4.0x^2+2.0x^3+12.0x^4+10.0x^8+15.0x^9", p13.toString());
	}



	@Test
	void testDerivative()
	{
		Polynom p15 = new Polynom("2.0x^1+4.0x^8-3.0x^5+8");
		Polynom p16= new Polynom("-5.0x^2+6.0x^9");
		assertEquals("2.0x^0+32.0x^7-15.0x^4", p15.derivative().toString());
		assertEquals("-10.0x^1+54.0x^8", p16.derivative().toString());


	}
	@Test
	void polynomCopy()
	{
		Polynom p17 = new Polynom("2x-3x^5+4x^8");
		Polynom p18 = new Polynom(p17.toString());
		assertEquals("2.0x^1-3.0x^5+4.0x^8", p17.toString());
		assertEquals("2.0x^1-3.0x^5+4.0x^8", p18.toString());	
	}
	@Test
	void testRoot()
	{
		Polynom p19=new Polynom ("x-5");
		assertEquals(5.0, p19.root(2, 8, 0.01));

		Polynom p = new Polynom("x^2+3x+1");
		assertEquals(-2.6180343627929688, p.root(-4, -1, 0.00001));

	}

	@Test
	void testArea()
	{
		Polynom p20 = new Polynom("2x-3x^2+x^3");
		double eps = 0.0001;
		assertEquals(0.25, p20.area(0, 1, eps));
		
		Polynom p = new Polynom("x^2+3x+1");
		double eps1 = 0.00001;
		assertEquals(2.83, p.area(0, 1, eps1));
	}

	@Test
	void testZero()
	{
		Polynom_able p21 = new Polynom();

		p21.add(new Monom(0.2,0));
		p21.add(new Monom(-0.1,0));
		p21.add(new Monom(-0.1,0));
		assertEquals("0", p21.toString());
	}

	@Test
	void testIterator()
	{
		Polynom p = new Polynom("4.0x^1+2.0x^5-9.3x^6");
		Iterator<Monom> itr = p.iteretor();
		Polynom actual = new Polynom();
		while(itr.hasNext())
		{
			Monom m=itr.next();
			actual.add(m);
		}
		assertEquals("4.0x^1+2.0x^5-9.3x^6", actual.toString());
	}

}
