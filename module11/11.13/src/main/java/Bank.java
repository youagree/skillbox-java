import lombok.Data;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Bank {

    private HashMap<Integer, Account> accounts = fillAccounts();
    private final Random random = new Random();

    public synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException {
        Account from = accounts.get(fromAccountNum);
        Account to = accounts.get(toAccountNum);
        if (from.getIsBlocked().get() || to.getIsBlocked().get()) {
            return;
        }

        if (from.withdraw(amount)) {
            to.deposit(amount);
        }

        if (amount > 50000) {
            if (isFraud(from.getAccNumber(), to.getAccNumber(), amount)) {
                from.getIsBlocked().set(true);
                to.getIsBlocked().set(true);
            }
        }
    }

    public long getBalance(int accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney().get();
    }

    private static HashMap<Integer, Account> fillAccounts() {
        HashMap<Integer, Account> accountMap = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            long initialValue = (long) (80000 + 20000 * Math.random());
            Account account = new Account(new AtomicLong(initialValue), i);
            accountMap.put(i, account);
        }
        return accountMap;
    }
}