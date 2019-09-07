

public class Loader {

    public static void main(String[] args) {

    Company google = new Company();

    google.addEmployee(300);
    google.topFiveSalary();
    System.out.println(Salary.getIncomeAll());

    google.removeTenPercentEmpl(10);
    System.out.println(Salary.getIncomeAll());

    Company microsoft = new Company();
    microsoft.addEmployee(10);
    microsoft.topFiveSalary();
    }

}
