import java.util.Calendar;
import java.util.Date;

public class AccountDepository extends AccountBank {

    private long lastAdd = 0L;

    public void setAccountAdd(double amountMoney) {
        super.account = super.account + amountMoney;
        lastAdd = System.currentTimeMillis();
    }

    public void setAccountRemove(double amountMoney) {
        Date lastPayment = new Date(lastAdd);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, -1);
        if (lastPayment.after(calendar.getTime())) {
            System.out.println("Прошло меньше месяца с последнего внесения денег.");
        } else {
            super.account = super.account - amountMoney;
        }
    }
}
