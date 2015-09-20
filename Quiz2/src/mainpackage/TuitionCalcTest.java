package mainpackage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TuitionCalcTest {

	//Create 5 test cases (calls constructor method with args)
	//Note: Constructor(initTuition, AnnualPercentIncrease, LoanAPR, RepaymentTerm)
	TuitionCalc calc1 = new TuitionCalc(10000, 10, 4, 12);
	TuitionCalc calc2 = new TuitionCalc(5000, 8, 5, 5);
	TuitionCalc calc3 = new TuitionCalc(20000, 5, 5, 15);
	TuitionCalc calc4 = new TuitionCalc(30000, 7, 3, 20);
	TuitionCalc calc5 = new TuitionCalc(15000, 10, 6, 16);
	
	@Before
	//Run the methods to calculate and set the total and monthly payment
	public void setAll(){
		calc1.CalculateTotalPayment();
		calc2.CalculateTotalPayment();
		calc3.CalculateTotalPayment();
		calc4.CalculateTotalPayment();
		calc5.CalculateTotalPayment();
		
		calc1.CalculateMonthlyPayment();
		calc2.CalculateMonthlyPayment();
		calc3.CalculateMonthlyPayment();
		calc4.CalculateMonthlyPayment();
		calc5.CalculateMonthlyPayment();
	}
	
	@Test
	/* Check to see if the Total Payment attributed variable in each test case is 
	 * equal to my manual calculations of the total payment over 4 years using 
	 * assertTrue function
	 */
	public void TotalPaymentTest() {
		assertTrue(calc1.getTotalPayment() == 46410);
		assertTrue(calc2.getTotalPayment() == 22530.56);
		assertTrue(calc3.getTotalPayment() == 86202.5);
		assertTrue(calc4.getTotalPayment() == 133198.29);
		assertTrue(calc5.getTotalPayment() == 69615);

	}

	@Test
	/* Check to see if the Monthly Payment is equal to the calculations of 
	 * the PMT function in excel.
	 * 
	 * Because of precision errors in either Excel or FinanceLib, check to see that
	 * the difference between the Excel-calculated and Java function-calculated
	 * values is less than 0.01
	 */
	public void MonthlyPaymentTest(){
		assertTrue(Math.abs(calc1.getMonthlyPayment() - 406.33) < 0.01);
		assertTrue(Math.abs(calc2.getMonthlyPayment() - 425.18) < 0.01);
		assertTrue(Math.abs(calc3.getMonthlyPayment() - 681.68) < 0.01);
		assertTrue(Math.abs(calc4.getMonthlyPayment() - 738.71) < 0.01);
		assertTrue(Math.abs(calc5.getMonthlyPayment() - 564.88) < 0.01);
	}
}
