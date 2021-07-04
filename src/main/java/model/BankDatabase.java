package model;

import java.util.ArrayList;
import static model.Constants.*;

public class BankDatabase {
	private ArrayList <Account> accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new ArrayList<Account>();
      // just 4 accounts for testing
      // parameter pembuatan akun {account number, pin, available balance, deposito, role, status account (actif/terblokir)}
      accounts.add(new Account(1234, 4321, 1000.0, 1200.0, 2, 1)); // Nasabah perorangan, active, total saldo 2200
      accounts.add(new Account(8765, 5678, 200.0, 0.0, 2, 1)); // Nasabah perorangan, active, total saldo 200
      accounts.add(new Account(1235, 5321, 500.0, 500.0, 2, 1)); // Nasabah perorangan, active, total saldo 1000
      accounts.add(new Account(2205, 2205, 1500.0, 0.0, 2, 0)); // Nasabah perorangan, block, total saldo 1500
      accounts.add(new Account(1111, 1234, 0, 0, 1, 1)); // Admin, active
      
      //public Account(int theAccountNumber, int thePIN, 
    	      //double theAvailableBalance, double theTotalBalance)
   }
   
   
   public Account[] getDataAllAccount() {
	   return accounts.toArray(new Account[accounts.size()]);
   }
   
   public void addAccount(Account newAccount) {
	   accounts.add(newAccount);
   }

   public Account[] getListAccountByRoleStatus(int roleID, int status) {
	   ArrayList <Account> result = new ArrayList();
	   int sum, idx;
	      
	   idx = 0;
	   sum = 0;
	   while (idx<accounts.size()) {
		   if(accounts.get(idx).getRoleUser() == roleID && accounts.get(idx).getStatusActive() == status) {
			   // add account role to list account
			   result.add(accounts.get(idx));
		   }
		   idx++;
	   }
	   return result.toArray(new Account[result.size()]);     
   } 
   
   private Account getAccount(int accountNumber) {
      int i;
      boolean ketemu;
      Account accData = null;
      
      i = 0;
      ketemu = false;
      while (i<accounts.size() && !ketemu) {
    	  if(accounts.get(i).getAccountNumber() == accountNumber) {
    		// akun ATM terdaftar dalam mesin ATM
    		  ketemu = true;
			  accData = accounts.get(i);
    	  }
		i++;
	  }
      return accData; 
      
   } 
   
   public int authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
    	  if (userAccount.validatePIN(userPIN)) {
    		  if(userAccount.isAccActive()) {
    			  return LOGIN_SUCCESS;  
    		  }else {
    			return LOGIN_ERROR_ACC_BLOCKED;  
    		  }
    	  }else {
    		  return LOGIN_ERROR_INVALID_PIN;
    	  }
      }else {
    	  return LOGIN_ERROR_ACC_NOT_REGISTERED;  
      }
   } 

   public boolean isAccountExist(int userAccountNumber) {
	   Account userAccount = getAccount(userAccountNumber);
	   // if account exists, return true
	   if (userAccount != null) {
		   return true;
	   }else {
		   return false; // account number not found, so return false
	   }
   }
   
   public int getRoleAccount(int userAccountNumber) {
	   return getAccount(userAccountNumber).getRoleUser();
   }
   
   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   
   public void blockingAcc(int userAccountNumber) {
	   getAccount(userAccountNumber).setStatusBlocked();
   }
   
   public void unblockingAcc(int userAccountNumber) {
	   getAccount(userAccountNumber).setStatusActive();
   }
   
   public boolean isAccountActive(int userAccountNumber) {
	   return (getAccount(userAccountNumber).isAccActive());
   }
   
   public boolean isAccountBlocked(int userAccountNumber) {
	   return (getAccount(userAccountNumber).isAccBlocked());
   }
} 