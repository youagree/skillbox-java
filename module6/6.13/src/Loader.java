import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Loader {

    private static final int TEN_PERCENT = (int)(270*(10.0f/100.0f));

    public static void main(String[] args) {

        ArrayList<Salary> allEmployee = new ArrayList<>();

        for (int i = 0; i < 270; i++) {
            switch (RandomData.randomEmployee()) {
                case 1:
                    allEmployee.add(new OperationsOfficer());
                    break;
                case 2:
                    allEmployee.add(new Manager());
                    break;
                case 3:
                    allEmployee.add(new ManagerTop());
                    break;
            }
        }

        Collections.sort(allEmployee);
//        System.out.println("Все сотрудники, всего " + allEmployee.size() + " человек.");
//        System.out.println("Все менеджеры принесли " + Salary.getIncomeAll() + " руб.");
//        String premium = Salary.getIncomeAll() > 10000000 ? "есть" : "не будет";
//        System.out.println("Премия топ manager " + premium);
//        allEmployee.forEach((employee) -> {
//            System.out.println(employee.getTitle() + "-" + employee.getMonthSalary());
//        });


        topFiveSalary(allEmployee);
        removeTenPercentEmpl(allEmployee);
        topFiveSalary(allEmployee);

//        System.out.println("5 самых маленьких зарплат - ");
//        for (int i = 269; i >= 264; i--) {
//
//            System.out.println(allEmployee.get(i).title + "-" + allEmployee.get(i).getMonthSalary());
//        }
    }


    public  static List<Salary> removeTenPercentEmpl(List<Salary> list){
        for (int i = 0; i < TEN_PERCENT; i++){
            list.remove(RandomData.random());
            System.out.println(RandomData.random());
        }
        return list;
    }

    public  static List<Salary> topFiveSalary(List<Salary> list){
        System.out.println("5 самых больших зарплат - ");
        for (int i = 0; i <= 5; i++) {
            System.out.println(list.get(i).title + "-" + list.get(i).getMonthSalary());
        }
        return list;
    }
}
