import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private int incomeAll;
    private List<AbstractEmployee> list = new ArrayList<>();
    private String nameCompany;

    public Company(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public int getIncomeAll() {
        if (incomeAll != 0) {
            return incomeAll;
        } else {
            for (AbstractEmployee employee : list) {
                if (employee instanceof Manager) {
                    incomeAll += ((Manager) employee).getIncome();
                }
            }
        }
        return incomeAll;
    }

    public void addEmployee(int count) {
        for (int i = 0; i < count; i++) {
            switch (RandomData.randomEmployee()) {
                case 1:
                    list.add(new OperationsOfficer());
                    break;
                case 2:
                    list.add(new Manager());
                    break;
                case 3:
                    list.add(new ManagerTop());
                    break;
            }
        }
    }

    public List<AbstractEmployee> removeTenPercentEmpl(int percent) {
        percent = (int) (list.size() * (percent / 100.0f));
        for (int i = 0; i < percent; i++) {
            list.remove(RandomData.random());
        }
        return list;
    }

    public void topFiveSalary(SalaryParam salaryParam) {
        Company company = this;
        Collections.sort(list, (o1, o2) -> {
            if (o1.getSalary(company) < o2.getSalary(company)) {
                return 1;
            } else if (o1.getSalary(company) > o2.getSalary(company)) {
                return -1;
            } else {
                return 0;
            }
        });
        if (salaryParam.equals(SalaryParam.BIG)) {
            System.out.println("5 самых больших зарплат - " + company.getNameCompany());
            for (int i = 0; i <= 5; i++) {
                System.out.println(list.get(i).getTitle() + "-" + list.get(i).getSalary(company));
            }
        } else if (salaryParam.equals(SalaryParam.SMALL)) {
            System.out.println("5 самых маленьких зарплат - " + company.getNameCompany());
            for (int i = list.size() - 1; i >= list.size() - 5; i--) {
                System.out.println(list.get(i).getTitle() + "-" + list.get(i).getSalary(company));
            }
        }
    }

    public void printEmployee() {
        Company company = this;
        list.forEach((abstractEmployee) ->
                System.out.println(abstractEmployee.getTitle() + "-" + abstractEmployee.getSalary(company)));
    }
}
