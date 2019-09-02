class Manager extends Salary {

    private int income;

    public Manager() {
        deferment = RandomData.randomSalary();
        income = RandomData.randomIncome();
        incomeAll = getIncomeAll()+ income;
        title  = "Менеджер по продажам";
    }

    @Override
    public int getMonthSalary() {
        salary = deferment + income / 20;
        return salary;
    }
}
