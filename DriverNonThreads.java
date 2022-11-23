package cs4440Folder;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Step 1: *
 * Step 2: *
 * Step 3: *
 * Step 4: *
 * Step 5: *
 * Step 6: *
 * 
 */
public class DriverNonThreads {
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("-------------Program without threads-------------");
		System.out.print("Type in the number of credit cards you would like to check: ");
		int numOfCards = input.nextInt();
		System.out.println("--------------------------------------------------------------");		
		
		ArrayList<String> cards = step1(numOfCards);
		
		Instant start = Instant.now();
		
		for(int i=0; i<cards.size(); i++) {
			// save the current card to check 
			String currentCard = cards.get(i);
			
			//execute step 2
			boolean isStep2Valid = step2(currentCard);
			
			// execute step 3
			int step3Sum = step3(currentCard);
			
			// execute step 4
			int step4Sum = step4(currentCard);
			
			// execute step 5 
			int step5Sum = step5(step4Sum, step3Sum);
			
			//execute step 6
			step6(step5Sum, isStep2Valid, currentCard);
		}
		
		Instant end = Instant.now();

		System.out.println("Time to complete the program with threads(in nanoseconds): "+Duration.between(start, end).getNano());
	}
	
	public static ArrayList<String> step1(int numOfCards) {
		ArrayList<String> cards = new ArrayList<String>();
		// repeat until the desired number of cards are accepted
		for(int i=0; i<numOfCards; i++) {
			// save the user card as a String
			String userCard = "";
	
			// loop until the card is between 13 and 19 digits long
			do{
				// ask the user for a credit card number and save as a string
				userCard = saveCardInfo();
				
				// if wrong size or contains a letter in the string, let the user know
				if(userCard.length()>19 || userCard.length()<13 || !userCard.matches("[0-9]+")) {
					System.out.println("invalid card, please type in a card that is between 13-19 digits\n");
				}
				
			}while(userCard.length()<13 || userCard.length()>19 || !userCard.matches("[0-9]+"));
			
			// save the accepted card to the class as attribute
			cards.add(userCard);
			//cardValue = userCard;
			
			
			// tell the user that the card was accepted
			System.out.println("Credit card saved!\n");
		}
		return cards;
	}
	
	public static boolean step2(String card) {
		boolean isValidProvider=false;
		// save the first digit in the credit card
		int firstValue = Integer.parseInt(Character.toString(card.charAt(0)));
		
		// depending on the first digit, print correct provider or let
		// user know that the card is not valid
		switch (firstValue) {
		  case 3:
			    System.out.println("Credit card is with American Express");
			    isValidProvider=true;
			    break;
		  case 4:
			    System.out.println("Credit card is with Visa");
			    isValidProvider=true;
			    break;
		  case 5:
			    System.out.println("Credit card is with MasterCard");
			    isValidProvider=true;
			    break;
		  case 6:
			    System.out.println("Credit card is with Discover");
			    isValidProvider=true;
			    break;
		  default:
		    //System.out.println("The credit card is not valid");
		    isValidProvider=false;
		}
		
		return isValidProvider;
	}
	
	public static int step3(String card) {
		int returnedSum = 0;
		// variable to keep track of the sum in the loop
		int currentSum = 0;
		
		// loop through all second digits in the string
		for(int i=card.length()-2; i>=0; i-=2) {
			// get the current number from string and save as int
			int currentDigit = Integer.parseInt(Character.toString(card.charAt(i)));
			
			// multiply the current digit by 2
			currentDigit = currentDigit*2;
			
			// check if the current value is greater than or equal to 10
			if(currentDigit>=10) {
				// change the integer to a string to manipulate
				String digits = Integer.toString(currentDigit);
				
				// save each digit from the string above
				int firstDigit = Integer.parseInt(Character.toString(digits.charAt(0)));
				int secondDigit = Integer.parseInt(Character.toString(digits.charAt(1)));
				
				// add both digits
				int sum = firstDigit+secondDigit;
				
				// add the sum of both digits to the current sum
				currentSum+=sum;
				
			}
			else {
				currentSum+=currentDigit;
			}
			
		}
		returnedSum = currentSum;
		return returnedSum;
	}
	
	public static int step4(String card) {
		int sum = 0;
		// variable to keep track of the sum in the loop
		int currentSum = 0;
		
		// loop through all second digits in the string
		for(int i=card.length()-1; i>=0; i-=2) {
			// get the current number from string and save as int
			int currentDigit = Integer.parseInt(Character.toString(card.charAt(i)));
			
			// add the current digit to the sum
			currentSum+=currentDigit;
			
		}
		sum = currentSum;
		return sum;
	}	

	public static int step5(int step4Sum, int step3Sum) {
		return step4Sum + step3Sum;
	}
	
	public static void step6(int step5Sum, boolean step2Valid, String cardSaved) {
		
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
	
	// helper method in step 1
	public static String saveCardInfo() {
		// print out the info on the card
		System.out.print("Type in a credit card number between 13 & 19 digits long: ");
		
		// save the user input as a string
		String userCard = input.next();
		
		// return user card info
		return userCard;
	}
}
