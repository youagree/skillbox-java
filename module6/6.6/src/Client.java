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

    public void setAccountRemove(double account) {
        this.account -= account;
    }

    public void setAccountAdd(double account) {
        this.account += account;
    }
}
