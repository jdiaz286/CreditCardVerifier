package cs4440Folder;

// this class extends runnable to make sure that we are able to pass the credit card to the
// current instance of the program. It will take in the credit card number and ultimately 
// end up determining whether or not the card has a valid provider/company
public class IndustryVerifier implements Runnable{
	private String savedCard;
	private boolean isValidProvider;
	
	// constructor that accepts given card value
	public IndustryVerifier(String input) {
		savedCard=input;
	}
	
	@Override
	public void run() {
		// save the first digit in the credit card
		int firstValue = Integer.parseInt(Character.toString(savedCard.charAt(0)));
		
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
	}
	
	
	// send back a boolean value to confirm the card has valid provider 
	public boolean getIsValidProvider() {
		return isValidProvider;
	}
}
