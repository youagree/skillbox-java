import java.time.LocalDate;

public class AccountDepository extends AccountBank {

    private LocalDate lastOperation;
    private LocalDate canTakeOf;

    @Override
    public void withdraw(double amountMoney) {
        this.account += amountMoney;
        lastOperation = LocalDate.now();
        canTakeOf = lastOperation.plusMonths(1);
    }

    @Override
    public void deposit(double amountMoney) {
          lastOperation = LocalDate.now();
          if (lastOperation.isAfter(canTakeOf)) {
              this.account -= amountMoney;
              System.out.println("Success");
          } else {
              System.out.println("Прошло меньше месяца с последнего внесения денег.");
          }
    }
}
