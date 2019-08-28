public class OperationsOfficer extends Salary {

    public OperationsOfficer() {
        salary = RandomData.randomSalary();
        title = "Оператор";
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public int compareTo(Salary pp) {
        return pp.getMonthSalary()-this.getMonthSalary();
    }
}
