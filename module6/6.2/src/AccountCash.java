public class AccountCash extends AccountBank {

    public void deposit(double amountMoney) {
        super.account = super.account - amountMoney - amountMoney/100;
    }
}
