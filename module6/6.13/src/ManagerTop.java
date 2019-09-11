
public class ManagerTop extends AbstractEmployee {

    private int deferment;

    public ManagerTop() {
        title = "Топ менеджер";
        deferment = RandomData.randomSalary();
    }

    @Override
    public int getSalary() {
        if (Company.getCompany().getIncomeAll() > 10000000) {
            deferment += deferment / 10;
        }
        return deferment;
    }

    @Override
    public int compareTo(AbstractEmployee o) {
        return o.getSalary()-this.getSalary();
    }
}
