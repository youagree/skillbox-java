public class ManagerTop extends Company {

    public ManagerTop() {
        title = "Топ менеджер";
        deferment = RandomData.randomSalary();
    }

    @Override
    public int getSalary() {
        salary = deferment;
        if (super.getIncomeAll() > 10000000) {
            salary = deferment + deferment / 10;
        }
        return salary;
    }
}
