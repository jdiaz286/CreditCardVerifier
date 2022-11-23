package cs4440Folder;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/*
 * CreditCard class works
 * 
 * CreditCardVerifier (step 1) works as intended
 * 
 * IndustryVerifier (step 2) works as intended
 * 
 * ValidityCheck (step 3) works as intended
 * 
 * OddSumCalc (step 4) works as intended
 */
public class Driver {
	
	public static void main(String[] args) {
		System.out.println("-------------Program with threads-------------");


		/*
		 * instantiate the first step to run in the program
		 */
		CreditCardVerifier step1 = new CreditCardVerifier();

		// execute the first thread and waits for it to finish
		step1.start();
		
		// wait until the thread completes step 1
		try {
			step1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// save all the credit cards that were obtained from thread as array list
		ArrayList<String> cardsSaved = step1.getCards();
		
		/*
		 * since we only care about the time the program takes to determine whether the card is valid or not, we don't need to 
		 * get the time until the user has finished typing in all of the cards that they want to verify
		 */
		Instant start = Instant.now();
		
		// loop through each card that the user saved and asses whether it is valid
		for(int i=0; i<cardsSaved.size(); i++) {
			// save the current card to check 
			String cardSaved = cardsSaved.get(i);
			/*
			 * instantiate the second step to run in the program
			 */
			IndustryVerifier step2 = new IndustryVerifier(cardSaved);
			Thread step2T = new Thread(step2);
	
			// execute the second thread and wait for it to finish
			step2T.start();
			
			// wait until the thread completes step 2
			try {
				step2T.join();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// get the status of whether the card is valid or not from step 2
			boolean step2Valid = step2.getIsValidProvider();
			
			/*
			 * instantiate the third step to run in the program
			 */
			ValidityCheck step3 = new ValidityCheck(cardSaved);
			Thread step3T = new Thread(step3);
			
			// execute the third thread and wait for it to finish
			step3T.start();
			
			// wait until the thread completes step 3
			try {
				step3T.join();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// get the sum of all single digits from step 3
			int step3Sum = step3.getSum();
			
			/*
			 * instantiate the fourth step to run in the program
			 */
			OddSumCalc step4 = new OddSumCalc(cardSaved);
			Thread step4T = new Thread(step4);
			
			// execute the third thread and wait for it to finish
			step4T.start();
			
			// wait until the thread completes step 4
			try {
				step4T.join();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// get the sum of all single digits from step 3
			int step4Sum = step4.getSum();
			
			/*
			 * This represents fifth step in the program
			 */
			int step5Sum = step4Sum + step3Sum;
			
	
			/*
			 * This represents the sixth step in the program
			 */
			if(step5Sum%10 == 0 && step2Valid) {
				System.out.println("The number "+cardSaved + " is valid.\n");
			}
			else {
				if(!step2Valid) {
					System.out.println("Credit card does not have a valid provider.");
				}
				System.out.println("The number "+cardSaved + " is invalid.\n");
			}
		}
		
		Instant end = Instant.now();

		System.out.println("Time to complete the program with threads(in nanoseconds): "+Duration.between(start, end).getNano());
		
	}
}
