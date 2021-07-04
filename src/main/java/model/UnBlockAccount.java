package model;

import static model.Constants.*;

public class UnBlockAccount extends Transaction{
	private int statusBlock; //status block process
	private int accNumUnBlock; // account number which blocked
	
	public UnBlockAccount(BankDatabase atmBankDatabase) {
		super(0, atmBankDatabase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int execute() {
		// TODO Auto-generated method stub
		BankDatabase bankDatabase;
		int status = getStatusBlock();
		int accUnBlock = getAccNumUnBlock();
		
		if (status != UNBLOCK_ACCOUNT_CANCELED) { // cek apakah idxamount bukan canceled (6) 
			// get bankdatabase akun
			bankDatabase = getBankDatabase();
		   		      
			   // some validation
			if (!bankDatabase.isAccountExist(accUnBlock)) { // cek account number terdaftar (V3)
				status = ACCOUNT_NOT_REGISTERED;
			}else if (!bankDatabase.isAccountBlocked(accUnBlock)) { // cek account number sudah terblok (V4)
				status = UNBLOCK_ACCOUNT_ERROR_NOT_BLOCKED;
			}else {// lakukan proses unblock account number:
					// a. set status account to active
					bankDatabase.unblockingAcc(accUnBlock);	   
					status = UNBLOCK_SUCCESSFUL;
			}   

		}else {
			status = UNBLOCK_ACCOUNT_CANCELED;
		}	
		return status;		
		
	}

	public int getStatusBlock() {
		return statusBlock;
	}

	public void setStatusBlock(int statusBlock) {
		this.statusBlock = statusBlock;
	}

	public int getAccNumUnBlock() {
		return accNumUnBlock;
	}

	public void setAccNumUnBlock(int accNumBlock) {
		this.accNumUnBlock = accNumBlock;
	}

}
