public class IndividualEntrepreneur extends Client {

    private static final int SUM_ADD = 1000;
    private static final int FIRST_CONDITION = 100;
    private static final int SECOND_CONDITION = 1000;

    public void withdraw(double account) {
        if (account < SUM_ADD) {
            this.account = account - account / FIRST_CONDITION;
        } else {
            this.account = account - account / SECOND_CONDITION;
        }
    }
}
