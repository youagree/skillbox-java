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
        this.account -= account;
    }

    public void replenishAccount(double account) {
        this.account += account;
    }

    public void transferOfMoney(Client client, double account) {
        this.withdraw(account);
        client.replenishAccount(account);
    }
}
