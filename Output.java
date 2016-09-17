/**
 * File:	Output.java
 * package:	---
 * 
 * This file is a runnable object that prints the output of the Fib program 
 * once all the values have been calculated. This output is terminated with
 * a newline character.
 * 
 * @author Richard DiTullio
 *
 */

public class Output implements Runnable{

	int i;
	Monitor mon;
	
	// constructer to create the output object
	public Output(Monitor mon, int i){
		this.i = i;
		this.mon = mon;
	}
	
	// Run method that prints out the final value and a newline character
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.print(mon.getValue(i) + "\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
