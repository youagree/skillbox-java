public class IndividualEntrepreneur extends Client {

    private static final double LOW_COMMISSION_TRESHOLD = 1000.0;
    private static final double BEFORE_TRESHOLD_COMMISSION = 0.01;
    private static final double AFTER_TRESHOLD_COMMISSION = 0.05;
    private static final double COMISSION_REPLENISH = 0.01;

    @Override
    public boolean withdraw(double account) {
        if (account < LOW_COMMISSION_TRESHOLD) {
            double resultBeforeTreshhold = this.balance - account + account * BEFORE_TRESHOLD_COMMISSION;
            return checkTransaction(resultBeforeTreshhold, 0.0);
        } else {
            double resultAfterTreshold = this.balance - account + account * AFTER_TRESHOLD_COMMISSION;
            return checkTransaction(0.0, resultAfterTreshold);
        }
    }

    @Override
    public void replenishAccount(double account) {
        this.balance += account - (account * COMISSION_REPLENISH);
    }

    @Override
    public boolean checkTransaction(double resultBeforeTreshhold, double resultAfterTreshold) {
        if(resultBeforeTreshhold >= 0 && resultAfterTreshold == 0.0){
            this.balance = resultBeforeTreshhold;
            return true;
        } else if (resultAfterTreshold >= 0 && resultBeforeTreshhold == 0.0) {
            this.balance = resultAfterTreshold;
            return true;
        } else {
            return false;
        }
    }
}
