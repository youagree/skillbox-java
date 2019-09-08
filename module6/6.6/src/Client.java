public abstract class Client {

    private double balance;

    public Client() {
        balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void replenishAccount(double account) {
        this.balance += account;
    }

    public boolean withdraw(double account) {
     double resultOperation = this.balance - account;
     if (resultOperation > 0) {
         this.balance = resultOperation;
         return true;
     } else {
         return false;
     }
    }

    public void transferOfMoney(Client client, double account) {
        if (this.withdraw(account)) {
            client.replenishAccount(account);
            System.out.println("GOOD");
        } else {
            System.out.println("you dont have much money");
        }
    }
}
