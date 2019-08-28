public class LegalEntity extends Client {

    private static final double TRESHOLD_COMMISSION = 0.01;

    public void withdraw(double account) {
        this.account = this.account - account - account / TRESHOLD_COMMISSION;
    }

    @Override
    public void transferOfMoney(Client client, double account) {
        if (account > 100) {
            if (client.getAccount() > account) {
                this.withdraw(account);
                client.replenishAccount(account);
                System.out.println("GOOD");
            } else {
                System.out.println("you dont have much money");
            }
        } else {
            System.out.println("Sorry sum is very small");
        }
    }
}
