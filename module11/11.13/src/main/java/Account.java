import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Account {

    @Setter(AccessLevel.PACKAGE)
    private long money;

    @Setter(AccessLevel.PACKAGE)
    private int accNumber;

    @Setter(AccessLevel.PACKAGE)
    private volatile Boolean isBlocked = false;

    public Account(Long money, int accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public boolean withdraw(long value) {
        if (this.money >= value) {
            this.money -= value;
            return true;
        }
        return false;
    }

    public void deposit(long value) {
        this.money += value;
    }
}
