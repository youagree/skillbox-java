public class AccountBank {

    protected double account = 0.0;

    public double getAccount() {
        return account;
    }

    public void withdraw(double amountMoney) {
        account += amountMoney;
    }

    public void deposit(double amountMoney) {
        account -= amountMoney;
    }

}
