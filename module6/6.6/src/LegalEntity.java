public class LegalEntity extends Client {

    public void withdraw(double account) {
        this.account = this.account - account - account / 100;
    }
}
