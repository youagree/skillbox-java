public abstract class Client {

    protected double account;

    public Client() {
        account = 0.0;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public void withdraw(double account) {

    }

    public void replenishAccount(double account) {
        this.account += account;
    }

    public void transferOfMoney(Client client, double account) {
        if (this.getAccount() >= account) {
            this.withdraw(account);
            client.replenishAccount(account);
            System.out.println("GOOD");
        } else {
            System.out.println("you dont have much money");
        }
    }
}
