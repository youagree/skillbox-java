public class Loader {

    public static void main(String[] args) {

        AccountCash accountCash = new AccountCash();
        AccountDepository accountDepository = new AccountDepository();

        accountDepository.setAccountAdd(10.0);

        accountCash.setAccountAdd(20.0);
        System.out.println(accountCash.getAccount());

        accountCash.setAccountRemove(10.0);
        System.out.println(accountCash.getAccount());

        accountDepository.setAccountAdd(20.0);

        accountDepository.setAccountRemove(10.0);
        System.out.println(accountDepository.getAccount());
    }
}
