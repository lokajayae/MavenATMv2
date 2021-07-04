import static org.junit.Assert.*;
import org.junit.Test;

import controller.CashDispenserController;
import model.BankDatabase;
import model.Transaction;
import view.Keypad;
import view.Screen;
import model.Constants;
import model.CashDispenser;
/**
 * Unit test for model Cash Dispenser
 */
public class CashDispenserControllerTest {
    @Test
    public void testCashDispenserConstructor()
    {
    	Keypad keypad = new Keypad();
    	Screen screen = new Screen();
    	BankDatabase bankdb = new BankDatabase();
    	CashDispenser cashDispenser = new CashDispenser();
    	CashDispenserController cdController = new CashDispenserController(cashDispenser, keypad, screen);
        assertNotNull(cdController);

    }  
}
