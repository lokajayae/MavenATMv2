package model;

public class Constants {
	// constants corresponding to role user
	public static final int ROLE_ADMIN = 1; // role admin
	public static final int ROLE_INDIVIDUAL_CUSTOMER = 2; // role individual customer
	public static final int ROLE_COMPANY_CUSTOMER = 3; // role company customer
	
	//constants for status account active or blocked
	public static final int STATUS_ACC_ACTIVE = 1; // account active
	public static final int STATUS_ACC_BLOCKED = 0; // account blocked
	
	//constans status login process
	public static final int LOGIN_ERROR_ACC_NOT_REGISTERED = 1;
	public static final int LOGIN_ERROR_INVALID_PIN = 2;
	public static final int LOGIN_ERROR_ACC_BLOCKED = 3;
	public static final int LOGIN_SUCCESS = 4;
	
	// constants corresponding to main menu options
	// customer menu
	public static final int BALANCE_INQUIRY = 1;
	public static final int WITHDRAWAL = 2;
	//public static final int DEPOSIT = 3;
	public static final int TRANSFER = 3;
	public static final int EXIT_CUSTOMER_MENU = 4;
	
	// admin menu
	public static final int UNBLOCK_ACCOUNT_MENU= 1;
	public static final int BLOCK_ACCOUNT_MENU= 2;
	public static final int VIEW_CASHDISPENSER_MENU = 3;
	public static final int ADD_CASHDISPENSER_MENU = 4;
	public static final int ADD_ACCOUNT_MENU = 5;
	public static final int VIEW_ACCOUNT_MENU = 6;
	public static final int EXIT_ADMIN_MENU = 7;
	
	// constant for minimum amount when withdrawal and transfer process
	public final static int MIN_AMOUNT_BALANCE = 100; // 100 dolar
		
	// constant corresponding to menu option to cancel or error proccess on withdrawal menu
	public final static int WITHDRAWAL_OTHER_AMOUNT = 6;
	public final static int WITHDRAWAL_CANCELED = 7;
	public final static int WITHDRAWAL_ERROR_INPUT_MENU = -1;
	public final static int WITHDRAWAL_ERROR_POSITIVE_AMOUNT = -2;
	public final static int WITHDRAWAL_ERROR_MULTIPLE_AMOUNT = -3;
	public final static int WITHDRAWAL_ERROR_RANGE_AMOUNT = -4;
	
	// constant for status after withdrawal, transfer , block, unblock, and add process
    public static final int WITHDRAW_SUCCESSFUL = 0;
    public static final int TRANSFER_SUCCESSFUL = 0;
    public static final int BLOCK_SUCCESSFUL = 0;
    public static final int UNBLOCK_SUCCESSFUL = 0;
    public static final int ADD_ACCOUNT_SUCCESSFUL = 0;
    public static final int BALANCE_NOT_ENOUGH = -1;
    public static final int CASHDISPENSER_NOT_ENOUGH = -2;
    public static final int ACCOUNT_NOT_REGISTERED = -3; // destination account number (transfer) atau block account:  account not found
    public static final int SAME_ACCOUNT_NUMBER = -4; // transfer: destination account number same with login
    public static final int DEST_ACCOUNT_BLOCKED = -5; // transfer: destination account has bee blocked
    public static final int ACCOUNT_REGISTERED = -6; // account already registered
    
    //number of pieces of fraction value 20 in cash dispenser
    public final static int INITIAL_COUNT_CASHDISPINSER = 10;
    public final static int NOMINAL_AMOUNT = 20;
    public final static int MAX_COUNT_CASHDISPINSER = 1000;
   
    // constant corresponding to range value transfer amount
   	public final static int MIN_WITHDRAW_AMOUNT = 20;
   	public final static int MAX_WITHDRAW_AMOUNT = 500;

    // constant corresponding to menu option to cancel or error proccess on transfer menu
 	public final static int TRANSFER_CANCELED = 2;
 	public final static int TRANSFER_ERROR_INPUT_MENU = -1;
 	public final static int TRANSFER_ERROR_POSITIVE_AMOUNT = -2;
 	public final static int TRANSFER_ERROR_RANGE_AMOUNT = -3;
 	public final static int VALIDATION_TRANSFER_SUCCESSFULL = 1;
 	
 	// constant corresponding to range value transfer amount
  	public final static int MIN_TRANSFER_AMOUNT = 1;
  	public final static int MAX_TRANSFER_AMOUNT = 500;
  	
    // constant corresponding to menu option to cancel or error proccess on add cash dispenser menu
  	public final static int ADD_CASHDISPENSER = 1;
  	public final static int ADD_CASHDISPENSER_CANCELED = 4;
  	public final static int ADD_CASHDISPENSER_ERROR_INPUT_MENU = -1;
 	public final static int ADD_CASHDISPENSER_ERROR_POSITIVE_AMOUNT = -2;
 	public final static int ADD_CASHDISPENSER_ERROR_NOT_ENOUGH_SLOT = -3;	
 	
 	// constant corresponding to menu option to cancel or error proccess on add block account menu
 	public final static int BLOCK_ACCOUNT_CANCELED = 2;
 	public final static int BLOCK_ACCOUNT_ERROR_INPUT_MENU = -1;
   	public final static int BLOCK_ACCOUNT_ERROR_INPUT_DIGIT = -2;
   	public final static int BLOCK_ACCOUNT_ERROR_POSITIVE_VALUE = -3;
   	public final static int BLOCK_ACCOUNT_ERROR_ALREADY_BLOCK = -4;
 	public final static int VALIDATION_BLOCK_SUCCESSFULL = 1;
 	
 	// constant corresponding to menu option to cancel or error proccess on add block account menu
 	public final static int UNBLOCK_ACCOUNT_CANCELED = 2;
 	public final static int UNBLOCK_ACCOUNT_ERROR_INPUT_MENU = -1;
   	public final static int UNBLOCK_ACCOUNT_ERROR_INPUT_DIGIT = -2;
   	public final static int UNBLOCK_ACCOUNT_ERROR_POSITIVE_VALUE = -3;
   	public final static int UNBLOCK_ACCOUNT_ERROR_NOT_BLOCKED = -4;
 	public final static int VALIDATION_UNBLOCK_SUCCESSFULL = 1;
 	
 	// constant corresponding to menu option to cancel or error proccess on add Add account menu
 	public final static int ADD_ACCOUNT_CANCELED = 3;
 	public final static int ADD_ACCOUNT_ERROR_INPUT_MENU = -1;
   	public final static int ADD_ACCOUNT_ERROR_INPUT_DIGIT_ACC = -2;
   	public final static int ADD_ACCOUNT_ERROR_POSITIVE_VALUE_ACC = -3;
   	public final static int ADD_ACCOUNT_ERROR_POSITIVE_VALUE_PIN = -4;
   	public final static int ADD_ACCOUNT_ERROR_INPUT_DIGIT_PIN = -5;
   	public final static int ADD_ACCOUNT_ERROR_POSITIVE_VALUE_AVBALANCE = -6;
   	public final static int ADD_ACCOUNT_ERROR_POSITIVE_VALUE_DEPOSIT = -7;
   	public final static int ADD_ACCOUNT_ERROR_INPUT_RANGE_AVBALANCE = -8;
   	public final static int ADD_ACCOUNT_ERROR_INPUT_RANGE_DEPOSIT = -9;
 	public final static int VALIDATION_ADD_SUCCESSFULL = 1;
 	
}
