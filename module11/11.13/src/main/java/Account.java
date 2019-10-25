import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class Account {

    @Setter(AccessLevel.PACKAGE)
    private AtomicLong money;

    @Setter(AccessLevel.PACKAGE)
    private int accNumber;

    @Setter(AccessLevel.PRIVATE)
    private AtomicBoolean isBlocked = new AtomicBoolean(false);

    public Account(AtomicLong money, int accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public synchronized boolean withdraw(long value) {
        if (this.money.get() >= value) {
            this.money.updateAndGet(e -> e - value);
            return true;
        }
        return false;
    }

    public synchronized void deposit(long value) {
        this.money.updateAndGet(e -> e + value);
    }
}
