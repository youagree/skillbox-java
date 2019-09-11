import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private static Company company = new Company();

    private int incomeAll;
    private List<AbstractEmployee> list = new ArrayList<>();

    public static Company getCompany() {
        return company;
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
        Collections.sort(list);
        if (salaryParam.equals(SalaryParam.BIG)) {
            System.out.println("5 самых больших зарплат - ");
            for (int i = 0; i <= 5; i++) {
                System.out.println(list.get(i).getTitle() + "-" + list.get(i).getSalary());
            }
        } else if (salaryParam.equals(SalaryParam.SMALL)) {
            System.out.println("5 самых маленьких зарплат - ");
            for (int i = list.size() - 1; i >= list.size() - 5; i--) {
                System.out.println(list.get(i).getTitle() + "-" + list.get(i).getSalary());
            }
        }
    }

    public void printEmployee() {
        list.forEach((abstractEmployee) -> System.out.println(abstractEmployee.getTitle() + "-" + abstractEmployee.getSalary()));
    }
}
