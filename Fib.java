/**
 * File:	Fib.java
 * Package:	---
 * 
 * Class Fib is the main program for the generic fibonacci number generation.
 * The program generates the nth number in a fibonacci sequence based on the
 * arguments(a, b) passed in by the user. This main thread checks the command
 * line arguments to ensure their validity. The program proceeds to create a 
 * thread to print the out put. Then creates a thread for each index in the 
 * array holding the values needed to generate the final number. This process
 * starts with the last number and works back to the start. All the threads are
 * synchronized by a monitor. Finally the main program exits.
 * 
 * Usage: java Fib a b n
 * 
 * @author 	Richard DiTullio
 * @version 9/13/2016
 * 
 */

import java.math.BigInteger;

public class Fib {

	static Thread threads[];
	static int n;
	static BigInteger firstNum;
	static BigInteger secondNum;
	
	/**
	 * Main program 
	 * @param args
	 * a: the First number in the Fib sequence
	 * b: the Second number in the Fib sequence
	 * c: the length of the Fib sequence must be a number >=0
	 */
	public static void main(String[] args) {
		// checks to see if there are 3 arguments
		if(args.length != 3){
			System.out.println("You entered in:" + args.length + " arguments"
					+ " please enter 3 arguments.");
			System.exit(1);
		}
		try{
			n = Integer.parseInt(args[2]);
		}
		catch(java.lang.NumberFormatException nonNum){
			System.out.println("Please enter a number for argument n.");
			System.exit(1);
		}
		
		// checks if n >= 0
		if(n < 0){
			System.out.println("You entered in:" + n + " for the third"
					+ " argument. Please enter a number >= 0.");
			System.exit(1);
		}
		
		// creates the first two numbers in the sequence
		try{
			firstNum = new BigInteger(args[0]);
		}
		catch(java.lang.NumberFormatException word){
			System.out.println("Please enter a number for argument a.");
			System.exit(1);
		}
		try{
			secondNum = new BigInteger(args[1]);
		}
		catch(java.lang.NumberFormatException nonNum){
			System.out.println("Please enter a number for argument b.");
			System.exit(1);
		}
		// creates a monitor
		Monitor mon = new Monitor(n + 1);
		
		// if there are no calculations needed to be done
		if(n == 0){
			mon.putValue(0, firstNum);
			// creates and starts the output thread
			Thread output = new Thread(new Output(mon, n));
			output.start();
		}
		else if(n == 1){
			mon.putValue(0, firstNum);
			mon.putValue(1, secondNum);
			// creates and starts the output thread
			Thread output = new Thread(new Output(mon, n));
			output.start();
		}
		// if calculations need to be made
		else{
			// creates and starts the output thread
			Thread output = new Thread(new Output(mon, n));
			output.start();
			
			// Creates the computing threads
			threads = new Thread[n + 1];
			for(int count2 = n; count2 >= 0; count2--){
				threads[count2] = new Thread(new findCalc(mon, count2, count2 - 1, 
						count2 - 2, firstNum, secondNum));
				threads[count2].start();
				n--;
			}
		}
	}

}
