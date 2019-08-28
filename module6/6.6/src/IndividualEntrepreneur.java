public class IndividualEntrepreneur extends Client {

    private static final int LOW_COMMISSION_TRESHOLD = 1000;
    private static final double BEFORE_TRESHOLD_COMMISSION = 0.01;
    private static final double AFTER_TRESHOLD_COMMISSION = 0.05;

    public void withdraw(double account) {
        if (account < LOW_COMMISSION_TRESHOLD) {
            this.account = account - account / BEFORE_TRESHOLD_COMMISSION;
        } else {
            this.account = account - account / AFTER_TRESHOLD_COMMISSION;
        }
    }

    @Override
    public void transferOfMoney(Client client, double sum) {
        if (client.getAccount() >= account) {
            this.withdraw(account);
            client.replenishAccount(account);
            System.out.println("GOOD");
        } else {
            System.out.println("you dont have much money");
        }
    }
}
