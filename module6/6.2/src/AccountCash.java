public class AccountCash extends AccountBank {

    @Override
    public void deposit(double amountMoney) {
        this.account -= amountMoney - amountMoney/100;
    }
}
