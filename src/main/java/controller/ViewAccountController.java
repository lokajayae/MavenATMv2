package controller;

import static model.Constants.*;
import model.Account;
import model.BalanceInquiry;
import model.BankDatabase;
import model.Transaction;
import model.ViewAccount;
import view.Keypad;
import view.Screen;

public class ViewAccountController extends TransactionController{

	public ViewAccountController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
		super(theTransaction, theKeypad, theScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO View Account Number
		ViewAccount transaction = (ViewAccount) getTransaction();
		
		// get references to bank database and screen
	    BankDatabase bankDatabase = transaction.getBankDatabase();
	    Screen screen = getScreen();
	    Account[] dataAccount = bankDatabase.getDataAllAccount();
	    Account[] dataAccountAdminAktif = bankDatabase.getListAccountByRoleStatus(ROLE_ADMIN, STATUS_ACC_ACTIVE);
	    Account[] dataAccountAdminDeaktif = bankDatabase.getListAccountByRoleStatus(ROLE_ADMIN, STATUS_ACC_BLOCKED);
	    Account[] dataAccountNasabahAktif = bankDatabase.getListAccountByRoleStatus(ROLE_INDIVIDUAL_CUSTOMER, STATUS_ACC_ACTIVE);
	    Account[] dataAccountNasabahDeaktif = bankDatabase.getListAccountByRoleStatus(ROLE_INDIVIDUAL_CUSTOMER, STATUS_ACC_BLOCKED);
	    int noUrut = 0;
	    int NNasabah = dataAccountNasabahAktif.length + dataAccountNasabahDeaktif.length;
	    int NAdmin = dataAccountAdminAktif.length + dataAccountAdminDeaktif.length;
	    
	    // display the data account information on the screen
	    screen.displayMessageLine("\nAccount Number Information:");
	    screen.displayMessageLine("Registered ["+ NNasabah +"] account number as nasabah and ["+ NAdmin + "] account number as admin");
	    screen.displayMessageLine("------------------------------------------------------------------------");
	    for (int idx = 0; idx<dataAccountNasabahAktif.length; idx++) {
	    	noUrut = noUrut + 1;
	    	screen.displayMessageLine(noUrut + "   - Account Number: " + dataAccountNasabahAktif[idx].getAccountNumber());
	    	screen.displayMessageLine("    - Role Account: " + dataAccountNasabahAktif[idx].getRoleDescription());
	    	screen.displayMessageLine("    - Description Account Number: " + dataAccountNasabahAktif[idx].getDesc());
	    	screen.displayMessageLine("    - Status Account Number: " + dataAccountNasabahAktif[idx].getStatusDescription() + "\n");	
	    }
	    
	    for (int idx = 0; idx<dataAccountNasabahDeaktif.length; idx++) {
	    	noUrut = noUrut + 1;
	    	screen.displayMessageLine(noUrut + "   - Account Number: " + dataAccountNasabahDeaktif[idx].getAccountNumber());
	    	screen.displayMessageLine("    - Role Account: " + dataAccountNasabahDeaktif[idx].getRoleDescription());
	    	screen.displayMessageLine("    - Description Account Number: " + dataAccountNasabahDeaktif[idx].getDesc());
	    	screen.displayMessageLine("    - Status Account Number: " + dataAccountNasabahDeaktif[idx].getStatusDescription() + "\n");	
	    }
	    
	    for (int idx = 0; idx<dataAccountAdminAktif.length; idx++) {
	    	noUrut = noUrut + 1;
	    	screen.displayMessageLine(noUrut + "   - Account Number: " + dataAccountAdminAktif[idx].getAccountNumber());
	    	screen.displayMessageLine("    - Role Account: " + dataAccountAdminAktif[idx].getRoleDescription());
	    	screen.displayMessageLine("    - Description Account Number: " + dataAccountAdminAktif[idx].getDesc());
	    	screen.displayMessageLine("    - Status Account Number: " + dataAccountAdminAktif[idx].getStatusDescription()+ "\n");	
	    }
	    
	    for (int idx = 0; idx<dataAccountAdminDeaktif.length; idx++) {
	    	noUrut = noUrut + 1;
	    	screen.displayMessageLine(noUrut + "   - Account Number: " + dataAccountAdminDeaktif[idx].getAccountNumber());
	    	screen.displayMessageLine("    - Role Account: " + dataAccountAdminDeaktif[idx].getRoleDescription());
	    	screen.displayMessageLine("    - Description Account Number: " + dataAccountAdminDeaktif[idx].getDesc());
	    	screen.displayMessageLine("    - Status Account Number: " + dataAccountAdminDeaktif[idx].getStatusDescription()+ "\n");	
	    }
	    screen.displayMessageLine("------------------------------------------------------------------------");
	}

}
