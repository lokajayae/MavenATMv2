import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import java.io.*;
import java.util.Scanner;

import controller.WithdrawalController;
import model.BankDatabase;
import model.Transaction;
import model.Withdrawal;
import view.Keypad;
import view.Screen;
import model.Constants;
import model.CashDispenser;
import model.Account;

/**
 * Unit test for controller CashDispenser
 */
public class WithdrawalControllerTest {
    private final PrintStream stdOut = System.out;
    private final InputStream stdIn = System.in;
    private ByteArrayOutputStream outputStreamCaptor;
    private Constants constant = new Constants();

    private byte[] toBytes(int i){
      byte[] result = new byte[4];
    
      result[0] = (byte) (i >> 24);
      result[1] = (byte) (i >> 16);
      result[2] = (byte) (i >> 8);
      result[3] = (byte) (i /*>> 0*/);
    
      return result;
    }

    @Before
    public void setUp(){
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown(){
        System.setOut(stdOut);
        System.setIn(stdIn);
    }

    @Test
    public void testCashWithdrawalMenu()
    {
        /**
         * Test Case 4.1.1
         * Author : Evan Lokajaya
         */
    	InputStream input = new ByteArrayInputStream("7".getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        Withdrawal transaction = new Withdrawal(1234, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        wdController.displayMenuOfAmounts();

        assertEquals("\nCash Withdrawal Menu:\r\n"+
                    "1 - $20\r\n" + "2 - $40\r\n" + "3 - $60\r\n" +
                    "4 - $100\r\n" + "5 - $200\r\n" + "6 - Other amount\r\n" +
                    "7 - Cancel transaction\r\n" + "\nChoose option menu: ",
                    outputStreamCaptor.toString());
    } 

    @Test
    public void testMenuWithInputBelowOne()
    {
        /**
         * Test Case 4.3.1
         * Description : Mencoba method run pada cash withdrawal controller dengan input dibawah batas bawah
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("0".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_INPUT_MENU);
    }

    @Test
    public void testMenuWithInputAboveSeven()
    {
        /**
         * Test Case 4.3.2
         * Description : Mencoba method run pada cash withdrawal controller dengan input diatas batas atas
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("8".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_INPUT_MENU);
    }

    @Test
    public void testMenuWithInputBelowOneWithN()
    {
        /**
         * Test Case 4.3.3
         * Description : Mencoba method run pada cash withdrawal controller dengan input dibawah batas bawah
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("-3".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_INPUT_MENU);
    }

    @Test
    public void testMenuWithInputAboveSevenWithN()
    {
        /**
         * Test Case 4.3.4
         * Description : Mencoba method run pada cash withdrawal controller dengan input diatas batas atas
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("9".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_INPUT_MENU);
    }

    @Test
    public void testMenuWithInputSeven()
    {
        /**
         * Test Case 4.3.5
         * Description : Mencoba method run pada cash withdrawal controller dengan input 7
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("7".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_CANCELED);
    }

    @Test
    public void testMenuWithInputOne()
    {
        /**
         * Test Case 4.3.6
         * Description : Mencoba method run pada cash withdrawal controller dengan input 1
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("1".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), 20);
    }

    @Test
    public void testMenuWithInputTwo()
    {
        /**
         * Test Case 4.3.7
         * Description : Mencoba method run pada cash withdrawal controller dengan input 2
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("2".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), 40);
    }

    @Test
    public void testMenuWithInputThree()
    {
        /**
         * Test Case 4.3.8
         * Description : Mencoba method run pada cash withdrawal controller dengan input 3
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("3".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), 60);
    }

    @Test
    public void testMenuWithInputFour()
    {
        /**
         * Test Case 4.3.9
         * Description : Mencoba method run pada cash withdrawal controller dengan input 4
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("4".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), 100);
    }

    @Test
    public void testMenuWithInputFive()
    {
        /**
         * Test Case 4.3.10
         * Description : Mencoba method run pada cash withdrawal controller dengan input 5
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream("5".getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), 200);
    }

    @Test
    public void testOtherAmountInputScreen()
    {
        /**
         * Test Case 4.3.11
         * Description : Menguji menu other amount pada cash withdrawal
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        wdController.displayMenuOfAmounts();

        assertEquals("\nCash Withdrawal Menu:\r\n"+
                    "1 - $20\r\n" + "2 - $40\r\n" + "3 - $60\r\n" +
                    "4 - $100\r\n" + "5 - $200\r\n" + "6 - Other amount\r\n" +
                    "7 - Cancel transaction\r\n" + "\nChoose option menu: " +
                    "\nInput other amount in multiples of 20 :\r\n",
                    outputStreamCaptor.toString());
    }

    @Test
    public void testOtherAmountInputField()
    {
        /**
         * Test Case 4.4.1
         * Description : Menguji hal;aman utama other amount cash withdrawal
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        wdController.displayMenuOfAmounts();

        assertEquals("\nCash Withdrawal Menu:\r\n"+
                    "1 - $20\r\n" + "2 - $40\r\n" + "3 - $60\r\n" +
                    "4 - $100\r\n" + "5 - $200\r\n" + "6 - Other amount\r\n" +
                    "7 - Cancel transaction\r\n" + "\nChoose option menu: " +
                    "\nInput other amount in multiples of 20 :\r\n",
                    outputStreamCaptor.toString());
    }

    @Test
    public void testOtherAmountwithNegativeInput(){
        /**
         * Test Case 4.4.2
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount dibawah range yang disediakan
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "-20").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_POSITIVE_AMOUNT);
    }

    @Test
    public void testOtherAmountwithInputNotMultipleOfTwenty()
    {
        /**
         * Test Case 4.4.3
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount dalam range yang disediakan namun bukan kelipatan 20
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "35").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_MULTIPLE_AMOUNT);
    }

    @Test
    @Ignore
    public void testOtherAmountwithBelowLimitInput()
    {
        /**
         * Test Case 4.4.4
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount dibawah batas yang didefinisikan
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "0").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(constant.WITHDRAWAL_ERROR_RANGE_AMOUNT, wdController.displayMenuOfAmounts());
    }

    @Test
    public void testOtherAmountwithAboveLimitInput()
    {
        /**
         * Test Case 4.4.5
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount diatas range yang disediakan
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "520").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(wdController.displayMenuOfAmounts(), constant.WITHDRAWAL_ERROR_RANGE_AMOUNT);
    }

    @Test
    public void testOtherAmountwithInputExact20()
    {
        /**
         * Test Case 4.4.6
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount tepat di batas bawah
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "20").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(20, wdController.displayMenuOfAmounts());
    }

    @Test
    public void testOtherAmountwithInputExact500()
    {
        /**
         * Test Case 4.4.7
         * Description : Mencoba method run pada cash withdrawal controller dengan input other amount
         * Input amount tepat di batas bawah
         * Author : Evan Lokajaya
         */
        InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "500").getBytes());
        System.setIn(input);
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	Transaction transaction = null;
    	WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        assertEquals(500, wdController.displayMenuOfAmounts());
    }
    
    @Test
    public void testForTestCase448()
    {
        /**
         * Test Case 4.4.8
         * Author : Evan Lokajaya
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 210, 0, 2, 1));
        cashDispenser.dispenseCash(120); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.CASHDISPENSER_NOT_ENOUGH);

    }

    @Test
    public void testForTestCase449()
    {
        /**
         * Test Case 4.4.9
         * Author : Evan Lokajaya
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 210, 0, 2, 1));
        cashDispenser.dispenseCash(140); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.CASHDISPENSER_NOT_ENOUGH);

    }

    @Test
    public void testForTestCase4410()
    {
        /**
         * Test Case 4.4.10
         * Author : Evan Lokajaya
         * Expected Result : transaksi gagal karena available balance tidak mencukupi
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 180, 0, 2, 1));
        cashDispenser.dispenseCash(60); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.BALANCE_NOT_ENOUGH);

    }

    @Test
    public void testForTestCase4411()
    {
        /**
         * Test Case 4.4.11
         * Author : Evan Lokajaya
         * Expected Result : transaksi berhasil, available balance bernilai $110 dan cash dispenser bersisa 1 lembar
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 210, 0, 2, 1));
        cashDispenser.dispenseCash(80); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.WITHDRAW_SUCCESSFUL);
        assertEquals(bankdb.getDataAllAccount()[5].getAvailableBalance(), 110.0, 0);
        assertEquals(cashDispenser.getCount(), 1);

    }

    @Test
    @Ignore
    public void testForTestCase4412()
    {
        /**
         * Test Case 4.4.12
         * Author : Evan Lokajaya
         * Expected Result : transaksi gagal karena available balance tidak mencukupi
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 200, 0, 2, 1));
        cashDispenser.dispenseCash(80); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.BALANCE_NOT_ENOUGH);

    }

    @Test
    @Ignore
    public void testForTestCase4413()
    {
        /**
         * Test Case 4.4.13
         * Author : Evan Lokajaya
         * Expected Result : transaksi berhasil, available balance bernilai $110 dan cash dispenser bersisa 1 lembar
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 210, 0, 2, 1));
        cashDispenser.dispenseCash(60); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.WITHDRAW_SUCCESSFUL);
        assertEquals(bankdb.getDataAllAccount()[5].getAvailableBalance(), 110.0, 0);
        assertEquals(cashDispenser.getCount(), 1);

    }

    @Test
    @Ignore
    public void testForTestCase4414()
    {
        /**
         * Test Case 4.4.14
         * Author : Zara Veda
 	 * Expected Result : transaksi gagal karena available balance tidak mencukupi
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 200, 0, 2, 1));
        cashDispenser.dispenseCash(60); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.BALANCE_NOT_ENOUGH);
    }

    @Test
    public void testForTestCase4415()
    {
        /**
         * Test Case 4.4.15
         * Author : Zara Veda
	 * Expected Result : transaksi gagal karena available balance tidak mencukupi
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 180, 0, 2, 1));
        cashDispenser.dispenseCash(5); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.BALANCE_NOT_ENOUGH);
    }

    @Test
    @Ignore
    public void testForTestCase4416()
    {
        /**
         * Test Case 4.4.16
         * Author : Zara Veda
	 * Expected Result : transaksi berhasil, available balance tersisa $110, dan sisa cash dispenser 1 lembar
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 200, 0, 2, 1));
        cashDispenser.dispenseCash(5); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.WITHDRAW_SUCCESSFUL);
        assertEquals(bankdb.getDataAllAccount()[5].getAvailableBalance(), 110.0, 0);
        assertEquals(cashDispenser.getCount(), 1);
    }

    @Test
    @Ignore
    public void testForTestCase4417(){
        /**
         * Test Case 4.4.17
         * Author : Zara Veda
	 * Expected Result : transaksi berhasil, available balance tersisa $110, dan sisa cash dispenser 1 lembar
         */
    	InputStream input = new ByteArrayInputStream(("6" + System.lineSeparator() + "100").getBytes());
        System.setIn(input);
        
        Keypad keypad = new Keypad();
        Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();

        //Set Up the PreConditions
        bankdb.addAccount(new Account(4444, 4444, 210, 0, 2, 1));
        cashDispenser.dispenseCash(5); // initial amount is 200 (10 lembar)

        Withdrawal transaction = new Withdrawal(4444, bankdb, cashDispenser);
        WithdrawalController wdController = new WithdrawalController(transaction, keypad, screen);

        transaction.setAmount(100);
        
        //checking the result
        assertEquals(transaction.execute(), constant.WITHDRAW_SUCCESSFUL);
        assertEquals(bankdb.getDataAllAccount()[5].getAvailableBalance(), 110.0, 0);
        assertEquals(cashDispenser.getCount(), 1);
    }

} 