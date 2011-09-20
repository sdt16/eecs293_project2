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
	 * First we set up our 2 lists. The numberList takes every number from 2 to maxNum.
	 * The primeNumbers takes every number we know is prime.
	 * 
	 * First, we call getInput to handle parsing the argument from the command line.
	 * 
	 * Then, we fill in the numberList.
	 * 
	 * Then, while numberList isn't empty, we add the first number in the list to
	 * primeNumbers, because we know that it must be prime. Then we call removeMultiples
	 * to get rid of every multiple of the primeNumber still in the numberList, including
	 * the number itself.
	 * 
	 * When numberList is empty, we know that every number has been determined to be
	 * prime or composite.
	 * 
	 * Finally, we print out all of the numbers in primeNumbers.
	 * 
	 * @param args args[0] is the number to find primes up to.
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

	/**
	 * This method checks to make sure the args from the command line are valid.
	 * It checks that there is only 1 argument, that the arg is a number, and that the
	 * number is at least 2.
	 * 
	 * If the argument is invalid, quit.
	 * @param args The command line arguments
	 * @return The parsed number from the command line.
	 */
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

	/**
	 * This method removes all of the multiples in the numberList of the prime
	 * number passed in factor. It iterates though the list, and sees if the 
	 * modulus of the number currently being tested to the factor is 0. If it
	 * is, we know it is a multiple of the prime, so it can't be prime itself. 
	 * It is therefore removed. 
	 * 
	 * This method has the advantage of having constant time removal, and iterating
	 * over the list exactly once for every factor.
	 * @param factor The prime number you want to remove multiples of.
	 * @param numberList The numberList you want to remove the multiples from.
	 * @return the new numberList with the multiples removed.
	 */
	private static LinkedList<Integer> removeMultiples(int factor, LinkedList<Integer> numberList) {
		for (Iterator<Integer> it = numberList.iterator(); it.hasNext();) {
			if (it.next() % factor == 0) {
				it.remove();
			} 
		}
		return numberList;
	}
}
