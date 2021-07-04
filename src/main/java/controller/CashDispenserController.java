package controller;

import static model.Constants.*;

import model.CashDispenser;
import view.Keypad;
import view.Screen;

public class CashDispenserController {
	 private CashDispenser cashDispenser; // ATM's cash dispenser
	 Keypad keypad;
	 Screen screen;
	    
	 public CashDispenserController(CashDispenser atmCashDispenser, Keypad theKeypad, Screen theScreen) {
		 cashDispenser = atmCashDispenser;
		 keypad = theKeypad;
		 screen = theScreen;
	 }
	 
	 public void displayCashDispenser() {
		 int totalCashAmount = cashDispenser.getTotalAmountCashDispenser();
		 int slotLeft = cashDispenser.getSlotCashLeft();
		 
		 screen.displayMessageLine("\nCash Dispenser Information:");
		 screen.displayMessageLine(" - Total number of sheet with nominal amount $" + NOMINAL_AMOUNT + ": "+ cashDispenser.getCount() + " sheet");
		 screen.displayMessage(" - Total cash in dispenser is ");
		 screen.displayDollarAmount(totalCashAmount);
		 screen.displayMessageLine("\n - In the slot cash dispenser, there are " + slotLeft + " empty sheets slots left");
		 screen.displayMessageLine("\nPress \"Enter\"");
	     keypad.pressEnter(); // user press enter
	 }
	 
	 public void addCashDispenser() {
		 int numberSheet;
		 
		 // loop while no valid choice has been made on add cash dispenser option menu
		 do {
			 numberSheet = displayMenuOfAmounts();
			 while (numberSheet <= 0) {
				 switch(numberSheet) {
				 	case ADD_CASHDISPENSER_ERROR_POSITIVE_AMOUNT:
				 		screen.displayMessageLine("value number of sheet not valid, must be positif value");
				 		break;
				 	case ADD_CASHDISPENSER_ERROR_NOT_ENOUGH_SLOT:
				 		screen.displayMessageLine("Your cash dispenser not enough space");
				 		break;
				 	case ADD_CASHDISPENSER_ERROR_INPUT_MENU :
						screen.displayMessageLine("Invalid selection. Try again!");
						break;
				 }
				 numberSheet = displayMenuOfAmounts();
			 }
			 
			 if(numberSheet != ADD_CASHDISPENSER_CANCELED) {
				 cashDispenser.addCount(numberSheet);
				 screen.displayMessageLine("Cash dispenser has been added");
			 } 
		 }while(numberSheet!=ADD_CASHDISPENSER_CANCELED);
		 screen.displayMessageLine("You canceled transaction");
	 }
	 
	 private int displayMenuOfAmounts() {
		 int userChoice = 0; // local variable to store return value
		 int inputSheet;
		 
		// array of amounts to correspond to menu number sheet has to be add
		int[] sheets = {0, 50, 100};
		      
		 // display the add cash menu
		 screen.displayMessageLine("\nThe number of cash dispenser added");
		 screen.displayMessageLine("1 - 50 Sheet");
		 screen.displayMessageLine("2 - 100 Sheet");
		 screen.displayMessageLine("3 - Other Number");
		 screen.displayMessageLine("4 - Cancel transaction");
		 screen.displayMessage("\nChoose option menu: ");

		 int input = keypad.getInput(); // get user input through keypad
		 // determine how to proceed based on the input value
		 switch (input) {
		 	case 1: // if the user chose a add cash dispenser amount 
			case 2: // (i.e., chose option 1, 2), return the
				if (!isMaxNumberSheetValid(sheets[input])) { // check slot dispenser masih cukup (V2)
	        		userChoice = ADD_CASHDISPENSER_ERROR_NOT_ENOUGH_SLOT;
				}else {
					userChoice = sheets[input]; // save user's choice
	        	}
				break;       
		 	case 3: // do add cash amount in dispenser
		 		screen.displayMessage("Input number of sheet with nominal amount $" + NOMINAL_AMOUNT + ":");
				inputSheet = keypad.getInput(); // get user input through keypad
				if (!validPositiveAddCashAmount(inputSheet)) { // check input lembaran uang harus positif value (V1)
	        		userChoice = ADD_CASHDISPENSER_ERROR_POSITIVE_AMOUNT;
	        	}else if (!isMaxNumberSheetValid(inputSheet)) { // check slot dispenser masih cukup (V2)
	        		userChoice = ADD_CASHDISPENSER_ERROR_NOT_ENOUGH_SLOT;
				}else {
	        		userChoice = inputSheet;
	        	}
		 		break;       
		 	case ADD_CASHDISPENSER_CANCELED: // the user chose to cancel
		 		userChoice = ADD_CASHDISPENSER_CANCELED; // save user's choice
		 		break;
		 	default: // the user did not enter a value from 1, 2, 3, and 4
		 		userChoice = ADD_CASHDISPENSER_ERROR_INPUT_MENU;
			} 

			return userChoice; // return add cash dispenser amount or CANCELED
	 }
	 
	 private boolean validPositiveAddCashAmount(int sheetAmount) {
		 if(sheetAmount > 0) {
			 return  true;
		 }else {
			 return false;
		 }
	}
	 
	private boolean isMaxNumberSheetValid(int sheetAmount){
		if((cashDispenser.getCount()+sheetAmount) <= MAX_COUNT_CASHDISPINSER) {
			return true;
		}else {
			return false;
		}		
	}
}
