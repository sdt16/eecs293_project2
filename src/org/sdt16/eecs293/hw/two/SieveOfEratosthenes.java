package org.sdt16.eecs293.hw.two;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * This is my sieve. It uses an {@link LinkedList} to store all of the 
 * numbers that haven't been eliminated yet. When it is decided that 
 * a number is defiantly prime, it is added to the {@link ArrayDeque}.
 * I chose a deque, because I wanted to be able to peek at both sides.
 * 
 * 
 * @author Schuyler Thompson (sdt16)
 *
 */

public class SieveOfEratosthenes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxNum = getInput(args);
		
		LinkedList<Integer> numberList = new LinkedList<Integer>();
		LinkedList<Integer> primeNumbers = new LinkedList<Integer>();
		
		for (int i = 2; i <= maxNum; i++) {
			numberList.add(i);
		}
		
		while (!numberList.isEmpty()) {
				primeNumbers.add(numberList.getFirst());
				numberList = removeMultiples(numberList.getFirst(), numberList);
		}
		
		for (Integer primeNumber : primeNumbers) {
			System.out.println(primeNumber);
		}	
	}

	private static int getInput(String[] args) {
		int maxNum = 0;
		try {
			if (args.length != 1) {
				throw new IllegalArgumentException("The program takes one argument, the number to find primes up to.");
			}
			maxNum = Integer.parseInt(args[0]);
			if (maxNum < 2) {
				throw new NumberFormatException("The argument was less than 2.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return maxNum;
	}

	private static LinkedList<Integer> removeMultiples(int factor, LinkedList<Integer> numberList) {
		for (Iterator<Integer> it = numberList.iterator(); it.hasNext();) {
			if (it.next() % factor == 0) {
				it.remove();
			} 
		}
		return numberList;
	}
}
