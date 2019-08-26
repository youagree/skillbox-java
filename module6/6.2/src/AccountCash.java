public class AccountCash extends AccountBank {

    public void setAccountRemove(double amountMoney) {
        super.account = super.account - amountMoney - amountMoney/100;
    }
}
