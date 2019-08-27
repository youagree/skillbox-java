public class IndividualEntrepreneur extends Client {

    private int sumAdd = 1000;

    public void setAccountAdd(double account) {
        if (account < sumAdd) {
            this.account = account - account / 100;
        } else {
            this.account = account - account / 200;
        }
    }
}
