public class IndividualEntrepreneur extends Client {

    private static final double LOW_COMMISSION_TRESHOLD = 1000.0;
    private static final double BEFORE_TRESHOLD_COMMISSION = 0.01;
    private static final double AFTER_TRESHOLD_COMMISSION = 0.05;
    private static final double COMISSION_REPLENISH = 0.01;

    @Override
    public boolean withdraw(double account) {
        if (account < LOW_COMMISSION_TRESHOLD) {
              return super.withdraw(account + (account * BEFORE_TRESHOLD_COMMISSION));
        } else {
            return super.withdraw(account + (account * AFTER_TRESHOLD_COMMISSION));
        }
    }

    @Override
    public void replenishAccount(double account) {
        super.replenishAccount(account - (account * COMISSION_REPLENISH));
    }

}
