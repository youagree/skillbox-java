import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TransferTest {

    public HashMap<Integer, Account> accounts = new HashMap<>();
    public Account firstAccount;
    public Account secondAccount;
    public Account thirdAccount;
    public Account fourthAccount;
    public Account fifthAccount;
    public Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
        firstAccount = new Account(50000L, 1);
        secondAccount = new Account(50000L, 2);
        thirdAccount = new Account(50000L, 3);
        fourthAccount = new Account(60000L, 4);
        fifthAccount = new Account(60000L, 5);

        accounts.put(1, firstAccount);
        accounts.put(2, secondAccount);
        accounts.put(3, thirdAccount);
        accounts.put(4, fourthAccount);
        accounts.put(5, fifthAccount);

        bank.setAccounts(accounts);
    }

    @Test
    public void testTransferOneThread() throws InterruptedException {
        bank.transfer(1, 2, 1000L);
        long actualFrom = firstAccount.getMoney();
        long expectedFrom = 49000L;
        long actualTo = secondAccount.getMoney();
        long expectedTo = 51000L;
        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void testTransferManyThread() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    bank.transfer(1, 2, 1L);
                    Thread.sleep(100);
                    bank.transfer(1, 3, 1L);
                    Thread.sleep(100);
                    bank.transfer(3, 1, 1L);
                    Thread.sleep(100);
                    bank.transfer(3, 2, 1L);
                    Thread.sleep(100);
                    bank.transfer(2, 1, 1L);
                    Thread.sleep(100);
                    bank.transfer(2, 3, 1L);
                    Thread.sleep(100);
                    bank.transfer(1, 3, 1L);
                    Thread.sleep(100);
                    bank.transfer(2, 3, 1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
        }

        Thread.sleep(100);
        long actualA1 = firstAccount.getMoney();
        long expectedA1 = 49000L;
        long actualA2 = secondAccount.getMoney();
        long expectedA2 = 49000L;
        long actualA3 = thirdAccount.getMoney();
        long expectedA3 = 52000L;

        assertEquals(expectedA1, actualA1);
        assertEquals(expectedA2, actualA2);
        assertEquals(expectedA3, actualA3);
    }

    @Test
    public void testIfFraud() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                try {
                    bank.transfer(4, 5, 51000L);
                    bank.transfer(5, 4, 51000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();
        }
        boolean actualFrom = fourthAccount.getIsBlocked();
        boolean actualTo = fifthAccount.getIsBlocked();
        assertTrue(actualFrom);
        assertTrue(actualTo);
    }

    @Test
    public void getBalance() throws InterruptedException {
        assertEquals(50000L, bank.getBalance(1));
    }
}
