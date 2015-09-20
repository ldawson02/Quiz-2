package mainpackage;

import java.util.Scanner;
import org.apache.poi.ss.formula.functions.FinanceLib;

/**
 * @author Lia Dawson Quiz 2 Problem 1
 * 
 *         This program creates a class that calculates the total amount of a
 *         loan a student will have to take out to cover the tuition of a 4-year
 *         college, and also calculates the monthly payment they will have to
 *         make on their loan.
 * 
 *         The program has a method setValues() which allows the user to input
 *         the 4 variables necessary to calculate the total loan and the monthly
 *         payments. Those 4 variables are as follows:
 * 
 *         initTuition = the initial tuition cost of the university
 *         AnnualPercentIncrease = the percent that the tuition increases by
 *         every year 
 *         LoanAPR = the annual rate that the bank gives on the
 *         user's loan 
 *         RepaymentTerm = how long the student has to repay all of
 *         the loan
 *         
 *         The methods CalculateTotalPayment() and CalculateMonthlyPayment() use
 *         these 4 variables to calculate the total size of the loan (using a 
 *         simple for loop that calculates the tuition cost per year and sums them)
 *         and the monthly payment the student will need to make (based on the PMT 
 *         Finance Lib function).
 *         
 *         The CalculateTotalPayment() and CalculateMonthlyPayment() methods act as
 *         setters for the private attributes totalPayment and monthlyPayment - this
 *         is because we don't want a user to override the function and set the values
 *         to an arbitrary, incorrect value.
 *
 */

public class TuitionCalc {

	private double initTuition;
	private double AnnualPercentIncrease;
	private double LoanAPR;
	private double RepaymentTerm;
	private double TotalPayment;
	private double MonthlyPayment;

	//This method acts as the main method that calls the other methods in the class and prints out the total and monthly payment on tuition to the user
	public static void main(String[] args) {
		//Create a new object of the class TuitionCalc
		TuitionCalc Calculator = new TuitionCalc();
		
		//Call the function that asks the user to set all the variables necessary for calculating total and monthly payment
		Calculator.setValues();
		
		//Call the functions that calculate the total and monthly payment based on the variables set in setValues()
		Calculator.CalculateTotalPayment();
		Calculator.CalculateMonthlyPayment();
		
		//Print out the values to the user
		System.out.printf("You will need to take out a loan of $%3.2f in order to cover tuition costs for 4 years.", Calculator.getTotalPayment());
		System.out.printf("You will owe $%3.2f a month in order to pay off a loan of this size.", Calculator.getMonthlyPayment());

	}

	//Constructor no args
	public TuitionCalc() {

	}

	//Constructor with arguments
	public TuitionCalc(double initTuition, double annualPercentIncrease, double loanAPR, double repaymentTerm) {
		this.initTuition = initTuition;
		this.AnnualPercentIncrease = annualPercentIncrease;
		this.LoanAPR = loanAPR;
		this.RepaymentTerm = repaymentTerm;
	}

	//Getters for initTuition, AnnualPercentIncrease, LoanAPR, and RepaymentTerm
	public double getInitTuition() {
		return initTuition;
	}

	public double getAnnualPercentIncrease() {
		return AnnualPercentIncrease;
	}

	public double getLoanAPR() {
		return LoanAPR;
	}

	public double getRepaymentTerm() {
		return RepaymentTerm;
	}
	
	//Setters for initTuition, AnnualPercentIncrease, LoanAPR, and RepaymentTerm
	public void setInitTuition(double initTuition) {
		this.initTuition = initTuition;
	}

	public void setAnnualPercentIncrease(double annualPercentIncrease) {
		AnnualPercentIncrease = annualPercentIncrease;
	}

	public void setLoanAPR(double loanAPR) {
		LoanAPR = loanAPR;
	}

	public void setRepaymentTerm(double repaymentTerm) {
		RepaymentTerm = repaymentTerm;
	}
	
	//Getters for TotalPayment and MonthlyPayment
	public double getTotalPayment() {
		return TotalPayment;
	}

	public double getMonthlyPayment() {
		return MonthlyPayment;
	}


	/*This method utilizes the Scanner to ask the user to set the necessary values of:
	 * 
	 * initTuition 				= the initial tuition cost of the university
	 * AnnualPercentIncrease 	= the percent that the tuition increases by every year
	 * LoanAPR 					= the annual rate that the bank gives on the user's loan
	 * RepaymentTerm 			= how long the student has to repay all of the loan
	 * 
	 * The setter method is then called for each of the variables so they are set.
	 */
	public void setValues() {
		
		Scanner input = new Scanner(System.in);
		

		System.out.print("What is the tuition cost of your university for your freshman year? ");
		Double initTuition = input.nextDouble();
		this.setInitTuition(initTuition);

		System.out.print("What percent will your university increase your tuition every year? (e.g. 2%) ");
		Double AnnualPercentIncrease = input.nextDouble();
		this.setAnnualPercentIncrease(AnnualPercentIncrease);
		
		System.out.print("What is your loan APR? ");
		Double LoanAPR = input.nextDouble();
		this.setLoanAPR(LoanAPR);

		System.out.print("How long is your loan repayment term (in years)? ");
		Double RepaymentTerm = input.nextDouble();
		this.setRepaymentTerm(RepaymentTerm);

		input.close();

	}

	/*This method, using encapsulation, calls the private attributes initTuition and AnnualPercentIncrease
	 * to calculate the total amount that would be owed after 4 years at this university.
	 * 
	 * Utilizes a for loop to calculate the tuition rate for a certain year, then adds it to the total amount.
	 * 
	 * This method acts as a setter for TotalPayment.
	 */
	public void CalculateTotalPayment() {
		
		double total = this.initTuition;
		double tuition = this.initTuition;
		for (int i = 1; i < 4; i++) {
			tuition = tuition * (1 + (this.AnnualPercentIncrease / 100));
			total += tuition;
		}
		this.TotalPayment = total;
	}

	/*This method calls the private attributes LoanAPR, RepaymentTerm, and TotalPayment to calculate
	 * the monthly payment a student must make to pay off their loan.
	 * 
	 * It utilizes the FinanceLib method pmt() which has the following variables:
	 * 
	 * rate = monthly interest rate (decimal)
	 * n 	= number of months to repay the loan
	 * pv	= present value of the loan, aka the amount that you borrow
	 * fv	= future value of the loan, aka the amount you want to have at the end of the repayment term
	 * t 	= boolean value that states whether the payment is collected at the beginning (true) or end (false)
	 * 		of the month
	 * 
	 * This method acts as a setter for the variable MonthlyPayment.
	 */
	public void CalculateMonthlyPayment() {
		double payment = FinanceLib.pmt(LoanAPR / 1200, RepaymentTerm * 12, TotalPayment, 0, false);
		this.MonthlyPayment = -1*payment;
	}
}
