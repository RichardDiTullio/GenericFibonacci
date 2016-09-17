/**
 * File:	Monitor.java
 * Package:	---
 * 
 * Class Monitor is a monitor class that ensures the proper order of execution
 * of the threads created by the main program. This program also posesses all
 * of the values generated in the generation of the fibonacci sequence in an
 * array of BigIntegers. The monitor also ensures that each thread waits for a
 * value to exist at the index it is requesting and that each index can only 
 * hold one value that can't be overwritten.
 * 
 * @author Richard DiTullio
 *
 */
import java.math.BigInteger;

public class Monitor {
	int n;
	BigInteger F[];
	
	/*
	 * @param: an integer to initialize the monitor class
	 */
	Monitor(int n){
		this.n = n;
		F = new BigInteger[n + 1];
	}
	
	/*
	 * @param: i: the index to place the value value in the array
	 * value: the BigInteger to be placed in the array
	 * 
	 * This method places a calculated value in the generic fibonacci sequence
	 * into its place in the array
	 */
	synchronized public void putValue(int i, BigInteger value){
		if(F[i] != null){
			System.out.println("Error: value alread exists at " + i + ". "
					+ "Terminating now.");
			for(int j = 0; j < 10; j++){
					System.out.print(F[j] + " ");
			}
			System.exit(1);
		}
		F[i] = value;
		notifyAll();
	}
	
	/*
	 * @param: the index of the number to get
	 * @return: the value at the index i in array
	 * 
	 * This method returns the number stored in the array at the index only
	 * when the number has been placed in the array.
	 */
	synchronized BigInteger getValue(int i) throws InterruptedException{
		while(F[i] == null){
			wait();
		}
		return F[i];
	}
}
