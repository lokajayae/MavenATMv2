package model;
import static model.Constants.*;

public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double depositFund; // deposit fund
   private double totalBalance; // funds available & pending deposits
   private int roleUser; // 1 sebagai admin; 2 sebagai nasabah perorangan; 3 sebagai nasabah company
   private String desc; // deskripsi Nasabah
   private int statusActive; // status nasabah, 1: status nasabah aktif (tidak diblokir); 
   							 //0: status nasabah tidak aktif (di blokir)  
   
   // inisialisasi akun tanpa default value
   public Account() {
   }
   
   // Account constructor initializes attributes without deposit fund
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double thedepositFund, int role, int status) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      depositFund = thedepositFund;
      totalBalance = theAvailableBalance + thedepositFund; // total balance adalah available balance + deposito
      roleUser = role;
      statusActive = status;
      desc = "-";
   }
   
   public Account(int theAccountNumber, int thePIN, 
	  double theAvailableBalance, double thedepositFund, String theDescAcc, int role, int status) {
	  accountNumber = theAccountNumber;
	  pin = thePIN;
	  availableBalance = theAvailableBalance;
	  depositFund = thedepositFund;
	  totalBalance = theAvailableBalance + thedepositFund;
	  desc = theDescAcc;
	  roleUser = role;
	  statusActive = status;
   }
   
   // Account constructor initializes attributes with deposit fund
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theDepositFund, double theTotalBalance, int role, int status) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      depositFund = theDepositFund;
      totalBalance = theTotalBalance;
      roleUser = role;
      statusActive = status;
      desc = "-";
   }

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
         return false;
      }
   } 

   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 

   public void credit(double amount) {
	   availableBalance = availableBalance - amount;
	   totalBalance = totalBalance - amount;
   }

   public void debit(double amount) {
	   availableBalance = availableBalance + amount;
	   totalBalance = totalBalance + amount;
   }

   public int getAccountNumber() {
      return accountNumber;  
   }

	public int getPin() {
		return pin;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public int getRoleUser() {
		return roleUser;
	}
	
	public String getRoleDescription() {
		String roleDes = "";
		
		if(roleUser == ROLE_ADMIN) {
			roleDes = "admin";
		}else if (roleUser == ROLE_INDIVIDUAL_CUSTOMER) {
			roleDes = "nasabah";
		}
		return roleDes;
		
	}
	
	public void setRoleUser(int roleUser) {
		this.roleUser = roleUser;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public int getStatusActive() {
		return statusActive;
	}

	public void setStatusActive() {
		this.statusActive = STATUS_ACC_ACTIVE;
	}
	
	public void setStatusBlocked() {
		this.statusActive = STATUS_ACC_BLOCKED;
	}
	
	public String getStatusDescription() {
		String statusDes = "";
		
		if(statusActive == STATUS_ACC_ACTIVE) {
			statusDes = "active";
		}else if (statusActive == STATUS_ACC_BLOCKED) {
			statusDes = "deactive";
		}
		return statusDes;
	}
	
	public boolean isAccActive() {
		if(statusActive == STATUS_ACC_ACTIVE) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isAccBlocked() {
		if(statusActive == STATUS_ACC_BLOCKED) {
			return true;
		}else {
			return false;
		}
	}

	public double getDepositFund() {
		return depositFund;
	}

	public void setDepositFund(double depositFund) {
		this.depositFund = depositFund;
	}
} 