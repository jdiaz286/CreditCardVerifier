package cs4440Folder;

public class OddSumCalc implements Runnable{
	private String savedCard;
	private int sum=0;
	
	// constructor that accepts given card value
	public OddSumCalc(String inputCard) {
		savedCard = inputCard;
	}
	
	@Override
	public void run() {
		// variable to keep track of the sum in the loop
		int currentSum = 0;
		
		// loop through all second digits in the string
		for(int i=savedCard.length()-1; i>=0; i-=2) {
			// get the current number from string and save as int
			int currentDigit = Integer.parseInt(Character.toString(savedCard.charAt(i)));
			
			// add the current digit to the sum
			currentSum+=currentDigit;
			
		}
		sum = currentSum;
	}
	
	public int getSum() {
		return sum;
	}
}
