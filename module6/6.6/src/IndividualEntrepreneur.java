public class IndividualEntrepreneur extends Client {

    private static final double LOW_COMMISSION_TRESHOLD = 1000.0;
    private static final double BEFORE_TRESHOLD_COMMISSION = 0.01;
    private static final double AFTER_TRESHOLD_COMMISSION = 0.05;

    @Override
    public void withdraw(double account) {
        if (account < LOW_COMMISSION_TRESHOLD) {
            this.account -= account + account * BEFORE_TRESHOLD_COMMISSION;
        } else {
            this.account -= account + account * AFTER_TRESHOLD_COMMISSION;
        }
    }
}
