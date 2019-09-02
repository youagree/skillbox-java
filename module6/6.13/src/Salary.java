abstract class Salary implements Comparable<Salary> {

    protected static int incomeAll;

    public int salary;
    public int deferment;
    public String title;

    public String getTitle() {
        return title;
    }

    public static int getIncomeAll() {
        return incomeAll;
    }

    public int getMonthSalary() {
        return salary;
    }

    public int compareTo(Salary quantity) {
        return quantity.getMonthSalary() - this.getMonthSalary();
    }
}
