import static org.junit.Assert.*;
import org.junit.Ignore;

import org.junit.Test;
import org.junit.Ignore;

import model.BankDatabase;
import model.CashDispenser;
import model.Withdrawal;
import model.Account;
import static model.Constants.*;

/**
 * Unit test for model Cash Withdrawal
 */
public class WithdrawalModelTest {

	private BankDatabase bankDatabase;
	private CashDispenser cashDispenser;
    private int userAccountNumber;

    @Test
    public void constructorCreationSucceedTest()
    {
        /**
         * Unit Test
         * Description: menguji proses pembuatan objek model Withdrawal.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
		cashDispenser = new CashDispenser();
        userAccountNumber = 1234;
        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        assertNotNull(withdrawal);
    }

    @Test
    public void testSetAmount()
    {
        /**
         * Unit Test
         * Description : Menguji fungsi set amount pada model withdrawal
         * Author
         */
        BankDatabase bd = new BankDatabase();
        CashDispenser cd = new CashDispenser();
        Withdrawal withdrawal = new Withdrawal(1234, bd, cd);
        withdrawal.setAmount(90);
        assertEquals(withdrawal.getAmount(), 90);
    }

    @Test
    public void getAmountTest()
    {
        /**
         * Unit Test
         * Description: Menguji fungsionalitas fungsi getAmount.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1234;
		cashDispenser = new CashDispenser();
        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        
        int expectedAmount = 100;
        withdrawal.setAmount(expectedAmount);
        int actualAmount = withdrawal.getAmount();
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    @Ignore
    public void executeRemainingCashDispenserEqualToZeroTest()
    {
        /**
         * Unit Test
         * Description: Menguji proses Withdrawal jika Cash Dispenser kurang.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1212;
        bankDatabase.addAccount(new Account(userAccountNumber, 4321, 100.0, 200.0, 2, 1));
		cashDispenser = new CashDispenser();

        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        withdrawal.setAmount(100);
        int actualStatus = withdrawal.execute();
        assertEquals(CASHDISPENSER_NOT_ENOUGH, actualStatus);
    }

    @Test
    @Ignore
    public void executeRemainingCashDispenserEqualToOneTest()
    {
        /**
         * Unit Test
         * Description: Menguji proses Withdrawal jika Cash Dispenser kurang.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1212;
        bankDatabase.addAccount(new Account(userAccountNumber, 4321, 100.0, 200.0, 2, 1));
		cashDispenser = new CashDispenser();

        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        withdrawal.setAmount(80);
        int actualStatus = withdrawal.execute();
        assertEquals(CASHDISPENSER_NOT_ENOUGH, actualStatus);
    }

    @Test
    public void executeBalanceNotEnoughTest()
    {
        /**
         * Unit Test
         * Description: Menguji proses Withdrawal jika Avl. Balance kurang.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1235;
        cashDispenser = new CashDispenser();
        cashDispenser.addCount(25);

        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        withdrawal.setAmount(500);
        int actualStatus = withdrawal.execute();
        assertEquals(BALANCE_NOT_ENOUGH, actualStatus);
    }

    @Test
    public void executeWithdrawSuccessfulTest()
    {
        /**
         * Unit Test
         * Description: Menguji proses Withdrawal jika Avl. Balance dan Cash Dispenser cukup.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1234;
		cashDispenser = new CashDispenser();
        cashDispenser.addCount(5);

        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        withdrawal.setAmount(100);
        int actualStatus = withdrawal.execute();
        assertEquals(WITHDRAW_SUCCESSFUL, actualStatus);
    }

    @Test
    public void executeWithdrawalCanceledTest()
    {
        /**
         * Unit Test
         * Description: Menguji proses pembatalan Withdrawal.
         * Author: Zara Veda
         */
        bankDatabase = new BankDatabase();
        userAccountNumber = 1234;
		cashDispenser = new CashDispenser();
        Withdrawal withdrawal = new Withdrawal(userAccountNumber, bankDatabase, cashDispenser);
        withdrawal.setAmount(7);
        int actualStatus = withdrawal.execute();
        assertEquals(WITHDRAWAL_CANCELED, actualStatus);
    }
}
