
class Manager extends AbstractEmployee {

    private int income;
    private int deferment;
    private int salary;

    public Manager() {
        deferment = RandomData.randomSalary();
        income = RandomData.randomIncome();
        title  = "Менеджер по продажам";
    }

    @Override
    public int getSalary() {
        salary = deferment + income / 20;
        return salary;
    }

    public int getIncome() {
        return income;
    }
}
