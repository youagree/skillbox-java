import java.lang.reflect.Field;

public class Loader {

    public static void main(String[] args) throws NoSuchFieldException {
        Company company = new Company();
        company.addEmployee(50);

        //check no repeats
        System.out.println(company.getIncomeAll());
        System.out.println(company.getIncomeAll());

        //try with Enum
        company.topFiveSalary(SalaryParam.BIG);
    }
}
