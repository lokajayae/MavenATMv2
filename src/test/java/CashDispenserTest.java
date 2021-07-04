import static org.junit.Assert.*;
import org.junit.Test;

import model.CashDispenser;
import model.BankDatabase;
/**
 * Unit test for model Cash Dispenser
 */
public class CashDispenserTest {
    @Test
    public void testSetAmount()
    {
    	BankDatabase bankdb = new BankDatabase();
    	
        CashDispenser cashDispenser = new CashDispenser();
        cashDispenser.dispenseCash(100);

        assertEquals(cashDispenser.getCount(), 5);

    }  
}
