package controller;

import static model.Constants.*;

import model.BlockAccount;
import model.Transaction;
import view.Keypad;
import view.Screen;

public class BlockAccountController extends TransactionController{
	private int statusBlock; //status transaction process
	private int accBlock; // account number which blocked
	
	public BlockAccountController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
		super(theTransaction, theKeypad, theScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		BlockAccount transaction = (BlockAccount) getTransaction();
		Screen screen = getScreen(); // get screen reference
		int statusProcess;
		
		 // loop while no valid choice has been made on block account option menu
		do {
			displayMenuOfAccounts();
			 while (statusBlock <= 0) {
				 switch(statusBlock) {
				 	case BLOCK_ACCOUNT_ERROR_INPUT_DIGIT:
				 		screen.displayMessageLine("The number of digit account number must be equal 4 digits");
				 		break;
				 	case BLOCK_ACCOUNT_ERROR_POSITIVE_VALUE:
				 		screen.displayMessageLine("Value account number not valid, must be positif value");
				 		break;
				 	case BLOCK_ACCOUNT_ERROR_INPUT_MENU :
						screen.displayMessageLine("Invalid selection. Try again!");
						break;
				 }
				 displayMenuOfAccounts();
			 }
			 
			 transaction.setAccNumBlock(accBlock);
			 transaction.setStatusBlock(statusBlock);
			 statusProcess = transaction.execute();
			 switch (statusProcess) {
					case ACCOUNT_NOT_REGISTERED:
						screen.displayMessageLine("Account number not registered in system");
						break;
					case BLOCK_ACCOUNT_ERROR_ALREADY_BLOCK:
						screen.displayMessageLine("Account number has already blocked");
						break;
					case BLOCK_ACCOUNT_CANCELED :
						screen.displayMessageLine("You canceled transaction");
						break;
					case BLOCK_SUCCESSFUL :
						screen.displayMessageLine("Account number has been blocked");
						break;	
				}
			}while(statusProcess!=BLOCK_ACCOUNT_CANCELED);
	}
	
	
	 private void displayMenuOfAccounts() {
		 int inputSheet;
		 statusBlock = 0;
		 accBlock = 0;
		      
		 // display the add cash menu
		 screen.displayMessageLine("\nBlock Account Menu:");
		 screen.displayMessageLine("1 - Input Transaction");
		 screen.displayMessageLine("2 - Cancel Transaction");
		 screen.displayMessage("\nChoose option menu: ");

		 int input = keypad.getInput(); // get user input through keypad
		 // determine how to proceed based on the input value
		 switch (input) {
		 	case 1: // do block account number process
		 		screen.displayMessage("Input the account number to be blocked:");
				inputSheet = keypad.getInput(); // get user input through keypad
				if (!isValidAccountNumberDigit(inputSheet)) { // cek jumlah digit account number apakah 4? (V1)
	        		statusBlock = BLOCK_ACCOUNT_ERROR_INPUT_DIGIT;
				}else if (!isValidAccountNumberValue(inputSheet)) { // cek account number bernilai positif (V2)
					statusBlock = BLOCK_ACCOUNT_ERROR_POSITIVE_VALUE;
	        	}else {
					statusBlock = VALIDATION_BLOCK_SUCCESSFULL;
					accBlock = inputSheet;
	        	}
		 		break;       
		 	case BLOCK_ACCOUNT_CANCELED: // the user chose to cancel
		 		statusBlock = BLOCK_ACCOUNT_CANCELED; // save user's choice
		 		break;
		 	default: // the user did not enter a value from 1-2
		 		statusBlock = BLOCK_ACCOUNT_ERROR_INPUT_MENU;
			} 
	 }

	 private boolean isValidAccountNumberValue(int AccountNumber) {
		 if(AccountNumber > 0) {
			 return  true;
		 }else {
			 return false;
		 }
	}
	 
	 private boolean isValidAccountNumberDigit(int AccountNumber) {
		 int digitValue = 0;
		 AccountNumber = Math.abs(AccountNumber);
		 while (AccountNumber>0) {
			 AccountNumber = AccountNumber / 10;
			 digitValue = digitValue + 1;
		 }
		 if(digitValue == 4) {
			 return  true;
		 }else {
			 return false;
		 }
	}
}
