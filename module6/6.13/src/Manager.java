class Manager extends Salary {

    public Manager() {
        deferment = RandomData.randomSalary();
        income = RandomData.randomIncome();
        Loader.incomeAll = Loader.incomeAll + income;
        title  = "Менеджер по продажам";
    }

    @Override
    public int getMonthSalary() {
        salary = deferment + income / 20;
        return salary;
    }

    @Override
    public int compareTo(Salary pp) {
        return pp.getMonthSalary()-this.getMonthSalary();
    }
}
