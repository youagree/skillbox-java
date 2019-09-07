public abstract class Client {

    protected double balance;

    public Client() {
        balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double account) {
        double result = this.balance - account;
        return checkTransaction(result);
    }

    public void replenishAccount(double account) {
        this.balance += account;
    }

    public boolean checkTransaction (double result) {
        if(result >= 0){
            this.balance = result;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkTransaction (double resultOne, double resultTwo) {
        return true;
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
