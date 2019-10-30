import com.sun.org.apache.xpath.internal.operations.Bool;
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

    @Setter(AccessLevel.PACKAGE)
    private volatile Boolean isBlocked = false;

    public Account(AtomicLong money, int accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public boolean withdraw(long value) {
        if (this.money.get() >= value) {
            this.money.getAndUpdate(e -> e - value);
            return true;
        }
        return false;
    }

    public synchronized void deposit(long value) {
        this.money.getAndUpdate(e -> e + value);
    }
}
