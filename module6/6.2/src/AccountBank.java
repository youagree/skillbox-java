public class AccountBank {

    protected double account = 0.0;

    public double getAccount() {
        return account;
    }

    public void setAccountAdd(double amountMoney) {
        account = account + amountMoney;
    }

    public void setAccountRemove(double amountMoney) {
        account = account - amountMoney;
    }

}
