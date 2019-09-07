import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company extends Salary{

    private List<Salary> list = new ArrayList<>();

    public List<Salary> getList() {
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

    public List<Salary> removeTenPercentEmpl(int percent){
        percent = (int)(list.size()*(percent/100.0f));
        for (int i = 0; i < percent; i++){
            list.remove(RandomData.random());
        }
        return list;
    }

    public void topFiveSalary(){
        Collections.sort(list);
        System.out.println("5 самых больших зарплат - ");
        for (int i = 0; i <= 5; i++) {
            System.out.println(list.get(i).title + "-" + list.get(i).getMonthSalary());
        }
    }

    public void printEmployee () {
        list.forEach((employee) -> {
            System.out.println(employee.getTitle() + "-" + employee.getMonthSalary());
       });
    }

}
