import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company  implements Comparable<Company>{

    protected int salary;
    protected int deferment;
    protected String title;
    private int incomeAll;

    private List<Company> list = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public int getSalary() {
        return salary;
    }

    public int getIncomeAll() {
        for (Company employee : list) {
            if (employee instanceof Manager) {
                incomeAll += ((Manager) employee).getIncome();
            }
        }
        return incomeAll;
    }

    public List<Company> getList() {
        return list;
    }

    public void addEmployee (int count){
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

    public List<Company> removeTenPercentEmpl(int percent){
        percent = (int)(list.size()*(percent/100.0f));
        for (int i = 0; i < percent; i++){
            list.remove(RandomData.random());
        }
        return list;
    }

    public void topFiveSalary(String parameter){
        Collections.sort(list);
        if (parameter.equals("big")) {
            System.out.println("5 самых больших зарплат - ");
            for (int i = 0; i <= 5; i++) {
                System.out.println(list.get(i).title + "-" + list.get(i).getSalary());
            }
        } else if (parameter.equals("small")) {
            System.out.println("5 самых маленьких зарплат - ");
            for (int i = list.size() - 1; i >= list.size() - 5; i--) {
                System.out.println(list.get(i).title + "-" + list.get(i).getSalary());
            }
        }
    }

    public void printEmployee () {
        list.forEach((employee) -> System.out.println(employee.getTitle() + "-" + employee.getSalary()));
    }

    @Override
    public int compareTo(Company o) {
        return o.getSalary()-this.getSalary();
    }
}
