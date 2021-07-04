package controller;

import static model.Constants.*;


import model.Account;
import model.AddAccount;
import model.Transaction;
import model.UnBlockAccount;
import view.Keypad;
import view.Screen;

public class AddAccountController extends TransactionController{
	private Account accAdded = new Account();
	private int statusAdd; //status transaction process
	
	public AddAccountController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
		super(theTransaction, theKeypad, theScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		AddAccount transaction = (AddAccount) getTransaction();
		Screen screen = getScreen(); // get screen reference
		int statusProcess;
		 // loop while no valid choice has been made on unblock account option menu
		do {
			displayMenuOfAccounts();
			 while (statusAdd <= 0) {
				 switch(statusAdd) {
				 	case ADD_ACCOUNT_ERROR_INPUT_DIGIT_ACC:
				 		screen.displayMessageLine("The number of digit account number must be equal 4 digits");
				 		break;
				 	case ADD_ACCOUNT_ERROR_POSITIVE_VALUE_ACC:
				 		screen.displayMessageLine("Value account number not valid, must be positif value");
				 		break;
				 	case ADD_ACCOUNT_ERROR_INPUT_DIGIT_PIN:
				 		screen.displayMessageLine("The number of digit pin must be equal 4 digits");
				 		break;
				 	case ADD_ACCOUNT_ERROR_POSITIVE_VALUE_PIN:
				 		screen.displayMessageLine("Value pin not valid, must be positif value");
				 		break;
				 	case ADD_ACCOUNT_ERROR_POSITIVE_VALUE_AVBALANCE:
				 		screen.displayMessageLine("Value available balance not valid, must be positif value");
				 		break;
				 	case ADD_ACCOUNT_ERROR_INPUT_RANGE_AVBALANCE:
				 		screen.displayMessageLine("Your balance does not meet the minimum balance standar");
				 		break;
				 	case ADD_ACCOUNT_ERROR_POSITIVE_VALUE_DEPOSIT:
				 		screen.displayMessageLine("Value deposit fund not valid, must be positif value");
				 		break;
				 	case ADD_ACCOUNT_ERROR_INPUT_RANGE_DEPOSIT:
				 		screen.displayMessageLine("Your deposit fund does not meet the minimum deposito standar");
				 		break;
				 	case ADD_ACCOUNT_ERROR_INPUT_MENU :
						screen.displayMessageLine("Invalid selection. Try again!");
						break;
				 }
				 displayMenuOfAccounts();
			 }
			 
			 transaction.setNewAccount(accAdded);
			 transaction.setStatusAdd(statusAdd);
			 statusProcess = transaction.execute();
			 switch (statusProcess) {
				case ACCOUNT_REGISTERED:
					screen.displayMessageLine("Account number has been registered in system”");
					break;
				case ADD_ACCOUNT_CANCELED :
					screen.displayMessageLine("You canceled transaction");
					break;
				case ADD_ACCOUNT_SUCCESSFUL :
					screen.displayMessageLine("Account number has been been add");
					break;	
			}
			}while(statusProcess!=ADD_ACCOUNT_CANCELED);				
	}

	private void displayMenuOfAccounts() {
		// TODO Auto-generated method stub
		Account inputSheet;
		int inputAccNum, inputPinNum;
		double inputAvBalance, inputDeposito;
		String inputDesc;
		
		 // display the add cash menu
		screen.displayMessageLine("\nAdd Account Menu:");
		screen.displayMessageLine("1 - Add Account Nasabah");
		screen.displayMessageLine("2 - Add Account Admin");
		screen.displayMessageLine("3 - Cancel Transaction");
		screen.displayMessage("\nChoose option menu: ");
		
		 int input = keypad.getInput(); // get user input through keypad
		 // determine how to proceed based on the input value
		 switch (input) {
		 	case 1: // do add account number nasabah process
		 	case 2: // do add account number admin process	
		 		screen.displayMessage("Input account number:");		 		
		 		inputAccNum = keypad.getInput(); // input acc number
				
				// validation input account number
				statusAdd = inputAccountNumberValidation(inputAccNum);
				if (statusAdd == VALIDATION_ADD_SUCCESSFULL) {
					screen.displayMessage("Input pin number:");		 		
					inputPinNum = keypad.getInput(); // input pin number
					
					// validation pin number
					statusAdd = pinNumberValidation(inputPinNum);
					if (statusAdd == VALIDATION_ADD_SUCCESSFULL) {
						if(input==1) { // for add account nasabah process
							screen.displayMessage("Input available balance:");		 		
							inputAvBalance = keypad.getInput(); // input available balance
							
							// validation available balance
							statusAdd = availableBalanceValidation(inputAvBalance);
							if (statusAdd == VALIDATION_ADD_SUCCESSFULL) {
								screen.displayMessage("Input deposit fund:");		 		
								inputDeposito = keypad.getInput(); // input deposito
								
								// validation deposit fund
								statusAdd = depositFundValidation(inputDeposito);
								if (statusAdd == VALIDATION_ADD_SUCCESSFULL) {
									screen.displayMessage("Input description Account:");		 		
									inputDesc = keypad.getInputString(); // input deskripsi account
									inputSheet = new Account(inputAccNum, inputPinNum, inputAvBalance, inputDeposito, 
											inputDesc, ROLE_INDIVIDUAL_CUSTOMER, STATUS_ACC_ACTIVE);
									accAdded = inputSheet;
								}
							}
						}else { // for add account admin
							screen.displayMessage("Input description Account:");		 		
							inputDesc = keypad.getInputString(); // input deskripsi account
							// available balance dan deposito diisi dengan nol
							inputSheet = new Account(inputAccNum, inputPinNum, 0, 0, inputDesc, ROLE_ADMIN, STATUS_ACC_ACTIVE);
							accAdded = inputSheet;
						}						
					}
				}
				
		 		break;       
		 	case ADD_ACCOUNT_CANCELED: // the user chose to cancel
		 		statusAdd = ADD_ACCOUNT_CANCELED; // save user's choice
		 		break;
		 	default: // the user did not enter a value from 1-2
		 		statusAdd = ADD_ACCOUNT_ERROR_INPUT_MENU;
			} 
	 }

	private int inputAccountNumberValidation(int inputAccNum) {
		int statusInput;
		
		if (!isValidValueNumberFourDigit(inputAccNum)) { // cek jumlah digit account number apakah 4? (V1)
			statusInput = ADD_ACCOUNT_ERROR_INPUT_DIGIT_ACC;
		}else if (!isValidAccountNumberValue(inputAccNum)) { // cek account number bernilai positif (V2)
			statusInput = ADD_ACCOUNT_ERROR_POSITIVE_VALUE_ACC;
		}else {
			statusInput = VALIDATION_ADD_SUCCESSFULL;
		}
		
		return statusInput;
	}
	
	private int pinNumberValidation(int inputPin) {
		// TODO Auto-generated method stub
		int statusInput;
		
		if (!isValidValueNumberFourDigit(inputPin)) { // cek jumlah digit pin number apakah 4? (V4)
			statusInput = ADD_ACCOUNT_ERROR_INPUT_DIGIT_PIN; 
		}else if (!isValidAccountNumberValue(inputPin)) { // cek pin number bernilai positif (V5)
			statusInput = ADD_ACCOUNT_ERROR_POSITIVE_VALUE_PIN;
		}else {
			statusInput = VALIDATION_ADD_SUCCESSFULL;
		}
		return statusInput;
	}
	 
	private int availableBalanceValidation(double inputAvailableBalance) {
		// TODO Auto-generated method stub
		int statusInput;
		if (!isValidInputAmmount(inputAvailableBalance)) { // cek available balance bernilai positif (V6)
			statusInput = ADD_ACCOUNT_ERROR_POSITIVE_VALUE_AVBALANCE;
		}else if(!isValidInputRangeBalance(inputAvailableBalance)){ // cek available balance dengan syarat minimal saldo $100 (V7)
			statusInput = ADD_ACCOUNT_ERROR_INPUT_RANGE_AVBALANCE;
		}else {
			statusInput = VALIDATION_ADD_SUCCESSFULL;
		}		
		
		return statusInput;
	}

	private int depositFundValidation(double inputDepositFund) {
		// TODO Auto-generated method stub
		int statusInput;
		if (!isValidInputAmmount(inputDepositFund)) { // cek deposit fund bernilai positif (V8)
			statusInput = ADD_ACCOUNT_ERROR_POSITIVE_VALUE_DEPOSIT;
		}else if(!isValidInputRangeDeposit(inputDepositFund)){ // cek deposit fund e dengan syarat minimal saldo $500 (V9)
			statusInput = ADD_ACCOUNT_ERROR_INPUT_RANGE_DEPOSIT;
		}else {
			statusInput = VALIDATION_ADD_SUCCESSFULL;
		}		
		return statusInput;
	}
	
	private boolean isValidAccountNumberValue(int AccountNumber) {
		 if(AccountNumber > 0) {
			 return  true;
		 }else {
			 return false;
		 }
	}
	 
	 private boolean isValidValueNumberFourDigit(int Number) {
		 int digitValue = 0;
		 Number = Math.abs(Number);
		 while (Number>0) {
			 Number = Number / 10;
			 digitValue = digitValue + 1;
		 }
		 if(digitValue == 4) {
			 return  true;
		 }else {
			 return false;
		 }
	}
	
	private boolean isValidInputAmmount(double amount) {
			// TODO Auto-generated method stub
		if(amount>0) {
			return  true;
		}else {
			return false;
		}
	}
	
	private boolean isValidInputRangeBalance(double amount) {
		// TODO Auto-generated method stub
		if(amount>=100) {
			return  true;
		}else {
			return false;
		}
	}
	
	private boolean isValidInputRangeDeposit(double amount) {
		// TODO Auto-generated method stub
		if(amount>=500) {
			return  true;
		}else {
			return false;
		}
	}
}
