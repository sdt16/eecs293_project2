package org.sdt16.eecs293.hw.two;

import java.util.ArrayDeque;
import java.util.ArrayList;


/**
 * This is my sieve. It uses an {@link ArrayList} to store all of the 
 * numbers that haven't been eliminated yet. When it is decided that 
 * a number is defiantly prime, it is added to the {@link ArrayDeque}.
 * I chose a deque, because I wanted to be able to peek at both sides.
 * 
 * 
 * @author Schuyler Thompson (sdt16)
 *
 */

public class SieveOfEratosthenes {
	ArrayList<Integer> numberList = new ArrayList<Integer>();
	ArrayDeque<Integer> primeNumbers = new ArrayDeque<Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SieveOfEratosthenes sieve = new SieveOfEratosthenes();
		
		int maxNum = 0;
		try {
			maxNum = Integer.parseInt(args[0]);
			if (maxNum < 2) {
				throw new NumberFormatException("The argument was less than 2.");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		for (int i = 2; i <= maxNum; i++) {
			sieve.numberList.add(i);
		}
		
		while (!sieve.isNumberListAllPrimes()) {
			if (sieve.primeNumbers.peek() != null) {
				int indexOfNextPrime = sieve.numberList.indexOf(sieve.primeNumbers.peekLast())+1;
				sieve.primeNumbers.add(sieve.numberList.get(indexOfNextPrime));
				sieve.removeNumbers(sieve.numberList.get(indexOfNextPrime));
			} else {
				//empty, start with 2
				sieve.primeNumbers.add(2);
				sieve.removeNumbers(2);
			}
		}
		
		for (Integer primeNumber : sieve.primeNumbers) {
			System.out.println(primeNumber);
		}	
	}

	private void removeNumbers(int i) {
		int maxNum = i*2;
		while (maxNum <= numberList.get(numberList.size()-1)) {
			this.numberList.remove(Integer.valueOf(maxNum));
			this.numberList.trimToSize();
			maxNum += i;
		}	
	}
	
	private boolean isNumberListAllPrimes() {
		Integer lastNumberOfNumberList = this.numberList.get(this.numberList.size()-1);
		Integer lastNumberOfPrimeList = this.primeNumbers.peekLast();
		return lastNumberOfNumberList == lastNumberOfPrimeList;
	}
}
