package cs4440Folder;

// this class acts as step 3 
public class ValidityCheck implements Runnable{
	private String savedCard;
	private int sum=0;
	
	// constructor that accepts given card value
	public ValidityCheck(String inputCard) {
		savedCard = inputCard;
	}
	
	@Override
	public void run() {
		// variable to keep track of the sum in the loop
		int currentSum = 0;
		
		// loop through all second digits in the string
		for(int i=savedCard.length()-2; i>=0; i-=2) {
			// get the current number from string and save as int
			int currentDigit = Integer.parseInt(Character.toString(savedCard.charAt(i)));
			
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
		sum = currentSum;
	}
	
	public int getSum() {
		return sum;
	}
}
