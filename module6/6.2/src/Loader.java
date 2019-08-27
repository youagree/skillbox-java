public class Loader {

    public static void main(String[] args) {

        AccountCash accountCash = new AccountCash();
        AccountDepository accountDepository = new AccountDepository();

        accountDepository.withdraw(10.0);

        accountCash.withdraw(20.0);
        System.out.println(accountCash.getAccount());

        accountCash.deposit(10.0);
        System.out.println(accountCash.getAccount());

        accountDepository.withdraw(20.0);

        accountDepository.deposit(10.0);
        System.out.println(accountDepository.getAccount());
    }
}
