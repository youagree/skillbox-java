import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

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
        firstAccount = new Account(new AtomicLong(50000L), 1);
        secondAccount = new Account(new AtomicLong(50000L), 2);
        thirdAccount = new Account(new AtomicLong(50000L), 3);
        fourthAccount = new Account(new AtomicLong(60000L), 4);
        fifthAccount = new Account(new AtomicLong(60000L), 5);

        accounts.put(1, firstAccount);
        accounts.put(2, secondAccount);
        accounts.put(3, thirdAccount);
        accounts.put(4, fourthAccount);
        accounts.put(5, fifthAccount);

        bank.setAccounts(accounts);
    }

    @Test
    public void testTransferOneThread() throws InterruptedException {
        bank.transfer(1, 2, 1000);
        long actualFrom = firstAccount.getMoney().get();
        long expectedFrom = 49000;
        long actualTo = secondAccount.getMoney().get();
        long expectedTo = 51000;
        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void testTransferManyThread() throws InterruptedException {
        IntStream.range(0, 1000).forEach(i -> {
            new Thread(() -> {
                try {
                    bank.transfer(1, 2, 1);
                    bank.transfer(1, 3, 1);
                    bank.transfer(3, 1, 1);
                    bank.transfer(3, 2, 1);
                    bank.transfer(2, 1, 1);
                    bank.transfer(2, 3, 1);
                    bank.transfer(1, 3, 1);
                    bank.transfer(2, 3, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        Thread.sleep(100);
        long actualA1 = firstAccount.getMoney().get();
        long expectedA1 = 49000;
        long actualA2 = secondAccount.getMoney().get();
        long expectedA2 = 49000;
        long actualA3 = thirdAccount.getMoney().get();
        long expectedA3 = 52000;

        assertEquals(expectedA1, actualA1);
        assertEquals(expectedA2, actualA2);
        assertEquals(expectedA3, actualA3);
    }

    @Test
    public void testIfFraud() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                try {
                    bank.transfer(4, 5, 51000);
                    bank.transfer(5, 4, 51000);
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
        assertEquals(50000L,bank.getBalance(1));
    }
}
