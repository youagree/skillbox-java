
public class OperationsOfficer extends AbstractEmployee {

    private int salary;

    public OperationsOfficer() {
        salary = RandomData.randomSalary();
        title = "Оператор";
    }

    @Override
    public int getSalary() {
        return salary;
    }
}
