public class ManagerTop extends Salary {

    public ManagerTop() {
        title = "Топ менеджер";
        deferment = RandomData.randomSalary();
    }

    @Override
    public int getMonthSalary() {
        salary = deferment;
        if (getIncomeAll() > 10000000) salary = deferment + deferment / 10;
        return salary;
    }
}
