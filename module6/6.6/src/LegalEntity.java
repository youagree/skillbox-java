public class LegalEntity extends Client {

    private static final double TRESHOLD_COMMISSION = 0.01;

    @Override
    public boolean withdraw(double account) {
        double result = this.balance - account + account * TRESHOLD_COMMISSION;
        return checkTransaction(result);
    }

    @Override
    public void replenishAccount(double account) {
        if (account < 100){
            System.out.println("внесите сумму больше 100");
        } else {
            super.replenishAccount(account);
        }
    }
}
