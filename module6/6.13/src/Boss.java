import java.util.ArrayList;
import java.util.List;

public class Boss extends AbstractEmployee {

    private int salary;
    private List<Company> listOfCompanies = new ArrayList<>();

    public Boss(int salary) {
        this.salary = salary;
    }

    @Override
    public int getSalary(Company company) {
        return salary;
    }

    public void addCompanyAndBecomeBoss (Company company) {
        listOfCompanies.add(company);
    }

    public void topFiveAmongCompanies () {
        listOfCompanies.stream().forEach(e -> e.topFiveSalary(SalaryParam.BIG));
    }
}
