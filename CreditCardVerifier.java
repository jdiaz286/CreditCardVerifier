package cs4440Folder;

import java.util.ArrayList;
import java.util.Scanner;

// this class extends thread to make sure that the user gives in a valid input that is between
// 13 and 19 digits, if not then the program will loop until the above 
// condition is met
public class CreditCardVerifier extends Thread{
	private Scanner input = new Scanner(System.in);
	private ArrayList<String> cards = new ArrayList<String>();
	//private String cardValue;
	
	@Override
	public void run() {
		System.out.print("Type in the number of credit cards you would like to check: ");
		int numOfCards = input.nextInt();
		System.out.println("--------------------------------------------------------------");
		
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
		
	}
	
	// method to get the card info
	public String saveCardInfo() {
		// print out the info on the card
		System.out.print("Type in a credit card number between 13 & 19 digits long: ");
		
		// save the user input as a string
		String userCard = input.next();
		
		// return user card info
		return userCard;
	}
	
	
	// assuming that the card is accepted, this method will return valid card
	public ArrayList<String> getCards() {
		return cards;
	}
}
