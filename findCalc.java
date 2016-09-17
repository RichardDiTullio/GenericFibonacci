/**
 * Class findCalc is a runnable object that will, if necessary, calculate the
 * value for each index, store it in the array, and terminate. 
 * 
 * @author Richard DiTullio
 *
 */

import java.math.BigInteger;

public class findCalc implements Runnable {
	
	int a;
	int b;
	int n;
	Monitor mon;
	BigInteger temp1;
	BigInteger temp2;
	BigInteger res;
	BigInteger firstNum;
	BigInteger secondNum;

	/*
	 * @param mon: The monitor object
	 * a: the index of the first of the two numbers to be added
	 * b: the index of the second of the two numbers to be added
	 */
	public findCalc(Monitor mon, int n, int a, int b, BigInteger firstNum, 
			BigInteger secondNum){
		this.a = a;
		this.b = b;
		this.n = n;
		this.mon = mon;
		this.firstNum = firstNum;
		this.secondNum = secondNum;
	}

	/*
	 * The run method that will add the two BigIntegers and store the result in
	 * the monitor's array
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(n == 0){
			mon.putValue(n, firstNum);
		}
		else if(n == 1){
			mon.putValue(n, secondNum);
		}
		else{
			try {
				temp1 = mon.getValue(a);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				temp2 = mon.getValue(b);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BigInteger res = temp1.add(temp2);
			mon.putValue(n, res);
		}
	}
}
